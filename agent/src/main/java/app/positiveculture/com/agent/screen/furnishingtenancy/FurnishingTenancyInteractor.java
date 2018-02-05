package app.positiveculture.com.agent.screen.furnishingtenancy;

import com.gemvietnam.base.viper.Interactor;

/**
 * The FurnishingTenancy interactor
 */
class FurnishingTenancyInteractor extends Interactor<FurnishingTenancyContract.Presenter>
        implements FurnishingTenancyContract.Interactor {

    FurnishingTenancyInteractor(FurnishingTenancyContract.Presenter presenter) {
        super(presenter);
    }
}
