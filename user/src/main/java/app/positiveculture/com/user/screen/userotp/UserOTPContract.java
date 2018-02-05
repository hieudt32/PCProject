package app.positiveculture.com.user.screen.userotp;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * The UserOTP Contract
 */
interface UserOTPContract {

	interface Interactor extends IInteractor<Presenter> {
	}

	interface View extends PresentView<Presenter> {
    void bindView(OtpDTO mOTP, TypeProcess mTypeProcess);
  }

	interface Presenter extends IPresenter<View, Interactor> {
		void showDisclaimer();

		void viewFullContact();

		void extentOTP();

    void goBack();

		void goToUserViewParties();
	}
}



