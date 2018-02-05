package app.positiveculture.com.user.screen.usernric;

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

import java.io.File;
import java.io.IOException;

import app.positiveculture.com.agent.GlobalStuff;
import app.positiveculture.com.agent.customview.UserIdTypeSpinner;
import app.positiveculture.com.agent.utils.FileUtils;
import app.positiveculture.com.agent.utils.MediaUtils;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.enumdata.TypeID;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The UserNric Fragment
 */
public class UserNricFragment extends ViewFragment<UserNricContract.Presenter> implements UserNricContract.View {

  @BindView(R2.id.upload_img_ll)
  LinearLayout mUploadImgLL;
  @BindView(R2.id.nric_front_view_iv)
  SimpleDraweeView mNricFrontViewIv;
  @BindView(R2.id.nric_back_view_iv)
  SimpleDraweeView mNricBackViewIv;
  @BindView(R2.id.nric_number_et)
  EditText mNricNumberEt;
  @BindView(R2.id.custom_header_view)
  CustomHeaderView mHeader;
  @BindView(R2.id.user_nric_type_spinner)
  UserIdTypeSpinner mIdType;

  @OnTouch(R2.id.user_nric_ll)
  boolean doTouchLayout() {
    getBaseActivity().hideKeyboard();
    return false;
  }

  private Uri mSelectedFrontUri;
  private Uri mSelectedBackUri;
  private String mCaptureImagePath;
  private String mRealFrontImageFilePath;
  private String mRealBackImageFilePath;

  private static final int TAKE_PICTURE_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final int TAKE_PICTURE_REQUEST_CODE_BACK = GlobalStuff.getFreshInt();
  private static final int TAKE_PICTURE_PERMISSIONS_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

  public static UserNricFragment getInstance() {
    return new UserNricFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_user_nric;
  }

  @OnClick(R2.id.left_icon_iv)
  public void back() {
    mPresenter.back();
  }

  @OnClick(R2.id.right_text_tv)
  public void saveNric() {
    String nric = mNricNumberEt.getText().toString().trim();
    if (!StringUtils.isEmpty(nric)) {
      mPresenter.saveNricProfile(nric, mIdType.getSelectedType());
    } else {
      DialogUtils.showDialog(getViewContext(), getString(R.string.error), getString(R.string.nric_empty), new CustomDialog.OnConfirmSelected() {
        @Override
        public void onConfirmSelected() {

        }
      });
    }
  }

  @Override
  public void hideUploadImg() {
    mUploadImgLL.setVisibility(View.GONE);
  }

  @Override
  public void bindNric(MemberDTO mMemberDTO) {
    if (mMemberDTO.getIdType() == TypeID.co_reg_no) {
      mIdType.getSpinner().setSelection(1);
    }

    mNricNumberEt.setText(mMemberDTO.getmIdNumber());

    if (mMemberDTO.getNricFrontImage() != null) {
      mNricFrontViewIv.setVisibility(View.VISIBLE);
      ViewUtils.loadAvatarWithFresco(mNricFrontViewIv, mMemberDTO.getNricFrontImage().getImgMedium());
    }

    if (mMemberDTO.getNricBackImage() != null) {
      mNricBackViewIv.setVisibility(View.VISIBLE);
      ViewUtils.loadAvatarWithFresco(mNricBackViewIv, mMemberDTO.getNricBackImage().getImgMedium());
    }
  }

  @Override
  public void bindNricUpload(FileDTO data, boolean b) {
    if (data == null) return;
    if (b) {
      mNricFrontViewIv.setVisibility(View.VISIBLE);
      ViewUtils.loadAvatarWithFresco(mNricFrontViewIv, data.getImgMedium());
    } else {
      mNricBackViewIv.setVisibility(View.VISIBLE);
      ViewUtils.loadAvatarWithFresco(mNricBackViewIv, data.getImgMedium());
    }
  }

  @OnClick(R2.id.nric_view_front_rl)
  void onCaptureNricFrontView() {
    takePicture(TAKE_PICTURE_REQUEST_CODE);
  }

  @OnClick(R2.id.nric_view_back_rl)
  void onCaptureNricBackView() {
    takePicture(TAKE_PICTURE_REQUEST_CODE_BACK);
  }

  private void takePicture(int requestCode) {
    if (!PermissionUtils.needRequestPermissions(getActivity(), this, PERMISSIONS, TAKE_PICTURE_PERMISSIONS_REQUEST_CODE)) {
      callCameraIntent(requestCode);
    }
  }

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
//        mSelectedFrontUri = null;
      } else {
//        mSelectedBackUri = null;
      }

      return;
    }

    if (TAKE_PICTURE_REQUEST_CODE == requestCode) {

      viewImage(mNricFrontViewIv, requestCode);

    } else if (TAKE_PICTURE_REQUEST_CODE_BACK == requestCode) {

      viewImage(mNricBackViewIv, requestCode);

    }
  }

  private void viewImage(SimpleDraweeView simpleDraweeView, int requestCode) {
    final boolean isNougat = DeviceUtils.isNougat();

    if (isNougat) {
      viewImageFromUri(Uri.fromFile(new File(mCaptureImagePath)), simpleDraweeView, requestCode);
    } else {
      if (requestCode == TAKE_PICTURE_REQUEST_CODE) {
        viewImageFromUri(mSelectedFrontUri, simpleDraweeView, requestCode);
      } else {
        viewImageFromUri(mSelectedBackUri, simpleDraweeView, requestCode);
      }
    }
  }

  private void viewImageFromUri(Uri imageUri, SimpleDraweeView simpleDraweeView, int requestCode) {
    if (imageUri != null) {
      ViewUtils.rotateImage(imageUri);
      if (requestCode == TAKE_PICTURE_REQUEST_CODE) {
        mRealFrontImageFilePath = ViewUtils.getRealPathFromURI(imageUri, getActivity());
        mPresenter.uploadNricImage(mSelectedFrontUri, mRealFrontImageFilePath, true);
      } else {
        mRealBackImageFilePath = ViewUtils.getRealPathFromURI(imageUri, getActivity());
        mPresenter.uploadNricImage(mSelectedBackUri, mRealBackImageFilePath, false);
      }
    }
  }


}
