package app.positiveculture.com.agent.screen.seller.payment;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.OnClick;

/**
 * The Payment Fragment
 */
public class PaymentFragment extends ViewFragment<PaymentContract.Presenter> implements PaymentContract.View {

  @OnClick(R2.id.left_icon_iv)
  void doBackClick() {
    mPresenter.back();
  }

  public static PaymentFragment getInstance() {
    return new PaymentFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_payment;
  }
}
