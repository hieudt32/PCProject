package app.positiveculture.com.user.screen.extentotp;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * The ExtentOTP Contract
 */
interface ExtentOTPContract {

	interface Interactor extends IInteractor<Presenter> {
		void extendOTP(String idOTP, String newDateCompletion, CommonCallback<OtpDTO> commonCallback);
	}

	interface View extends PresentView<Presenter> {
    void bindOtpInfo(String dateCompletion);
  }

	interface Presenter extends IPresenter<View, Interactor> {
		void extendOTP(String format);
	}
}



