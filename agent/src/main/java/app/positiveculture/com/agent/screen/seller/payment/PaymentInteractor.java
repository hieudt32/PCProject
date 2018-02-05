package app.positiveculture.com.agent.screen.seller.payment;

import com.gemvietnam.base.viper.Interactor;

/**
 * The Payment interactor
 */
class PaymentInteractor extends Interactor<PaymentContract.Presenter>
        implements PaymentContract.Interactor {

  PaymentInteractor(PaymentContract.Presenter presenter) {
    super(presenter);
  }
}
