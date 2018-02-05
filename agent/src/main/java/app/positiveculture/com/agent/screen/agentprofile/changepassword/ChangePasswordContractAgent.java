package app.positiveculture.com.agent.screen.agentprofile.changepassword;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.data.response.dto.Users;

/**
 * The ChangePassword Contract
 */
interface ChangePasswordContractAgent {

  interface Interactor extends IInteractor<Presenter> {
    void saveNewPassword(String newPassword, CommonCallback<Users> commonCallback);
  }

  interface View extends PresentView<Presenter> {
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void saveNewPassword(String newPassword);
  }
}



