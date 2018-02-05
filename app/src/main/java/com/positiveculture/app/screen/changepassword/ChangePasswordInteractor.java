package com.positiveculture.app.screen.changepassword;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.data.response.dto.Users;

/**
 * The ChangePassword interactor
 */
class ChangePasswordInteractor extends Interactor<ChangePasswordContract.Presenter>
        implements ChangePasswordContract.Interactor {

  ChangePasswordInteractor(ChangePasswordContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void changePassword(String newPass, String confirmPass, CommonCallback<Users> commonCallback) {
    ServiceBuilder.getInstance().getService().changePassword(newPass, confirmPass).enqueue(commonCallback);
  }
}
