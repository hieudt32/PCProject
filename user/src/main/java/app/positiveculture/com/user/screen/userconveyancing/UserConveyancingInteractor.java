package app.positiveculture.com.user.screen.userconveyancing;

import com.gemvietnam.base.viper.Interactor;

/**
 * The UserConveyancing interactor
 */
class UserConveyancingInteractor extends Interactor<UserConveyancingContract.Presenter>
        implements UserConveyancingContract.Interactor {

  UserConveyancingInteractor(UserConveyancingContract.Presenter presenter) {
    super(presenter);
  }
}
