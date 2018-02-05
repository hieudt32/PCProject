package app.positiveculture.com.user.screen.userfullcontact;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * The UserFullContact Contract
 */
interface UserFullContactContract {

	interface Interactor extends IInteractor<Presenter> {
    void rejectOTP(String s, CommonCallback<OtpDTO> commonCallback);
  }

	interface View extends PresentView<Presenter> {
		void bindView(OtpDTO mOTP, TypeProcess mTypeProgress);
	}

	interface Presenter extends IPresenter<View, Interactor> {
		void viewDisclaimer();

		void rejectOTP();

		void backToOTP();
	}
}



