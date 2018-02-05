package app.positiveculture.com.user.screen.loandetails;

import com.gemvietnam.base.viper.Interactor;

/**
 * The LoanDetails interactor
 */
class LoanDetailsInteractor extends Interactor<LoanDetailsContract.Presenter>
        implements LoanDetailsContract.Interactor {

  LoanDetailsInteractor(LoanDetailsContract.Presenter presenter) {
    super(presenter);
  }
}
