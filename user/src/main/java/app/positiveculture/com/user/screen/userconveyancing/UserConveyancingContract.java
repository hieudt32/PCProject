package app.positiveculture.com.user.screen.userconveyancing;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

/**
 * The UserConveyancing Contract
 */
interface UserConveyancingContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
    void bindBankInfo(String bank);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToLoanDetails();
  }
}



