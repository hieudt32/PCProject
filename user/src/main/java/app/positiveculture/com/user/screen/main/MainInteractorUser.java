package app.positiveculture.com.user.screen.main;

import com.gemvietnam.base.viper.Interactor;

/**
 * The Main interactor
 */
class MainInteractorUser extends Interactor<MainContractUser.Presenter>
        implements MainContractUser.Interactor {

  MainInteractorUser(MainContractUser.Presenter presenter) {
    super(presenter);
  }
}
