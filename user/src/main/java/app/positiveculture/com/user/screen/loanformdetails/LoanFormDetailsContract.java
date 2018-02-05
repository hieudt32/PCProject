package app.positiveculture.com.user.screen.loanformdetails;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.response.dto.User;

/**
 * The LoanFormDetails Contract
 */
interface LoanFormDetailsContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
    void setupUI(String loanType, User user);
  }

  interface Presenter extends IPresenter<View, Interactor> {
  }
}



