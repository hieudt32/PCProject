package com.positiveculture.app.screen.bankdetails;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.BindView;
import butterknife.OnTouch;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The BankDetails Fragment
 */
public class BankDetailsFragment extends ViewFragment<BankDetailsContract.Presenter> implements BankDetailsContract.View {

  @BindView(R2.id.bank_details_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.bank_details_description)
  TextView mDescriptionTv;
  @BindView(R2.id.bank_details_name_et)
  EditText mBankNameEt;
  @BindView(R2.id.bank_details_account_number_et)
  EditText mAccountNumberEt;
  @BindView(R2.id.bank_details_account_type_et)
  EditText mAccountTypeEt;

  @OnTouch(R2.id.bank_details_agent_ll)
  boolean doTouchLayout(){
    getBaseActivity().hideKeyboard();
    return false;
  }

  public static BankDetailsFragment getInstance() {
    return new BankDetailsFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_bank_details;
  }


  @Override
  public void initLayout() {
    super.initLayout();
    mHeaderView.getLeftTextTv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.goToMainScreenUser();
      }
    });
    mHeaderView.getRightTextTv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String bank = mBankNameEt.getText().toString();
        String accountNumber = mAccountNumberEt.getText().toString();
        String accountType = mAccountTypeEt.getText().toString();
        if (!StringUtils.isEmpty(bank) && !StringUtils.isEmpty(accountNumber) && !StringUtils.isEmpty(accountType)) {
          getBaseActivity().hideKeyboard();
          mPresenter.saveBankDetail(bank, accountNumber, accountType);
        } else {
          DialogUtils.showDialog(getViewContext(), getString(R.string.error), getString(R.string.error_messenger), new CustomDialog.OnConfirmSelected() {
            @Override
            public void onConfirmSelected() {

            }
          });
        }
      }
    });

  }
}
