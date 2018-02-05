package app.positiveculture.com.agent.screen.bankdetails;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.agent.dto.BankDetail;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.User;

/**
 * The BankDetails Contract
 */
interface BankDetailsContractAgent {

  interface Interactor extends IInteractor<Presenter> {
    void updateBankDetail(String bankName, String accountNumber, String accountType, CommonCallback<User> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void setupDataForEditProfile(BankDetail mBankDetail);
  }

  interface Presenter extends IPresenter<View, Interactor> {

    void saveEditedBankDetails(String bankName, String accountNumber, String accountType);
  }
}



