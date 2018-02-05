package com.positiveculture.app.screen.nricupload;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.DeviceUtils;
import com.gemvietnam.utils.PermissionUtils;
import com.gemvietnam.utils.StringUtils;
import com.positiveculture.app.R;
import com.positiveculture.app.R2;

import java.io.File;
import java.io.IOException;

import app.positiveculture.com.agent.GlobalStuff;
import app.positiveculture.com.agent.customview.UserIdTypeSpinner;
import app.positiveculture.com.agent.utils.FileUtils;
import app.positiveculture.com.agent.utils.MediaUtils;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.enumdata.TypeID;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.PhotoDTO;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The NricUpload Fragment
 */
public class NricUploadFragment extends ViewFragment<NricUploadContract.Presenter> implements NricUploadContract.View {

  @BindView(R2.id.custom_header_view)
  CustomHeaderView mHeader;
  @BindView(R2.id.nric_front_view_iv)
  SimpleDraweeView mNricFrontViewIv;
  @BindView(R2.id.nric_back_view_iv)
  SimpleDraweeView mNricBackViewIv;
  @BindView(R2.id.nric_number_et)
  EditText mNricUploadNumberEt;
  @BindView(R2.id.nric_upload_user_form)
  LinearLayout mNricUploadUserForm;
  @BindView(R2.id.nric_upload_agent_form)
  LinearLayout mNricUploadAgentForm;
  @BindView(R2.id.nric_upload_id_type_sp)
  UserIdTypeSpinner mIdType;
  @BindView(R2.id.nric_upload_user_id_et)
  EditText mUserId;

  private static final int TAKE_PICTURE_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final int TAKE_PICTURE_REQUEST_CODE_BACK = GlobalStuff.getFreshInt();
  private static final int TAKE_PICTURE_PERMISSIONS_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};

  private Uri mSelectedFrontUri;
  private Uri mSelectedBackUri;
  private String mCaptureImagePath;
  private PhotoDTO mFrontPhoto = new PhotoDTO();
  private PhotoDTO mBackPhoto = new PhotoDTO();

  @OnTouch(R2.id.nric_upload_layout)
  boolean doTouchLayout() {
    getBaseActivity().hideKeyboard();
    return false;
  }

  @OnClick(R2.id.right_text_tv)
  void saveNricProfile() {
    getBaseActivity().hideKeyboard();
    if (mNricUploadAgentForm.isShown()) {
      if (!StringUtils.isEmpty(mNricUploadNumberEt.getText().toString().trim())) {
        mPresenter.saveNricProfile(mNricUploadNumberEt.getText().toString().trim());
      } else {
        showDialog(getString(R.string.error), getString(R.string.nric_empty));
      }
    } else {
      if (!StringUtils.isEmpty(mUserId.getText().toString().trim())) {
        mPresenter.saveUserId(mIdType.getSelectedType(), mUserId.getText().toString().trim());
      } else {
        showDialog(getString(R.string.error), getString(R.string.nric_empty));
      }
    }

  }

  @OnClick(R2.id.left_text_tv)
  void doLaterClick() {
    getBaseActivity().hideKeyboard();
    mPresenter.nextStep();
  }

  @Override
  public void showUserView(TypeID typeID, String idNumber) {
    mHeader.getLeftTextTv().setVisibility(View.GONE);
    mNricUploadAgentForm.setVisibility(View.GONE);
    mNricUploadUserForm.setVisibility(View.VISIBLE);
    if (typeID != null && typeID == TypeID.co_reg_no) {
      mIdType.getSpinner().setSelection(1);
    }
    if (idNumber != null) mUserId.setText(idNumber);
  }

  public static NricUploadFragment getInstance() {
    return new NricUploadFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_nric_upload;
  }

  @OnClick(R2.id.nric_view_front_rl)
  void onCaptureNricFrontView() {
    takePicture(TAKE_PICTURE_REQUEST_CODE);
  }

  @OnClick(R2.id.nric_view_back_rl)
  void onCaptureNricBackView() {
    takePicture(TAKE_PICTURE_REQUEST_CODE_BACK);
  }

  @Override
  public void initLayout() {
    super.initLayout();

  }

  /**
   * Take a picture from camera
   */
  private void takePicture(int requestCode) {
    if (!PermissionUtils.needRequestPermissions(getActivity(), this, PERMISSIONS, TAKE_PICTURE_PERMISSIONS_REQUEST_CODE)) {
      callCameraIntent(requestCode);
    }
  }

  /**
   * Call camera intent
   *
   * @param requestCode
   */
  private void callCameraIntent(int requestCode) {
    try {
//      mImageFile = MediaUtils.createImageFile(getActivity());
      final boolean isNougat = DeviceUtils.isNougat();

      if (!isNougat) {
        if (requestCode == TAKE_PICTURE_REQUEST_CODE) {
          mSelectedFrontUri = Uri.fromFile(MediaUtils.createImageFile(getActivity()));
          MediaUtils.dispatchTakePictureIntent(getActivity(), requestCode, mSelectedFrontUri);
        } else {
          mSelectedBackUri = Uri.fromFile(MediaUtils.createImageFile(getActivity()));
          MediaUtils.dispatchTakePictureIntent(getActivity(), requestCode, mSelectedBackUri);
        }
      } else {
        mCaptureImagePath = FileUtils.getPathTempFile(getActivity());
        if (requestCode == TAKE_PICTURE_REQUEST_CODE) {
          mSelectedFrontUri = Uri.fromFile(new File(mCaptureImagePath));
        } else {
          mSelectedBackUri = Uri.fromFile(new File(mCaptureImagePath));
        }

        MediaUtils.dispatchTakePictureIntentWithNougat(getActivity(), requestCode, mCaptureImagePath);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == TAKE_PICTURE_PERMISSIONS_REQUEST_CODE && PermissionUtils.isPermissionsGranted(grantResults)) {
      callCameraIntent(requestCode);
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode != Activity.RESULT_OK) {
      if (requestCode == TAKE_PICTURE_REQUEST_CODE) {
        mSelectedFrontUri = null;
      } else {
        mSelectedBackUri = null;
      }
      return;
    }

    if (TAKE_PICTURE_REQUEST_CODE == requestCode) {
      if (mSelectedFrontUri != null) mFrontPhoto.setPhotoUrl(mSelectedFrontUri);
      viewImage(requestCode);
    } else if (TAKE_PICTURE_REQUEST_CODE_BACK == requestCode) {
      if (mSelectedBackUri != null) mBackPhoto.setPhotoUrl(mSelectedBackUri);
      viewImage(requestCode);
    }
  }

  private void viewImage(int requestCode) {
    if (requestCode == TAKE_PICTURE_REQUEST_CODE) {
      if (mFrontPhoto.getPhotoUrl() != null) {
        mFrontPhoto.setRealImagePath(ViewUtils.getRealPathFromURI(mFrontPhoto.getPhotoUrl(), getActivity()));
        mPresenter.uploadNricImage(mFrontPhoto, true);
      }
    } else {
      if (mBackPhoto.getPhotoUrl() != null) {
        mBackPhoto.setRealImagePath(ViewUtils.getRealPathFromURI(mBackPhoto.getPhotoUrl(), getActivity()));
        mPresenter.uploadNricImage(mBackPhoto, false);
      }

    }
  }


  private void showDialog(String Title, String Messenger) {
    DialogUtils.showDialog(getViewContext(), Title, Messenger, new CustomDialog.OnConfirmSelected() {
      @Override
      public void onConfirmSelected() {

      }
    });
  }

  @Override
  public void bindView(FileDTO mFrontImage, FileDTO mBackImage) {
    if (mFrontImage != null && mFrontImage.getImgMedium() != null) {
      mNricFrontViewIv.setVisibility(View.VISIBLE);
      ViewUtils.loadImageWithFresco(mNricFrontViewIv, mFrontImage.getImgMedium());
    } else {
      mNricFrontViewIv.setVisibility(View.GONE);
    }

    if (mBackImage != null && mBackImage.getImgMedium() != null) {
      mNricBackViewIv.setVisibility(View.VISIBLE);
      ViewUtils.loadImageWithFresco(mNricBackViewIv, mBackImage.getImgMedium());
    } else {
      mNricBackViewIv.setVisibility(View.GONE);
    }
  }

  @Override
  public void showErrorDialog(boolean b) {
    if (b) {
      showDialog(getString(R.string.error), getString(R.string.nric_front_empty));
    } else {
      showDialog(getString(R.string.error), getString(R.string.nric_back_empty));
    }
  }
}
