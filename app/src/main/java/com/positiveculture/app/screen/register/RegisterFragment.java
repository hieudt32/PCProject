package com.positiveculture.app.screen.register;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;
import com.positiveculture.app.R;
import com.positiveculture.app.R2;

import app.positiveculture.com.data.response.dto.CEAProfileDTO;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;
import customview.CustomHeaderView;

/**
 * The Register Fragment
 */
public class RegisterFragment extends ViewFragment<RegisterContract.Presenter> implements RegisterContract.View {

  @BindView(R2.id.register_cea_number_et)
  EditText mCeaNumberEt;
  @BindView(R2.id.next_cb)
  CustomButton mNextCb;
  @BindView(R2.id.register_header)
  CustomHeaderView mHeader;

  public static RegisterFragment getInstance() {
    return new RegisterFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_register;
  }

  @Override
  public void initLayout() {
    super.initLayout();

    mCeaNumberEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!StringUtils.isEmpty(s)) {
          mNextCb.statusEnableButton();
        } else {
          mNextCb.statusDisabledButton();
        }
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });

    mHeader.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.goBack();
      }

      @Override
      public void onRightIconClick() {

      }
    });
  }

  @OnClick(R2.id.next_cb)
  void onNextSelected() {
    if (!StringUtils.isEmpty(mCeaNumberEt.getText().toString())) {
      mNextCb.statusLoadingButton();

      mPresenter.onNextSelected(mCeaNumberEt.getText().toString().trim());
    }
  }

  @Override
  public void onCheckCeaNumberSuccessful(CEAProfileDTO data) {
    mNextCb.statusSuccessButton();
    mPresenter.gotoConfirmProfile(data);
  }

  @Override
  public void onDisplay() {
    super.onDisplay();
    if (!StringUtils.isEmpty(mCeaNumberEt.getText().toString().trim())) {
      mNextCb.statusEnableButton();
    } else {
      mNextCb.statusDisabledButton();
    }
  }

  @Override
  public void onCheckCeaNumberFailed() {
    mNextCb.statusEnableButton();
  }
}
