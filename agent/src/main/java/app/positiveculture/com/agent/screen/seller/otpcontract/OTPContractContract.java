package app.positiveculture.com.agent.screen.seller.otpcontract;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;

/**
 * The VerifyOTP Contract
 */
interface OTPContractContract {

  interface Interactor extends IInteractor<Presenter> {
    void sendOutOTP(String idOTP, CommonCallback<OtpDTO> commonCallback);

    void deleteOTP(String idOTP, CommonCallback<ResponseDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void bindDataFromCreateOTP(OtpDTO mOtpDTo);

    void bindViewFromOTPScreen(OtpDTO mOtpDTo, TypeProcess mTypeProcess);

    void binViewFromCreateOTP();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToVerifySigning();

    void backToHome();

    void sendOutOTP();

    void goToCreateOTP();

    void goToCompleteOffline();

    void deleteOTP();
  }
}



