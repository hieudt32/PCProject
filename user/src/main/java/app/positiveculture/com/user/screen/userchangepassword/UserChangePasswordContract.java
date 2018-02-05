package app.positiveculture.com.user.screen.userchangepassword;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.data.response.dto.Users;

/**
 * The UserChangePassword Contract
 */
interface UserChangePasswordContract {

  interface Interactor extends IInteractor<Presenter> {
    void changePassword(String s, CommonCallback<Users> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void showToastChangePasswordStatus(boolean status);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void changePassword(String s);
  }
}



