package com.positiveculture.app.screen.createpassword;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.response.dto.CeaSmsDTO;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.RegisterAgentDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The CreatePassword Contract
 */
interface CreatePasswordContract {

  interface Interactor extends IInteractor<Presenter> {

    void createPassword(RegisterAgentDTO registerAgentDTO, CommonCallback<User> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void onCreatePasswordSuccessful(User data);

    void onCreatePasswordFailed();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToNric();

    CreatePasswordPresenter setSmsToken(String data);

    void changePassword(String email, String password);

    void goToConfirmEmail(String email);
  }
}



