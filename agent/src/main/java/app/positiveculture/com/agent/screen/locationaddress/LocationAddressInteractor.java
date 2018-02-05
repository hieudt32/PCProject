package app.positiveculture.com.agent.screen.locationaddress;

import com.gemvietnam.base.viper.Interactor;

/**
 * The Location_Adress interactor
 */
class LocationAddressInteractor extends Interactor<LocationAddressContract.Presenter>
	implements LocationAddressContract.Interactor {

	LocationAddressInteractor(LocationAddressContract.Presenter presenter) {
		super(presenter);
	}
}
