package com.positiveculture.app.screen.nricupload;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.positiveculture.app.screen.bankdetails.BankDetailsPresenter;

import app.positiveculture.com.agent.screen.main.MainPresenterAgent;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.PhotoDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The NricUpload Presenter
 */
public class NricUploadPresenter extends Presenter<NricUploadContract.View, NricUploadContract.Interactor>
        implements NricUploadContract.Presenter {

  private boolean isSetupProfile = false;
  private FileDTO mFrontImage;
  private FileDTO mBackImage;

  public NricUploadPresenter setSetupProfile(boolean setupProfile) {
    isSetupProfile = setupProfile;
    return this;
  }

  public NricUploadPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public NricUploadContract.View onCreateView() {
    return NricUploadFragment.getInstance();
  }

  @Override
  public void start() {
    if (isSetupProfile) {
      MemberDTO memberDTO = PrefWrapper.getMember();
      if (memberDTO != null) {
        mView.showUserView(memberDTO.getIdType(), memberDTO.getmIdNumber());
      }
    }
  }

  @Override
  public NricUploadContract.Interactor onCreateInteractor() {
    return new NricUploadInteractor(this);
  }

  @Override
  public void goToMainAgent() {
    new MainPresenterAgent(mContainerView).pushView();
  }

  @Override
  public void goToBankDetail() {
    new BankDetailsPresenter(mContainerView).pushView();
  }

  @Override
  public void saveNricProfile(String nric) {
    mView.getBaseActivity().hideKeyboard();
    if (mFrontImage == null) {
      mView.showErrorDialog(true);
      return;
    }
    if (mBackImage == null) {
      mView.showErrorDialog(false);
      return;
    }
    mView.showProgress();
    mInteractor.saveNricProfile(mFrontImage.getId(), mBackImage.getId(), nric, new CommonCallback<User>(getViewContext()) {
      @Override
      public void onSuccess(User data) {
        super.onSuccess(data);
        mView.hideProgress();
        if (data instanceof AgentDTO) PrefWrapper.saveAgent((AgentDTO) data);
        goToMainAgent();
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
        mView.hideProgress();
      }
    });
  }

  @Override
  public void uploadNricImage(PhotoDTO mPhoto, final boolean b) {
    if (mPhoto == null) return;
    mView.showProgress();
    ViewUtils.rotateImage(mPhoto.getPhotoUrl());
    mInteractor.uploadNricImage(mPhoto, new CommonCallback<FileDTO>(getViewContext()) {
      @Override
      public void onSuccess(FileDTO data) {
        super.onSuccess(data);
        if (b) {
          mFrontImage = data;
        } else {
          mBackImage = data;
        }
        mView.bindView(mFrontImage, mBackImage);
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public void saveUserId(String idType, String userId) {
    if (mFrontImage == null) {
      mView.showErrorDialog(true);
      return;
    }
    if (mBackImage == null) {
      mView.showErrorDialog(false);
      return;
    }
    mView.showProgress();
    mInteractor.saveUserId(idType, userId, mFrontImage.getId(), mBackImage.getId(), new CommonCallback<User>(getViewContext()) {
      @Override
      public void onSuccess(User data) {
        super.onSuccess(data);
        if (data instanceof MemberDTO) PrefWrapper.saveMember((MemberDTO) data);
        if (isSetupProfile) goToBankDetail();
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public void nextStep() {
    goToMainAgent();
  }
}
