package com.positiveculture.app.screen.verifyemail;

import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;
import com.positiveculture.app.R;
import com.positiveculture.app.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * The VerifyEmail Fragment
 */
public class VerifyEmailFragment extends ViewFragment<VerifyEmailContract.Presenter> implements VerifyEmailContract.View {

  @BindView(R2.id.verify_email_tv)
  TextView mMessageTv;

  public static VerifyEmailFragment getInstance() {
    return new VerifyEmailFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_verify_email;
  }

  @OnClick(R2.id.ok_tv)
  void goToUpdateNric() {
    mPresenter.goToUpdateNric();
  }

  @Override
  public void setupData(String userEmail) {
    if (!StringUtils.isEmailValid(userEmail)) return;
    StringBuilder builder = new StringBuilder();
    builder.append(userEmail.charAt(0));
    int pos = userEmail.indexOf("@");
    for (int i = 1; i < pos; i++) {
      builder.append("*");
    }
    builder.append("@");
    builder.append(userEmail.charAt(pos + 1));
    int posDot = userEmail.indexOf(".");
    for (int i = pos + 1; i < posDot; i++) {
      builder.append("*");
    }
    for (int i = posDot; i < userEmail.length(); i++) {
      builder.append(userEmail.charAt(i));
    }
    final String message = getString(R.string.verify_email_des_1) + " " + builder.toString() + " " + getString(R.string.verify_email_des_2);
    mMessageTv.setText(message);
  }
}
