package app.positiveculture.com.agent.screen.verifyaccount;

import android.view.View;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;

/**
 * The VerifyAccount Fragment
 */
public class VerifyAccountFragment extends ViewFragment<VerifyAccountContract.Presenter> implements VerifyAccountContract.View {

  @BindView(R2.id.verify_account_content)
  TextView mContentTv;
  @BindView(R2.id.verify_account_nric_tv)
  TextView mNricTv;
  @BindView(R2.id.verify_account_email_tv)
  TextView mEmailTv;
  @BindView(R2.id.verify_account_ok_bt)
  CustomButton mOkButton;

  @OnClick(R2.id.verify_account_ok_bt)
  void doOkButton() {
    mPresenter.back();
  }

  public static VerifyAccountFragment getInstance() {
    return new VerifyAccountFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_verify_account;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mOkButton.statusEnableButton();
  }

  @Override
  public void hideNric() {
    mNricTv.setVisibility(View.GONE);
    mContentTv.setText(getString(R.string.verify_des_email));
  }

  @Override
  public void hideEmail() {
    mEmailTv.setVisibility(View.GONE);
    mContentTv.setText(getString(R.string.verify_des_nric));
  }
}
