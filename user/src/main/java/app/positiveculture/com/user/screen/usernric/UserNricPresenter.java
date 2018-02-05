package app.positiveculture.com.user.screen.usernric;

import android.net.Uri;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The UserNric Presenter
 */
public class UserNricPresenter extends Presenter<UserNricContract.View, UserNricContract.Interactor>
        implements UserNricContract.Presenter {

  private int needUpLoadImg;
  private OnSaveNricListener onSaveNricListener;
  private MemberDTO mMemberDTO;
  private FileDTO mFrontImage;
  private FileDTO mBackImage;

  public void setOnSaveNricListener(OnSaveNricListener onSaveNricListener) {
    this.onSaveNricListener = onSaveNricListener;
  }

  @Override
  public int getNeedUpLoadImg() {
    return needUpLoadImg;
  }

  @Override
  public void saveNricProfile(String idNumber, String idType) {
    if (mBackImage == null || mFrontImage == null) return;
    mView.showProgress();
    mInteractor.saveNricProfile(idNumber, idType, mFrontImage.getId(), mBackImage.getId(), new CommonCallback<User>(getViewContext()) {
      @Override
      public void onSuccess(User data) {
        super.onSuccess(data);
        mView.hideProgress();
        if (data instanceof MemberDTO) {
          mMemberDTO = (MemberDTO) data;
          PrefWrapper.saveMember((MemberDTO) data);
          if (onSaveNricListener != null) {
            onSaveNricListener.onSaveNric(mMemberDTO);
          }
        }
        back();
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
        mView.hideProgress();
      }
    });
  }

  public UserNricPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public UserNricContract.View onCreateView() {
    return UserNricFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
    if (mMemberDTO != null) {
      mView.bindNric(mMemberDTO);
    }
    if (needUpLoadImg != 1) {
      mView.hideUploadImg();
    }

  }

  @Override
  public void uploadNricImage(Uri mSelectedUri, String mRealImageFilePath, final boolean b) {
    mView.showProgress();
    mInteractor.uploadNricImage(mSelectedUri, mRealImageFilePath, new CommonCallback<FileDTO>(getViewContext()) {
      @Override
      public void onSuccess(FileDTO data) {
        super.onSuccess(data);
        if (b) {
          mFrontImage = data;
        } else {
          mBackImage = data;
        }
        mView.bindNricUpload(data, b);
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public UserNricContract.Interactor onCreateInteractor() {
    return new UserNricInteractor(this);
  }

  public UserNricPresenter setNeedUpLoadImg(int needUpLoadImg) {
    this.needUpLoadImg = needUpLoadImg;
    return this;
  }

  public UserNricPresenter setMemberDTO(MemberDTO mMemberDTO) {
    this.mMemberDTO = mMemberDTO;
    mBackImage = mMemberDTO.getNricBackImage();
    mFrontImage = mMemberDTO.getNricFrontImage();
    return this;
  }

  public interface OnSaveNricListener {
    void onSaveNric(MemberDTO nric);
  }
}
