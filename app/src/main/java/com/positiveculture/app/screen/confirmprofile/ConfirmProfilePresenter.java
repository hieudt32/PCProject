package com.positiveculture.app.screen.confirmprofile;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.positiveculture.app.screen.verifymobilenumber.VerifyMobileNumberPresenter;


import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.CEAProfileDTO;
import app.positiveculture.com.data.response.dto.CeaSmsDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;

/**
 * The ConfirmProfile Presenter
 */
public class ConfirmProfilePresenter extends Presenter<ConfirmProfileContract.View, ConfirmProfileContract.Interactor>
        implements ConfirmProfileContract.Presenter {

  private CEAProfileDTO mCEAProfileDTO;

  public ConfirmProfilePresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public ConfirmProfileContract.View onCreateView() {
    return ConfirmProfileFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
    mView.bindCEAProfile(mCEAProfileDTO);
  }

  @Override
  public ConfirmProfileContract.Interactor onCreateInteractor() {
    return new ConfirmProfileInteractor(this);
  }

  @Override
  public void goBack() {
    mContainerView.back();
  }

  @Override
  public void sendSMSAgent() {
    mContainerView.getBaseActivity().hideKeyboard();
    mView.showProgress();
    mInteractor.sendSMSAgent(mCEAProfileDTO.getCEANumber(), new CommonCallback<ResponseDTO>(mView.getViewContext()) {
      @Override
      public void onSuccess(ResponseDTO data) {
        super.onSuccess(data);
        mView.hideProgress();
        mView.onSendSMSAgentSuccess();
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
        mView.hideProgress();
        mView.onSendSMSError();
      }
    });
  }

  @Override
  public void gotoVerifyMobileNumber() {
    new VerifyMobileNumberPresenter(mContainerView)
            .setCEAProfileDTO(mCEAProfileDTO.getCEANumber())
            .pushView();
  }

  @Override
  public ConfirmProfilePresenter setCEAProfileDTO(CEAProfileDTO data) {
    mCEAProfileDTO = data;
    return this;
  }
}
