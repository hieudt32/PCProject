package app.positiveculture.com.agent.screen.seller.payment;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

/**
 * The Payment Presenter
 */
public class PaymentPresenter extends Presenter<PaymentContract.View, PaymentContract.Interactor>
        implements PaymentContract.Presenter {

  public PaymentPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public PaymentContract.View onCreateView() {
    return PaymentFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public PaymentContract.Interactor onCreateInteractor() {
    return new PaymentInteractor(this);
  }
}
