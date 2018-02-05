package com.positiveculture.app.screen.login;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.User;

/**
 * The login Contract
 */
interface LoginContract {

  interface Interactor extends IInteractor<Presenter> {
    void login(String userName, String password, CommonCallback<User> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void changeStatusBt();

    void onLoginFailed();

    void checkSaveEmailAndPass();

    void disableLoginButton();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToChangePassword();

    void goToForgotPassword();

    void goBack();

    void login(String userName, String password);
  }
}



