package com.positiveculture.app.screen.setupprofile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.DeviceUtils;
import com.gemvietnam.utils.PermissionUtils;
import com.gemvietnam.utils.StringUtils;
import com.positiveculture.app.R;
import com.positiveculture.app.R2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.GlobalStuff;
import app.positiveculture.com.agent.utils.CountryCode;
import app.positiveculture.com.agent.utils.FileUtils;
import app.positiveculture.com.agent.utils.MediaUtils;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.PhotoDTO;
import app.positiveculture.com.data.response.dto.Users;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;
import dialog.CustomDialog;
import dialog.DialogAction;
import utils.DialogUtils;
import utils.PopupUtils;

/**
 * The SetupProfile Fragment
 */
public class SetupProfileFragment extends ViewFragment<SetupProfileContract.Presenter> implements SetupProfileContract.View {

  @BindView(R2.id.setup_profile_name)
  EditText mName;
  @BindView(R2.id.setup_profile_email)
  EditText mEmail;
  @BindView(R2.id.setup_profile_phone)
  EditText mPhone;
  @BindView(R2.id.setup_profile_mobile_spinner)
  Spinner mCountryCode;
  @BindView(R2.id.setup_profile_country_code_tv)
  TextView mCountryCodetv;
  @BindView(R2.id.setup_profile_avatar)
  SimpleDraweeView mAvatar;

  private ArrayAdapter<String> mAdapterCountryCode;
  List<String> listCountryCode;
  private Uri mSelectedUri;
  private String mCaptureImagePath;
  private String mRealFrontImageFilePath;
  private PhotoDTO photo;

  private static final int TAKE_PICTURE_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final int PICK_PICTURE_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final int TAKE_PICTURE_PERMISSIONS_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final int READ_EXTERNAL_STORAGE_PERMISSIONS_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
  private static final String[] READ_EXTERNAL_STORAGE = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};

  @OnTouch(R2.id.setup_profile_ll)
  boolean doTouchLayout() {
    getBaseActivity().hideKeyboard();
    return false;
  }

  @OnClick(R2.id.left_text_tv)
  void doLaterClick() {
    mPresenter.goToNricUpload();
  }

  @OnClick(R2.id.add_avatar_setup_profile)
  void doAddAvatarClick() {
    showDialogChooseImage();
  }

  @OnClick(R2.id.right_text_tv)
  void doSaveProfile() {
    String name = mName.getText().toString();
    String email = mEmail.getText().toString();
    String phone = mPhone.getText().toString();
    String countryCode = mCountryCodetv.getText().toString();

    if (!StringUtils.isEmpty(name) && StringUtils.isEmailValid(email)
            && phone.length() >= 8) {
      mPresenter.updateProfile(name, email, countryCode, phone);
    } else {
      if (StringUtils.isEmpty(name)) {
        showDialog(getString(R.string.setup_profile_messenger_name));
        mName.requestFocus();
        return;
      }
      if (StringUtils.isEmpty(phone)) {
        showDialog(getString(R.string.setup_profile_messenger_phone_1));
        mPhone.requestFocus();
        return;
      }
      if (phone.length() < 8) {
        showDialog(getString(R.string.setup_profile_messenger_phone_2));
        mPhone.requestFocus();
        return;
      }
      if (StringUtils.isEmpty(email)) {
        showDialog(getString(R.string.setup_profile_messenger_email_2));
        mEmail.requestFocus();
        return;
      }
      if (!StringUtils.checkEmail(email)) {
        showDialog(getString(R.string.setup_profile_messenger_email_1));
        mEmail.requestFocus();
      }
    }
  }

  private void showDialog(String messenger) {
    DialogUtils.showDialog(getViewContext(), getString(R.string.error), messenger, new CustomDialog.OnConfirmSelected() {
      @Override
      public void onConfirmSelected() {
      }
    });
  }


  public static SetupProfileFragment getInstance() {
    return new SetupProfileFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_setup_profile;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    listCountryCode = CountryCode.getCode();
    mAdapterCountryCode = new ArrayAdapter<String>(getViewContext(), android.R.layout.simple_spinner_dropdown_item, listCountryCode);
    mCountryCode.setAdapter(mAdapterCountryCode);
    mCountryCode.setSelection(0);
    mCountryCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mCountryCodetv.setText(parent.getSelectedItem().toString());
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
  }

  private void showDialogChooseImage() {
    List<DialogAction> dialogActionList = new ArrayList<DialogAction>();

    dialogActionList.add(new DialogAction("Take Photo", new DialogAction.OnActionSelectedListener() {
      @Override
      public void onActionSelected() {
        takePicture();
      }
    }));

    dialogActionList.add(new DialogAction("Choose Photo", new DialogAction.OnActionSelectedListener() {
      @Override
      public void onActionSelected() {
        pickFromGallery();
      }
    }));

    PopupUtils.showActionListDialog(getViewContext(), dialogActionList);
  }

  private void takePicture() {
    if (!PermissionUtils.needRequestPermissions(getActivity(), this, PERMISSIONS, TAKE_PICTURE_PERMISSIONS_REQUEST_CODE)) {
      callCameraIntent();
    }
  }

  private void callCameraIntent() {
    try {
//      mImageFile = MediaUtils.createImageFile(getActivity());
      final boolean isNougat = DeviceUtils.isNougat();

      if (!isNougat) {
        mSelectedUri = Uri.fromFile(MediaUtils.createImageFile(getActivity()));
        MediaUtils.dispatchTakePictureIntent(getActivity(), TAKE_PICTURE_REQUEST_CODE, mSelectedUri);
      } else {
        mCaptureImagePath = FileUtils.getPathTempFile(getActivity());
        mSelectedUri = Uri.fromFile(new File(mCaptureImagePath));
        MediaUtils.dispatchTakePictureIntentWithNougat(getActivity(), TAKE_PICTURE_REQUEST_CODE, mCaptureImagePath);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void pickFromGallery() {
    if (!PermissionUtils.needRequestPermissions(getActivity(), SetupProfileFragment.this, READ_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE_PERMISSIONS_REQUEST_CODE)) {
      Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
      photoPickerIntent.setType("image/*");
      startActivityForResult(photoPickerIntent, PICK_PICTURE_REQUEST_CODE);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == READ_EXTERNAL_STORAGE_PERMISSIONS_REQUEST_CODE &&
            PermissionUtils.isPermissionsGranted(getActivity(), grantResults)) {
      Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
      photoPickerIntent.setType("image/*");
      startActivityForResult(photoPickerIntent, PICK_PICTURE_REQUEST_CODE);
    } else if (requestCode == TAKE_PICTURE_PERMISSIONS_REQUEST_CODE && PermissionUtils.isPermissionsGranted(getActivity(), grantResults)) {
      callCameraIntent();
    }
  }


  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode != Activity.RESULT_OK) {
      mSelectedUri = null;
      return;
    }

    if (PICK_PICTURE_REQUEST_CODE == requestCode) {
      photo = new PhotoDTO();
      mSelectedUri = data.getData();
      mRealFrontImageFilePath = ViewUtils.getRealPathFromURI(mSelectedUri, getActivity());

      ViewUtils.rotateImage(mSelectedUri);
      photo.setPhotoUrl(mSelectedUri);
      photo.setRealImagePath(mRealFrontImageFilePath);
      mPresenter.uploadAvatar(photo);
    } else if (TAKE_PICTURE_REQUEST_CODE == requestCode) {
      final boolean isNougat = DeviceUtils.isNougat();
      photo = new PhotoDTO();
      if (isNougat) {
        mSelectedUri = Uri.fromFile(new File(mCaptureImagePath));
        photo.setPhotoUrl(mSelectedUri);
      } else {
        photo.setPhotoUrl(mSelectedUri);
      }
      mRealFrontImageFilePath = ViewUtils.getRealPathFromURI(mSelectedUri, getActivity());
      photo.setRealImagePath(mRealFrontImageFilePath);
      ViewUtils.rotateImage(mSelectedUri);
      mPresenter.uploadAvatar(photo);
    }
  }

  @Override
  public void bindAvatar(FileDTO data) {
    ViewUtils.loadAvatarWithFresco(mAvatar, data.getImgSmall());
  }

  @Override
  public void bindUser(Users mUsers) {
    mName.setText(mUsers.getFullName());
    mPhone.setText(mUsers.getPhoneNumber());
    mEmail.setText(mUsers.getEmail());
    for (int i = 0; i < listCountryCode.size(); i++) {
      if (listCountryCode.get(i).equals(mUsers.getPhoneCountryCode())) {
        mCountryCode.setSelection(i);
      }
    }
  }
}
