package com.positiveculture.app.screen.bankdetails;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.User;

/**
 * The BankDetails Contract
 */
interface BankDetailsContract {

  interface Interactor extends IInteractor<Presenter> {
    void saveBankDetail(String bank, String accountNumber, String accountType, CommonCallback<User> commonCallback);
  }

  interface View extends PresentView<Presenter> {
  }

  interface Presenter extends IPresenter<View, Interactor> {

    void saveBankDetail(String bank, String accountNumber, String accountType);

    void goToMainScreenUser();
  }
}



