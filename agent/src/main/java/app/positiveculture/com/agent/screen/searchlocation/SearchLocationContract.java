package app.positiveculture.com.agent.screen.searchlocation;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.agent.screen.location.SearchAdapter;

/**
 * The SearchLocation Contract
 */
interface SearchLocationContract {

	interface Interactor extends IInteractor<Presenter> {
	}

	interface View extends PresentView<Presenter> {
		void bindSearchs(SearchAdapter searchAdapter);
	}

	interface Presenter extends IPresenter<View, Interactor> {
    void goBack();
  }
}



