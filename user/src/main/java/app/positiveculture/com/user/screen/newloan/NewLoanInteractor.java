package app.positiveculture.com.user.screen.newloan;

import com.gemvietnam.base.viper.Interactor;

/**
 * The NewLoan interactor
 */
class NewLoanInteractor extends Interactor<NewLoanContract.Presenter>
        implements NewLoanContract.Interactor {

    NewLoanInteractor(NewLoanContract.Presenter presenter) {
        super(presenter);
    }
}
