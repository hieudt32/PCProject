package app.positiveculture.com.agent.screen.verifyaccount;

import com.gemvietnam.base.viper.Interactor;

/**
 * The VerifyAccount interactor
 */
class VerifyAccountInteractor extends Interactor<VerifyAccountContract.Presenter>
        implements VerifyAccountContract.Interactor {

  VerifyAccountInteractor(VerifyAccountContract.Presenter presenter) {
    super(presenter);
  }
}
