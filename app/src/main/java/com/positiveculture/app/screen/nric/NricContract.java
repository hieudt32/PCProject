package com.positiveculture.app.screen.nric;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeID;
import app.positiveculture.com.data.response.dto.User;

/**
 * The Nric Contract
 */
interface NricContract {

  interface Interactor extends IInteractor<Presenter> {
    void updateNric(String mNricNumber, CommonCallback<User> commonCallback);

    void updateUserId(String selectedType, String userid, CommonCallback<User> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void showUserFrom(TypeID typeID, String idNumber);
  }

  interface Presenter extends IPresenter<View, Interactor> {

    void goToBankDetails();
    void goToMainAgent();

    void updateNric(String mNricNumber);

    void nextStep();

    void updateUserId(String selectedType, String userid);
  }
}



