package app.positiveculture.com.agent.screen.seller.otpcontract;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;

/**
 * The VerifyOTP interactor
 */
class OTPContractInteractor extends Interactor<OTPContractContract.Presenter>
        implements OTPContractContract.Interactor {

  OTPContractInteractor(OTPContractContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void sendOutOTP(String idOTP, CommonCallback<OtpDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().sendOutOTP(idOTP).enqueue(commonCallback);
  }

  @Override
  public void deleteOTP(String idOTP, CommonCallback<ResponseDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().sellerAgentDeleteOTP(idOTP).enqueue(commonCallback);
  }
}
