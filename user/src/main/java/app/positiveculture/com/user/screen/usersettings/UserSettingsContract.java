package app.positiveculture.com.user.screen.usersettings;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.ErrorDTO;

/**
 * The UserSettings Contract
 */
interface UserSettingsContract {

  interface Interactor extends IInteractor<Presenter> {
    void logout(CommonCallback<ErrorDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void changePassword();

    void logout();
  }
}



