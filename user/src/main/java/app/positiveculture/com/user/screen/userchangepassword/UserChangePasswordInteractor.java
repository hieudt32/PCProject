package app.positiveculture.com.user.screen.userchangepassword;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.data.response.dto.Users;

/**
 * The UserChangePassword interactor
 */
class UserChangePasswordInteractor extends Interactor<UserChangePasswordContract.Presenter>
        implements UserChangePasswordContract.Interactor {

  UserChangePasswordInteractor(UserChangePasswordContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void changePassword(String s, CommonCallback<Users> commonCallback) {
    ServiceBuilder.getInstance().getService().changePassword(s,s).enqueue(commonCallback);
  }
}
