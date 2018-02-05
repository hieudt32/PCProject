package app.positiveculture.com.user.screen.searchaddress;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.agent.screen.location.SearchAdapter;
import app.positiveculture.com.data.response.dto.Address;

/**
 * The Search_Address Contract
 */
interface SearchAddressContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
    void bindListLocation(SearchAdapter searchAdapter);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void searchLocation(String query);
  }
}



