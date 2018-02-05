package com.positiveculture.app.screen.createpassword;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.data.response.dto.User;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;
import customview.CustomButton;
import dialog.CustomDialog;

/**
 * The CreatePassword Fragment
 */
public class CreatePasswordFragment extends ViewFragment<CreatePasswordContract.Presenter> implements CreatePasswordContract.View{

  @BindView(R2.id.email_et)
  EditText mEmailEt;
  @BindView(R2.id.password_et)
  EditText mPasswordEt;
  @BindView(R2.id.create_cb)
  CustomButton mCreateCb;

  @OnTouch(R2.id.create_password_layout)
  boolean doTouchLayout() {
    getBaseActivity().hideKeyboard();
    return false;
  }

  public static CreatePasswordFragment getInstance() {
    return new CreatePasswordFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_create_password;
  }

  @Override
  public void initLayout() {
    super.initLayout();

    mEmailEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!StringUtils.isEmpty(s) && StringUtils.isPasswordValid(mPasswordEt.getText().toString())) {
          mCreateCb.statusEnableButton();
        } else {
          mCreateCb.statusDisabledButton();
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
        if (StringUtils.isPasswordValid(s.toString()) && !StringUtils.isEmpty(mEmailEt.getText().toString())) {
          mCreateCb.statusEnableButton();
        } else {
          mCreateCb.statusDisabledButton();
        }
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });

  }

  @Override
  public void onCreatePasswordSuccessful(User data) {
    mCreateCb.statusSuccessButton();
    mPresenter.goToConfirmEmail(mEmailEt.getText().toString().trim());
  }

  @OnClick(R2.id.left_icon_iv)
  void back() {
    mPresenter.back();
  }

  @Override
  public void onCreatePasswordFailed() {
    mCreateCb.statusEnableButton();
  }

  @OnClick(R2.id.create_cb)
  void onCreatePassword() {

    String email = mEmailEt.getText().toString().trim();
    String password = mPasswordEt.getText().toString();

    if (!StringUtils.isEmailValid(mEmailEt.getText().toString().trim())) {
      utils.DialogUtils.showDialog(
              getViewContext(),
              getString(app.positiveculture.com.data.R.string.invalid_email_title),
              getString(app.positiveculture.com.data.R.string.invalid_email_msg),
              new CustomDialog.OnConfirmSelected() {
                @Override
                public void onConfirmSelected() {
                  mEmailEt.requestFocus();
                  getBaseActivity().forceShowKeyboard();
                }
              }
      );
    } else {
      mCreateCb.statusLoadingButton();
      mPresenter.changePassword(email, password);
    }
  }
}
