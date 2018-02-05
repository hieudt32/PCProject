package app.positiveculture.com.user.screen.properties;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.PropertiesCountDTO;
import app.positiveculture.com.user.screen.properties.adapter.PropertiesPagerAdapter;

/**
 * The Properties Contract
 */
interface PropertiesContract {

	interface Interactor extends IInteractor<Presenter> {
		void getPropertiesCount(CommonCallback<PropertiesCountDTO> commonCallback);
	}

	interface View extends PresentView<Presenter> {
    void showEmptyProperty();

		void setupTabLayout();
	}

	interface Presenter extends IPresenter<View, Interactor> {
		PropertiesPagerAdapter getViewPageAdapter();

  }
}



