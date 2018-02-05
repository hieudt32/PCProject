package com.positiveculture.app.screen.bankdetails;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.User;

/**
 * The BankDetails interactor
 */
class BankDetailsInteractor extends Interactor<BankDetailsContract.Presenter>
        implements BankDetailsContract.Interactor {

  BankDetailsInteractor(BankDetailsContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void saveBankDetail(String bank, String accountNumber, String accountType, CommonCallback<User> commonCallback) {
    ServiceBuilder.getInstance().getService().saveBankDetail(bank,accountNumber, accountType).enqueue(commonCallback);
  }
}
