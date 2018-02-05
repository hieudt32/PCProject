package app.positiveculture.com.user.screen.loan;

import com.gemvietnam.base.viper.Interactor;

/**
 * The Loan interactor
 */
class LoanInteractor extends Interactor<LoanContract.Presenter>
        implements LoanContract.Interactor {

    LoanInteractor(LoanContract.Presenter presenter) {
        super(presenter);
    }
}
