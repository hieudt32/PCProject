package com.positiveculture.app.screen.login;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
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
 * The login Fragment
 */
public class LoginFragment extends ViewFragment<LoginContract.Presenter> implements LoginContract.View, CustomHeaderView.OnHeaderEventListener {

  @BindView(R2.id.login_cb)
  CustomButton mLoginCb;
  @BindView(R2.id.login_user_et)
  EditText mUserNameEt;
  @BindView(R2.id.login_password_et)
  EditText mPasswordEt;
  @BindView(R2.id.forgot_password_tv)
  TextView mForgotPassword;
  @BindView(R2.id.login_header)
  CustomHeaderView mHeader;

  public static LoginFragment getInstance() {
    return new LoginFragment();
  }

  @Override
  public void initLayout() {
    super.initLayout();

    mHeader.setOnHeaderEventListener(this);
    mUserNameEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!StringUtils.isEmpty(s) && !StringUtils.isEmpty(mPasswordEt.getText().toString())) {
          mLoginCb.statusEnableButton();
        } else {
          mLoginCb.statusDisabledButton();
        }
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });

    mPasswordEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!StringUtils.isEmpty(s) && !StringUtils.isEmpty(mUserNameEt.getText().toString())) {
          mLoginCb.statusEnableButton();
        } else {
          mLoginCb.statusDisabledButton();
        }
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });

  }

  @OnClick(R2.id.login_cb)
  void doLogin() {

    String userName = mUserNameEt.getText().toString().trim();
    String password = mPasswordEt.getText().toString().trim();
    if (!StringUtils.isEmpty(userName) &&
            !StringUtils.isEmpty(password)) {
      mLoginCb.statusLoadingButton();

      mPresenter.login(userName, password);

    }
  }

  @OnClick(R2.id.forgot_password_tv)
  void doForgotPassword() {
    mPresenter.goToForgotPassword();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_login;
  }

  @Override
  public void onLeftIconClick() {
    mPresenter.goBack();
  }

  @Override
  public void onRightIconClick() {

  }

  @Override
  public void onDisplay() {
    super.onDisplay();

  }

  @Override
  public void onLoginFailed() {
    getBaseActivity().hideKeyboard();
    mLoginCb.statusEnableButton();
  }

  @Override
  public void changeStatusBt() {
    mLoginCb.statusSuccessButton();
  }

  @Override
  public void checkSaveEmailAndPass() {
    if (!StringUtils.isEmpty(mUserNameEt.getText()) && !StringUtils.isEmpty(mPasswordEt.getText())) {
      mLoginCb.statusEnableButton();
    }
  }

  @Override
  public void disableLoginButton() {
    mLoginCb.statusDisabledButton();
    mUserNameEt.setEnabled(false);
    mPasswordEt.setEnabled(false);
    mHeader.getLeftIconIv().setEnabled(false);
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        mLoginCb.statusEnableButton();
        mUserNameEt.setEnabled(true);
        mPasswordEt.setEnabled(true);
        mHeader.getLeftIconIv().setEnabled(true);
        mUserNameEt.requestFocus();
      }
    }, 60000);
  }
}
