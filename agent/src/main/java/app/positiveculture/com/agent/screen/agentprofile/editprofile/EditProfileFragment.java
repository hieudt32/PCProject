package app.positiveculture.com.agent.screen.agentprofile.editprofile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.DeviceUtils;
import com.gemvietnam.utils.PermissionUtils;
import com.gemvietnam.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.GlobalStuff;
import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.CustomProfileForm;
import app.positiveculture.com.agent.utils.FileUtils;
import app.positiveculture.com.agent.utils.MediaUtils;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.FileDTO;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import dialog.DialogAction;
import utils.DialogUtils;
import utils.PopupUtils;

/**
 * The EditProfile Fragment
 */
public class EditProfileFragment extends ViewFragment<EditProfileContract.Presenter> implements EditProfileContract.View {

  @BindView(R2.id.edit_profile_custom_header)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.edit_profile_avatar_sdv)
  SimpleDraweeView mAvatarSdv;
  @BindView(R2.id.edit_profile_name_form)
  CustomProfileForm mNameForm;
  @BindView(R2.id.edit_profile_phone_form)
  CustomProfileForm mPhoneForm;
  @BindView(R2.id.edit_profile_email_form)
  CustomProfileForm mEmailForm;
  @BindView(R2.id.edit_profile_cea_form)
  CustomProfileForm mCeaForm;
  @BindView(R2.id.edit_profile_agency_form)
  CustomProfileForm mAgencyForm;
  @BindView(R2.id.edit_profile_bank_form)
  CustomProfileForm mBankForm;
  @BindView(R2.id.edit_profile_nric_form)
  CustomProfileForm mNricForm;

  @OnClick(R2.id.edit_profile_bank_form)
  void onBankFromClick() {
    mPresenter.goToBankDetails();
  }

  @OnClick(R2.id.edit_profile_nric_form)
  void onNricFromClick() {
    mPresenter.goToNric();
  }

  private static final int TAKE_PICTURE_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final int PICK_PICTURE_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final int TAKE_PICTURE_PERMISSIONS_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final int READ_EXTERNAL_STORAGE_PERMISSIONS_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
  private static final String[] READ_EXTERNAL_STORAGE = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};

  private String mCurrentEmail;
  private Uri mSelectedUri;
  private String mCaptureImagePath;
  private String mRealFrontImageFilePath;

  public static EditProfileFragment getInstance() {
    return new EditProfileFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_edit_profile;
  }

  @Override
  public void initLayout() {
    super.initLayout();

    mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
    mEmailForm.getContentEt().setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) getBaseActivity().hideKeyboard(mEmailForm.getContentEt());
      }
    });

    mBankForm.getContentTv().setTextColor(ContextCompat.getColor(getViewContext(), R.color.tc_black87));
    mNricForm.getContentTv().setTextColor(ContextCompat.getColor(getViewContext(), R.color.tc_black87));
    setupListener();
  }

  private void setupListener() {
    mHeaderView.getLeftTextTv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.goBack();
      }
    });
    mHeaderView.getRightTextTv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String email = mEmailForm.getContentEt().getText().toString();
        if (StringUtils.isEmailValid(email)) {
          mPresenter.saveEditProfile(mEmailForm.getContentEt().getText().toString());
        } else if (StringUtils.isEmpty(email)) {
          DialogUtils.showDialog(getViewContext(), getString(R.string.error), getString(R.string.email_empty), new CustomDialog.OnConfirmSelected() {
            @Override
            public void onConfirmSelected() {
            }
          });
        } else if (!StringUtils.isEmailValid(email)) {
          DialogUtils.showDialog(getViewContext(), getString(R.string.error), getString(R.string.email_invalidate), new CustomDialog.OnConfirmSelected() {
            @Override
            public void onConfirmSelected() {
            }
          });
        }

      }
    });

    mAvatarSdv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
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
    });

    mEmailForm.getContentEt().addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (!StringUtils.isEmpty(s.toString().trim()) && !s.toString().trim().equals(mCurrentEmail)) {
          mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
        } else {
          if (mSelectedUri == null) {
            mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
            return;
          }
          mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
        }
      }
    });
  }

  /**
   * Take a picture from camera
   */
  private void takePicture() {
    if (!PermissionUtils.needRequestPermissions(getActivity(), EditProfileFragment.this, PERMISSIONS, TAKE_PICTURE_PERMISSIONS_REQUEST_CODE)) {
      callCameraIntent();
    }
  }

  /**
   * Pick image from gallery
   */
  private void pickFromGallery() {
    if (!PermissionUtils.needRequestPermissions(getActivity(), EditProfileFragment.this, READ_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE_PERMISSIONS_REQUEST_CODE)) {
      Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
      photoPickerIntent.setType("image/*");
      startActivityForResult(photoPickerIntent, PICK_PICTURE_REQUEST_CODE);
    }
  }

  /**
   * Call camera intent
   */
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
      mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
      mSelectedUri = null;
      return;
    }

    if (PICK_PICTURE_REQUEST_CODE == requestCode) {
      mSelectedUri = data.getData();
      mRealFrontImageFilePath = ViewUtils.getRealPathFromURI(mSelectedUri, getActivity());
      mPresenter.uploadAvatar(mSelectedUri, mRealFrontImageFilePath);

    } else if (TAKE_PICTURE_REQUEST_CODE == requestCode) {
      final boolean isNougat = DeviceUtils.isNougat();
      if (isNougat) {
        mSelectedUri = Uri.fromFile(new File(mCaptureImagePath));
      }
      mRealFrontImageFilePath = ViewUtils.getRealPathFromURI(mSelectedUri, getActivity());
      ViewUtils.rotateImage(mSelectedUri);
      mPresenter.uploadAvatar(mSelectedUri, mRealFrontImageFilePath);
    }

    mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
  }

  @Override
  public void setupData(AgentDTO agentDTO) {
    mCurrentEmail = agentDTO.getUsers().getEmail();
    if (agentDTO.getAvatar() != null && agentDTO.getAvatar().getImgSmall() != null) {
      ViewUtils.loadAvatarWithFresco(mAvatarSdv, agentDTO.getAvatar().getImgSmall());
    } else {
      mAvatarSdv.setImageResource(R.drawable.ic_avatar);
    }
    mNameForm.setContentTv(agentDTO.getUsers().getFullName());
    mPhoneForm.setContentTv(agentDTO.getUsers().getPhoneCountryCode() + " " + agentDTO.getUsers().getPhoneNumber());
    mEmailForm.setContentEt(agentDTO.getUsers().getEmail());
    mCeaForm.setContentTv(agentDTO.getCEA().getCEANumber());
    mAgencyForm.setContentTv(agentDTO.getAgency());
    mBankForm.setContentTv(agentDTO.getBankName());
    mNricForm.setContentTv(agentDTO.getNric());
  }

  @Override
  public void showAvatar(FileDTO data) {
    ViewUtils.loadAvatarWithFresco(mAvatarSdv, data.getImgSmall());
  }

  @Override
  public void updateBankDetails(String bankName, String accountNumber, String accountType) {
    mBankForm.getContentTv().setText(bankName);
  }

  @Override
  public void updateNric(String nric) {
    mNricForm.getContentTv().setText(nric);
  }
}
