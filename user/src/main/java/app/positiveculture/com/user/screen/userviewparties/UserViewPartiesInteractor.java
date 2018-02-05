package app.positiveculture.com.user.screen.userviewparties;

import com.gemvietnam.base.viper.Interactor;

/**
 * The UserViewParties interactor
 */
class UserViewPartiesInteractor extends Interactor<UserViewPartiesContract.Presenter>
        implements UserViewPartiesContract.Interactor {

  UserViewPartiesInteractor(UserViewPartiesContract.Presenter presenter) {
    super(presenter);
  }
}
