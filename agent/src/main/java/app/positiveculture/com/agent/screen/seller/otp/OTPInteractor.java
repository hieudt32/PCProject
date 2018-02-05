package app.positiveculture.com.agent.screen.seller.otp;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * The SignOTP interactor
 */
class OTPInteractor extends Interactor<OTPContract.Presenter>
        implements OTPContract.Interactor {

  OTPInteractor(OTPContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void sendOutOTP(String idOTP, CommonCallback<OtpDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().sendOutOTP(idOTP).enqueue(commonCallback);
  }
}
