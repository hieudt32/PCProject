package com.positiveculture.app.screen.confirmprofile;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.CEAProfileDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;

/**
 * The ConfirmProfile Contract
 */
interface ConfirmProfileContract {

  interface Interactor extends IInteractor<Presenter> {
    void sendSMSAgent(String ceaNumber, CommonCallback<ResponseDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void onSendSMSAgentSuccess();

    void onSendSMSError();

    void bindCEAProfile(CEAProfileDTO ceaProfileDTO);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void gotoVerifyMobileNumber();

    void goBack();

    ConfirmProfilePresenter setCEAProfileDTO(CEAProfileDTO data);

    void sendSMSAgent();
  }
}



