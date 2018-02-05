package com.positiveculture.app.screen.forgotpassword;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;
import com.positiveculture.app.R;
import com.positiveculture.app.R2;

import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;
import customview.CustomHeaderView;


/**
 * The ForgotPassword Fragment
 */
public class ForgotPasswordFragment extends ViewFragment<ForgotPasswordContract.Presenter> implements ForgotPasswordContract.View {
  @BindView(R2.id.email_reset_password_et)
  EditText mEmailInput;
  @BindView(R2.id.messenger_forgot_password_tv)
  TextView mMessenger;
  @BindView(R2.id.forgot_send_cb)
  CustomButton mSend;
  @BindView(R2.id.forgot_password_hd)
  CustomHeaderView mHeader;

  public static ForgotPasswordFragment getInstance() {
    return new ForgotPasswordFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_forgot_password;
  }

  @Override
  public void initLayout() {
    super.initLayout();

    mHeader.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.goBack();
      }

      @Override
      public void onRightIconClick() {

      }
    });
    mEmailInput.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (StringUtils.isEmpty(s.toString().trim()) || !StringUtils.isEmailValid(s.toString().trim())) {
          mSend.statusDisabledButton();
        } else {
          mSend.statusEnableButton();
        }
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });
  }

  @OnClick(R2.id.forgot_send_cb)
  void doSend() {
    if (mSend.isEnabled()) {
      getBaseActivity().hideKeyboard();
      mSend.statusDisabledButton();
      mPresenter.sendPassword(mEmailInput.getText().toString().trim());
      mSend.statusLoadingButton();
      mHeader.getLeftIconIv().setEnabled(false);
    }
  }

  @Override
  public void sendMailSuccess() {
    mSend.setTextButton(getString(R.string.sent_button));
    String s = getViewContext().getString(R.string.messenger_forgot_password) + mEmailInput.getText().toString()
            + getViewContext().getString(R.string.messenger_forgot_password2);
    mMessenger.setText(s);
    mMessenger.setVisibility(View.VISIBLE);
    mSend.statusDisabledButton();
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        mPresenter.goBack();
      }
    }, 3000);
  }

  @Override
  public void onSendMailFail() {
    mSend.statusEnableButton();
  }
}
