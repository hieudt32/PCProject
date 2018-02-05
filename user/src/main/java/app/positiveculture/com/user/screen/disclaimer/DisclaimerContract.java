package app.positiveculture.com.user.screen.disclaimer;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * The Disclaimer Contract
 */
interface DisclaimerContract {

	interface Interactor extends IInteractor<Presenter> {
	}

	interface View extends PresentView<Presenter> {
    void bindView(OtpDTO mOTP);
  }

	interface Presenter extends IPresenter<View, Interactor> {
		void viewSignOTP();

    boolean getFromFullContact();

		void backTwice();

		void postEvent(OtpDTO mOTP);
	}
}



