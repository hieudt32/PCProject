package app.positiveculture.com.agent.screen.nricupload;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.DeviceUtils;
import com.gemvietnam.utils.PermissionUtils;
import com.gemvietnam.utils.StringUtils;

import java.io.File;
import java.io.IOException;

import app.positiveculture.com.agent.GlobalStuff;
import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.utils.FileUtils;
import app.positiveculture.com.agent.utils.MediaUtils;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.PhotoDTO;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTouch;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The NricUpload Fragment
 */
public class NricUploadFragmentAgent extends ViewFragment<NricUploadContractAgent.Presenter> implements NricUploadContractAgent.View {

  @BindView(R2.id.nric_front_view_iv)
  SimpleDraweeView mNricFrontViewIv;
  @BindView(R2.id.nric_back_view_iv)
  SimpleDraweeView mNricBackViewIv;
  @BindView(R2.id.nric_number_et)
  EditText mNricNumberEt;
  @BindView(R2.id.custom_header_view)
  CustomHeaderView mHeader;

  @OnTouch(R2.id.nric_upload_layout)
  boolean doTouchLayout() {
    getBaseActivity().hideKeyboard();
    return false;
  }

  @OnClick(R2.id.right_text_tv)
  void saveNricProfile() {
    getBaseActivity().hideKeyboard();
    if (!StringUtils.isEmpty(mNricNumberEt.getText())) {
      mPresenter.saveNricProfile(mNricNumberEt.getText().toString());
    } else if (StringUtils.isEmpty(mNricNumberEt.getText())) {
      showDialog(getString(R.string.error), getString(R.string.nric_empty));
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

  private static final int TAKE_PICTURE_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final int TAKE_PICTURE_REQUEST_CODE_BACK = GlobalStuff.getFreshInt();
  private static final int TAKE_PICTURE_PERMISSIONS_REQUEST_CODE = GlobalStuff.getFreshInt();
  private static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};

  private Uri mSelectedFrontUri;
  private Uri mSelectedBackUri;
  private String mCaptureImagePath;
  private PhotoDTO mFrontPhoto = new PhotoDTO();
  private PhotoDTO mBackPhoto = new PhotoDTO();
  private String mNric;
  boolean isEdited = false;

  public static NricUploadFragmentAgent getInstance() {
    return new NricUploadFragmentAgent();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_nric_upload;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mHeader.getLeftIconIv().setImageResource(R.drawable.ic_back);
    mHeader.getLeftTextTv().setVisibility(View.GONE);
    mHeader.getLeftIconIv().setVisibility(View.VISIBLE);
    mHeader.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.back();
      }

      @Override
      public void onRightIconClick() {

      }
    });
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
      viewImage(mNricFrontViewIv, requestCode);

    } else if (TAKE_PICTURE_REQUEST_CODE_BACK == requestCode) {
      if (mSelectedBackUri != null) mBackPhoto.setPhotoUrl(mSelectedBackUri);
      viewImage(mNricBackViewIv, requestCode);
    }
  }

  private void viewImage(SimpleDraweeView simpleDraweeView, int requestCode) {
    final boolean isNougat = DeviceUtils.isNougat();

    if (isNougat) {
      viewImageFromUri(Uri.fromFile(new File(mCaptureImagePath)), simpleDraweeView, requestCode);
    } else {
      if (requestCode == TAKE_PICTURE_REQUEST_CODE) {
        if (mFrontPhoto.getPhotoUrl() != null)
          viewImageFromUri(mFrontPhoto.getPhotoUrl(), simpleDraweeView, requestCode);
      } else {
        if (mBackPhoto.getPhotoUrl() != null)
          viewImageFromUri(mBackPhoto.getPhotoUrl(), simpleDraweeView, requestCode);
      }
    }
  }

  /**
   * View image from File
   */
  private void viewImageFromUri(Uri imageUri, SimpleDraweeView simpleDraweeView, int requestCode) {
    if (imageUri != null) {
      if (requestCode == TAKE_PICTURE_REQUEST_CODE) {
        mFrontPhoto.setRealImagePath(ViewUtils.getRealPathFromURI(imageUri, getActivity()));
        mPresenter.uploadImage(mFrontPhoto, true);
      } else {
        mBackPhoto.setRealImagePath(ViewUtils.getRealPathFromURI(imageUri, getActivity()));
        mPresenter.uploadImage(mBackPhoto, false);
      }
    } else {
      simpleDraweeView.setVisibility(View.GONE);
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
  public void bindView(String nricNumber, FileDTO nricFrontImage, FileDTO nricBackImage) {

    mNricNumberEt.setText(nricNumber);
    if (nricFrontImage != null && nricFrontImage.getImgMedium() != null) {
      mNricFrontViewIv.setVisibility(View.VISIBLE);
      ViewUtils.loadImageWithFresco(mNricFrontViewIv, nricFrontImage.getImgMedium());
    }
    if (nricBackImage != null && nricBackImage.getImgMedium() != null) {
      mNricBackViewIv.setVisibility(View.VISIBLE);
      ViewUtils.loadImageWithFresco(mNricBackViewIv, nricBackImage.getImgMedium());
    }
    this.mNric = nricNumber;
  }

  @Override
  public void bindViewNricImage(FileDTO mNricImage, boolean b) {
    if (b) {
      if (mNricImage != null && mNricImage.getImgMedium() != null) {
        mNricFrontViewIv.setVisibility(View.VISIBLE);
        ViewUtils.loadImageWithFresco(mNricFrontViewIv, mNricImage.getImgMedium());
      }
    } else {
      if (mNricImage != null && mNricImage.getImgMedium() != null) {
        mNricBackViewIv.setVisibility(View.VISIBLE);
        ViewUtils.loadImageWithFresco(mNricBackViewIv, mNricImage.getImgMedium());
      }
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
