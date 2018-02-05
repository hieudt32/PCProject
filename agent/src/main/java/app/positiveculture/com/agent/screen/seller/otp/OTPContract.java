package app.positiveculture.com.agent.screen.seller.otp;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * The SignOTP Contract
 */
interface OTPContract {

  interface Interactor extends IInteractor<Presenter> {
    void sendOutOTP(String s, CommonCallback<OtpDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void bindView(OtpDTO mOTP, TypeProcess mTypeProcess);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToViewParties();

    void goToOTPContract();

    void goToVerifySigning();

    void sendOutOTP();

    void goToAssignBuyerScreen();

    void goToCompleteScreen();
  }
}



