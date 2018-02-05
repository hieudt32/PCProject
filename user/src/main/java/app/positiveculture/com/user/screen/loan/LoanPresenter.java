package app.positiveculture.com.user.screen.loan;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.user.Constant;
import app.positiveculture.com.user.screen.loanformdetails.LoanFormDetailsPresenter;
import app.positiveculture.com.user.screen.newloan.NewLoanPresenter;
import app.positiveculture.com.user.screen.refinance.RefinancePresenter;

/**
 * The Loan Presenter
 */
public class LoanPresenter extends Presenter<LoanContract.View, LoanContract.Interactor>
        implements LoanContract.Presenter {

    public LoanPresenter(ContainerView containerView) {
        super(containerView);
    }

    @Override
    public LoanContract.View onCreateView() {
        return LoanFragment.getInstance();
    }

    @Override
    public void start() {
        // Start getting data here
    }

    @Override
    public LoanContract.Interactor onCreateInteractor() {
        return new LoanInteractor(this);
    }

    @Override
    public void newLoan() {
        new LoanFormDetailsPresenter(mContainerView)
                .setLoanType(Constant.LoanType.NEW_LOAN)
                .pushView();
    }

    @Override
    public void newRefinance() {
        new LoanFormDetailsPresenter(mContainerView)
                .setLoanType(Constant.LoanType.REFINANCE)
                .pushView();
    }

}
