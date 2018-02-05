package app.positiveculture.com.agent.screen.clientlist.clientprofile;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.agent.screen.properties.PropertiesPagerAdapter;
import app.positiveculture.com.data.response.dto.MemberDTO;

/**
 * The ClientProfile Contract
 */
interface ClientProfileContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
    void setupData(MemberDTO client);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToClientDetails();

    PropertiesPagerAdapter setupPager();
  }
}



