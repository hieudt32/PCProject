package com.positiveculture.app.screen.forgotpassword;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;
import com.google.gson.JsonElement;

import app.positiveculture.com.data.callback.CommonCallback;

/**
 * The ForgotPassword Contract
 */
interface ForgotPasswordContract {

  interface Interactor extends IInteractor<Presenter> {
    void forgotPassword(String email, CommonCallback<JsonElement> commonCallback);

  }

  interface View extends PresentView<Presenter> {
    void sendMailSuccess();

    void onSendMailFail();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goBack();

    void sendPassword(String email);
  }
}



