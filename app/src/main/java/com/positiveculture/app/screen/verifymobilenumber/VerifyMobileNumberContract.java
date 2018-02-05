package com.positiveculture.app.screen.verifymobilenumber;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.CeaSmsDTO;
import app.positiveculture.com.data.response.dto.MemberVerifySmsDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The VerifyMobileNumber Contract
 */
interface VerifyMobileNumberContract {

  interface Interactor extends IInteractor<Presenter> {
    void checkSMSAgent(String mCeaNumber, String code, CommonCallback<CeaSmsDTO> commonCallback);

    void checkSMSMember(String code, CommonCallback<MemberVerifySmsDTO> commonCallback);

    void resendOneTimePinAgent(String ceaNumber, CommonCallback<ResponseDTO> commonCallback);

    void sendSMSMember(CommonCallback<MemberVerifySmsDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void onCheckSmsSuccess(String smsToken);

    void onResendOnTimePinSuccess();

    void showResendRequestDialog();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToCreatePassword(String smsToken);

    VerifyMobileNumberPresenter setCEAProfileDTO(String data);

    void goBack();

    void checkSMSAgent(String code);

    void resendOneTimePin();
  }
}



