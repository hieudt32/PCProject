package com.positiveculture.app.screen.verifymobilenumber;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.positiveculture.app.R;
import com.positiveculture.app.R2;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.data.response.dto.CeaSmsDTO;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The VerifyMobileNumber Fragment
 */
public class VerifyMobileNumberFragment extends ViewFragment<VerifyMobileNumberContract.Presenter> implements VerifyMobileNumberContract.View {

  @BindView(R2.id.user_number_1_et)
  EditText mUserNumber1Et;
  @BindView(R2.id.user_number_2_et)
  EditText mUserNumber2Et;
  @BindView(R2.id.user_number_3_et)
  EditText mUserNumber3Et;
  @BindView(R2.id.user_number_4_et)
  EditText mUserNumber4Et;
  @BindView(R2.id.user_number_5_et)
  EditText mUserNumber5Et;
  @BindView(R2.id.user_number_6_et)
  EditText mUserNumber6Et;
  @BindView(R2.id.custom_header_view)
  CustomHeaderView mHeader;
  List<EditText> mListNumber = new ArrayList<>();

  @OnClick(R2.id.resend_pin)
  void onResendOneTimePin() {
    mPresenter.resendOneTimePin();
    for (EditText et : mListNumber) {
      et.setText("");
    }
    mUserNumber1Et.requestFocus();
  }

  public static VerifyMobileNumberFragment getInstance() {
    return new VerifyMobileNumberFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_verify_mobile_number;
  }

  @Override
  public void initLayout() {
    super.initLayout();

    mListNumber.add(mUserNumber1Et);
    mListNumber.add(mUserNumber2Et);
    mListNumber.add(mUserNumber3Et);
    mListNumber.add(mUserNumber4Et);
    mListNumber.add(mUserNumber5Et);
    mListNumber.add(mUserNumber6Et);

    for (int i = 0; i < mListNumber.size() - 1; i++) {
      final int position = i;
      mListNumber.get(i).addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
          if (s.length() == 1) {
            mListNumber.get(position + 1).requestFocus();
          }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
      });
    }

    mUserNumber6Et.setImeActionLabel(getString(R.string.bt_submit), KeyEvent.KEYCODE_ENTER);
    mUserNumber6Et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
        if (keyEvent != null && keyEvent.getAction() != KeyEvent.ACTION_DOWN) {
          return false;
        }

        String code = mUserNumber1Et.getText().toString()
                + mUserNumber2Et.getText().toString()
                + mUserNumber3Et.getText().toString()
                + mUserNumber4Et.getText().toString()
                + mUserNumber5Et.getText().toString()
                + mUserNumber6Et.getText().toString();
        mPresenter.checkSMSAgent(code);

        return true;
      }

    });

    mHeader.getLeftIconIv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.goBack();
      }
    });
  }

  @Override
  public void onCheckSmsSuccess(String smsToken) {
    mPresenter.goToCreatePassword(smsToken);
  }

  @Override
  public void onResendOnTimePinSuccess() {
    hideProgress();
    DialogUtils.showDialog(
            getViewContext(),
            getString(R.string.resend_one_time_pin),
            getString(R.string.resend_one_time_pin_messenger),
            new CustomDialog.OnConfirmSelected() {
              @Override
              public void onConfirmSelected() {
              }
            }
    );
  }

  @Override
  public void showResendRequestDialog() {
    DialogUtils.showDialog(
            getViewContext(),
            getString(R.string.otp_expired_title),
            getString(R.string.otp_expired_message),
            new CustomDialog.OnConfirmSelected() {
              @Override
              public void onConfirmSelected() {

              }
            }
    );
  }

}
