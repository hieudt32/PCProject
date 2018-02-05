package app.positiveculture.com.user.screen.disclaimer;

import com.gemvietnam.base.viper.Interactor;

/**
 * The Disclaimer interactor
 */
class DisclaimerInteractor extends Interactor<DisclaimerContract.Presenter>
	implements DisclaimerContract.Interactor {

	DisclaimerInteractor(DisclaimerContract.Presenter presenter) {
		super(presenter);
	}
}
