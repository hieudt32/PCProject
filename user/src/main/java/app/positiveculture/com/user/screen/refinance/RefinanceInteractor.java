package app.positiveculture.com.user.screen.refinance;

import com.gemvietnam.base.viper.Interactor;

/**
 * The Refinance interactor
 */
class RefinanceInteractor extends Interactor<RefinanceContract.Presenter>
        implements RefinanceContract.Interactor {

    RefinanceInteractor(RefinanceContract.Presenter presenter) {
        super(presenter);
    }
}
