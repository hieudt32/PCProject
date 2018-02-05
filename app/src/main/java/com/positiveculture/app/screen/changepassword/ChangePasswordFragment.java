package com.positiveculture.app.screen.changepassword;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;
import com.positiveculture.app.R;
import com.positiveculture.app.R2;

import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The ChangePassword Fragment
 */
public class ChangePasswordFragment extends ViewFragment<ChangePasswordContract.Presenter> implements ChangePasswordContract.View {

  @BindView(R2.id.change_password_cb)
  CustomButton mChangePasswordCb;
  @BindView(R2.id.new_password_et)
  EditText mNewPasswordEt;
  @BindView(R2.id.confirm_password_et)
  EditText mConfirmPasswordEt;

  @OnClick(R2.id.right_text_tv)
  void doSkipClick() {
    mPresenter.goToSetupProfileScreen();
  }

  public static ChangePasswordFragment getInstance() {
    return new ChangePasswordFragment();
  }

  @Override
  public void initLayout() {
    super.initLayout();

    mNewPasswordEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 7 && mConfirmPasswordEt.getText().toString().length() > 7
                && s.toString().compareTo(mConfirmPasswordEt.getText().toString()) == 0) {
          mChangePasswordCb.statusEnableButton();
        } else {
          mChangePasswordCb.statusDisabledButton();
        }
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });

    mConfirmPasswordEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 7 && mNewPasswordEt.getText().toString().length() > 7
                && s.toString().compareTo(mNewPasswordEt.getText().toString()) == 0) {
          mChangePasswordCb.statusEnableButton();
        } else {
          mChangePasswordCb.statusDisabledButton();
        }
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_change_password;

  }

  @OnClick(R2.id.change_password_cb)
  void onChangePassword() {
    String newPass = mNewPasswordEt.getText().toString();
    String confirmPass = mConfirmPasswordEt.getText().toString();
    if (!mChangePasswordCb.isEnabled()) return;
    if (!StringUtils.isEmpty(newPass) && !StringUtils.isEmpty(confirmPass)) {

      if (confirmPass.compareTo(newPass) == 0) {
        mChangePasswordCb.statusLoadingButton();
        mPresenter.changePassword(newPass, confirmPass);
      } else {
        DialogUtils.showDialog(getViewContext(), getString(app.positiveculture.com.agent.R.string.error),
                getString(app.positiveculture.com.agent.R.string.error_messenger2), new CustomDialog.OnConfirmSelected() {
                  @Override
                  public void onConfirmSelected() {
                  }
                });
      }
    } else {
      DialogUtils.showDialog(getViewContext(), getString(app.positiveculture.com.agent.R.string.error),
              getString(app.positiveculture.com.agent.R.string.error_messenger), new CustomDialog.OnConfirmSelected() {
                @Override
                public void onConfirmSelected() {
                }
              });
    }
  }

  @Override
  public void onChangePasswordFailed() {
    mChangePasswordCb.statusEnableButton();
  }

  @Override
  public void onChangePasswordSuccess() {
    mPresenter.goToSetupProfileScreen();
  }
}
