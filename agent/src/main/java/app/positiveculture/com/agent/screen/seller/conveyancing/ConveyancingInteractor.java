package app.positiveculture.com.agent.screen.seller.conveyancing;

import com.gemvietnam.base.viper.Interactor;

/**
 * The Conveyancing interactor
 */
class ConveyancingInteractor extends Interactor<ConveyancingContract.Presenter>
        implements ConveyancingContract.Interactor {

  ConveyancingInteractor(ConveyancingContract.Presenter presenter) {
    super(presenter);
  }
}
