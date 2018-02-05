package app.positiveculture.com.agent.screen.seller.congratulation;

import com.gemvietnam.base.viper.Interactor;

/**
 * The Congratulation interactor
 */
class CongratulationInteractor extends Interactor<CongratulationContract.Presenter>
        implements CongratulationContract.Interactor {

  CongratulationInteractor(CongratulationContract.Presenter presenter) {
    super(presenter);
  }
}
