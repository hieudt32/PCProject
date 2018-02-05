package com.positiveculture.app.screen.verifymobilenumber;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.CeaSmsDTO;
import app.positiveculture.com.data.response.dto.MemberVerifySmsDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The VerifyMobileNumber interactor
 */
class VerifyMobileNumberInteractor extends Interactor<VerifyMobileNumberContract.Presenter>
        implements VerifyMobileNumberContract.Interactor {

  VerifyMobileNumberInteractor(VerifyMobileNumberContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void checkSMSAgent(String mCeaNumber, String code, CommonCallback<CeaSmsDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().checkSMSAgent(mCeaNumber, code).enqueue(commonCallback);
  }

  @Override
  public void checkSMSMember(String code, CommonCallback<MemberVerifySmsDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().checkSMSMember(code).enqueue(commonCallback);
  }

  @Override
  public void resendOneTimePinAgent(String ceaNumber, CommonCallback<ResponseDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().sendSMSAgent(ceaNumber).enqueue(commonCallback);
  }

  @Override
  public void sendSMSMember(CommonCallback<MemberVerifySmsDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().sendSMSMember().enqueue(commonCallback);
  }
}
