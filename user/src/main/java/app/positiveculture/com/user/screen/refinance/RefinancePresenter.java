package app.positiveculture.com.user.screen.refinance;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

/**
 * The Refinance Presenter
 */
public class RefinancePresenter extends Presenter<RefinanceContract.View, RefinanceContract.Interactor>
        implements RefinanceContract.Presenter {

    public RefinancePresenter(ContainerView containerView) {
        super(containerView);
    }

    @Override
    public RefinanceContract.View onCreateView() {
        return RefinanceFragment.getInstance();
    }

    @Override
    public void start() {
        // Start getting data here
    }

    @Override
    public RefinanceContract.Interactor onCreateInteractor() {
        return new RefinanceInteractor(this);
    }
}
