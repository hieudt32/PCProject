package com.positiveculture.app.screen.changepassword;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.data.response.dto.Users;

/**
 * The ChangePassword Contract
 */
interface ChangePasswordContract {

  interface Interactor extends IInteractor<Presenter> {
    void changePassword(String newPass, String confirmPass, CommonCallback<Users> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void onChangePasswordSuccess();

    void onChangePasswordFailed();
  }

  interface Presenter extends IPresenter<View, Interactor> {

    void changePassword(String newPass, String confirmPass);

    void goToSetupProfileScreen();
  }
}



