package app.positiveculture.com.user.screen.newloan;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

/**
 * The NewLoan Presenter
 */
public class NewLoanPresenter extends Presenter<NewLoanContract.View, NewLoanContract.Interactor>
        implements NewLoanContract.Presenter {

    public NewLoanPresenter(ContainerView containerView) {
        super(containerView);
    }

    @Override
    public NewLoanContract.View onCreateView() {
        return NewLoanFragment.getInstance();
    }

    @Override
    public void start() {
        // Start getting data here
    }

    @Override
    public NewLoanContract.Interactor onCreateInteractor() {
        return new NewLoanInteractor(this);
    }
}
