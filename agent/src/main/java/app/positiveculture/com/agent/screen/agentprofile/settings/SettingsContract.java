package app.positiveculture.com.agent.screen.agentprofile.settings;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.ErrorDTO;

/**
 * The Settings Contract
 */
interface SettingsContract {

  interface Interactor extends IInteractor<Presenter> {
    void logout(CommonCallback<ErrorDTO> commonCallback);
  }


  interface View extends PresentView<Presenter> {
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToChangePassword();

    void logout();
  }
}



