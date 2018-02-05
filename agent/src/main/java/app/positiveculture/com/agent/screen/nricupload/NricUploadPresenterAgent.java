package app.positiveculture.com.agent.screen.nricupload;

import android.net.Uri;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.PhotoDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The NricUpload Presenter
 */
public class NricUploadPresenterAgent extends Presenter<NricUploadContractAgent.View, NricUploadContractAgent.Interactor>
        implements NricUploadContractAgent.Presenter {

  private OnSaveNricUpload mOnSaveNricUpload;
  private String mNricNumber;
  private FileDTO mNricFrontImage;
  private FileDTO mNircBackImage;

  public NricUploadPresenterAgent setmOnSaveNricUpload(OnSaveNricUpload mOnSaveNricUpload) {
    this.mOnSaveNricUpload = mOnSaveNricUpload;
    return this;
  }

  public NricUploadPresenterAgent(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public NricUploadContractAgent.View onCreateView() {
    return NricUploadFragmentAgent.getInstance();
  }

  @Override
  public void start() {
    if (!StringUtils.isEmpty(mNricNumber)) {
      mView.bindView(mNricNumber, mNricFrontImage, mNircBackImage);
    }
  }

  @Override
  public NricUploadContractAgent.Interactor onCreateInteractor() {
    return new NricUploadInteractorAgent(this);
  }

  @Override
  public void saveNricProfile(String nric) {
    if (mNricFrontImage == null) {
      mView.showErrorDialog(true);
      return;
    }
    if (mNircBackImage == null) {
      mView.showErrorDialog(false);
      return;
    }
    mView.showProgress();
    mInteractor.saveNricProfile(nric, mNricFrontImage.getId(), mNircBackImage.getId(), new CommonCallback<User>(getViewContext()) {
      @Override
      public void onSuccess(User data) {
        super.onSuccess(data);
        mView.hideProgress();
        if (data instanceof AgentDTO) {
          PrefWrapper.saveAgent((AgentDTO) data);
          if (mOnSaveNricUpload != null) {
            mOnSaveNricUpload.onSaveNricUploadSuccessful((AgentDTO) data);
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

  @Override
  public void uploadImage(PhotoDTO mPhoto, final boolean isFrontImage) {
    if (mPhoto == null) return;
    mView.showProgress();
    ViewUtils.rotateImage(mPhoto.getPhotoUrl());
    mInteractor.uploadNricImage(mPhoto, new CommonCallback<FileDTO>(getViewContext()) {
      @Override
      public void onSuccess(FileDTO data) {
        super.onSuccess(data);
        if (isFrontImage) {
          mNricFrontImage = data;
          mView.bindViewNricImage(mNricFrontImage, true);
        } else {
          mNircBackImage = data;
          mView.bindViewNricImage(mNircBackImage, false);
        }
        mView.hideProgress();
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
        mView.hideProgress();
      }
    });
  }

  public NricUploadPresenterAgent setNric(String nric, FileDTO mNricFrontImage, FileDTO mNircBackImage) {
    this.mNricNumber = nric;
    this.mNricFrontImage = mNricFrontImage;
    this.mNircBackImage = mNircBackImage;
    return this;
  }

  public interface OnSaveNricUpload {
    void onSaveNricUploadSuccessful(AgentDTO agentDTO);
  }
}
