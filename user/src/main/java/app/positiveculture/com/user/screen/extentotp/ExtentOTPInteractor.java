package app.positiveculture.com.user.screen.extentotp;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * The ExtentOTP interactor
 */
class ExtentOTPInteractor extends Interactor<ExtentOTPContract.Presenter>
        implements ExtentOTPContract.Interactor {

  ExtentOTPInteractor(ExtentOTPContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void extendOTP(String idOTP, String newDateCompletion, CommonCallback<OtpDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().buyerExtendOTP(idOTP, newDateCompletion).enqueue(commonCallback);
  }
}
