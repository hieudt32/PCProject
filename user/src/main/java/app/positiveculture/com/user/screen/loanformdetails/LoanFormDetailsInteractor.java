package app.positiveculture.com.user.screen.loanformdetails;

import com.gemvietnam.base.viper.Interactor;

/**
 * The LoanFormDetails interactor
 */
class LoanFormDetailsInteractor extends Interactor<LoanFormDetailsContract.Presenter>
        implements LoanFormDetailsContract.Interactor {

  LoanFormDetailsInteractor(LoanFormDetailsContract.Presenter presenter) {
    super(presenter);
  }
}
