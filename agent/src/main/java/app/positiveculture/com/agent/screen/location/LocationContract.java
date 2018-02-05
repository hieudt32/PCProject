package app.positiveculture.com.agent.screen.location;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

/**
 * The Location Contract
 */
interface LocationContract {

	interface Interactor extends IInteractor<Presenter> {
	}

	interface View extends PresentView<Presenter> {
		void bindListLocation(SearchAdapter searchAdapter);

		void enableAddBt();
  }

	interface Presenter extends IPresenter<View, Interactor> {
		void showSearchScreen();

		void showLocationResult();

		void showScreenAddr();

		void goBack();

		void searchLocation(String location);
	}
}



