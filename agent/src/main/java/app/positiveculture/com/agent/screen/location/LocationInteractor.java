package app.positiveculture.com.agent.screen.location;

import com.gemvietnam.base.viper.Interactor;

/**
 * The Location interactor
 */
class LocationInteractor extends Interactor<LocationContract.Presenter>
	implements LocationContract.Interactor {

	LocationInteractor(LocationContract.Presenter presenter) {
		super(presenter);
	}
}
