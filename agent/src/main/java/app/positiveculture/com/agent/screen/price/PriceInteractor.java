package app.positiveculture.com.agent.screen.price;

import com.gemvietnam.base.viper.Interactor;

/**
 * The Price interactor
 */
class PriceInteractor extends Interactor<PriceContract.Presenter>
        implements PriceContract.Interactor {

  PriceInteractor(PriceContract.Presenter presenter) {
    super(presenter);
  }
}
