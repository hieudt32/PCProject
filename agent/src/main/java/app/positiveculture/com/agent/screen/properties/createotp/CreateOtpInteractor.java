package app.positiveculture.com.agent.screen.properties.createotp;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.CreateOtpDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * The CreateOtp interactor
 */
class CreateOtpInteractor extends Interactor<CreateOtpContract.Presenter>
        implements CreateOtpContract.Interactor {

  CreateOtpInteractor(CreateOtpContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void createOTP(CreateOtpDTO createOtpDTO, CommonCallback<OtpDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().createOTP(createOtpDTO).enqueue(commonCallback);
  }

  @Override
  public void updateOTP(String idOTP, CreateOtpDTO mCreateOtpDTO, CommonCallback<OtpDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().updateOTP(idOTP, mCreateOtpDTO).enqueue(commonCallback);
  }
}
