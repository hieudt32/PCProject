package app.positiveculture.com.agent.screen.properties;

import android.support.v4.app.Fragment;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.PropertiesCountDTO;
import app.positiveculture.com.data.response.dto.AgentDTO;


/**
 * The Properties Contract
 */
interface PropertiesContract {

  interface Interactor extends IInteractor<Presenter> {
    void checkListProperties(CommonCallback<PropertiesCountDTO> commonCallback);

    void getProfileAgent(CommonCallback<AgentDTO> verified);
  }

  interface View extends PresentView<Presenter> {
    void listingCreated();

    void showPropertiesEmptyList();

    void setupTabLayout();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    Fragment setPageFragment(int i);

    void goToSelectOwners();

    PropertiesPagerAdapter showProperties();

    PropertiesPagerAdapter showPayingClient();

    void goToSearchProperty();
  }
}



