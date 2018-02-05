package app.positiveculture.com.user.screen.userotp;

import com.gemvietnam.base.viper.Interactor;

/**
 * The UserOTP interactor
 */
class UserOTPInteractor extends Interactor<UserOTPContract.Presenter>
	implements UserOTPContract.Interactor {

	UserOTPInteractor(UserOTPContract.Presenter presenter) {
		super(presenter);
	}
}
