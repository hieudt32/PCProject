package app.positiveculture.com.user.screen.loandetails;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

/**
 * The LoanDetails Contract
 */
interface LoanDetailsContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
    void bindBankDetails(String bankName, String bankReference);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void saveLoanDetails(String bank, String reference);
  }
}



