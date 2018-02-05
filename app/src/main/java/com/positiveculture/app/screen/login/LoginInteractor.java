package com.positiveculture.app.screen.login;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.User;

/**
 * The login interactor
 */
class LoginInteractor extends Interactor<LoginContract.Presenter>
        implements LoginContract.Interactor {

  LoginInteractor(LoginContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void login(String userName, String password, CommonCallback<User> commonCallback) {
    ServiceBuilder.getInstance().getService().login(userName, password).enqueue(commonCallback);
  }
}
