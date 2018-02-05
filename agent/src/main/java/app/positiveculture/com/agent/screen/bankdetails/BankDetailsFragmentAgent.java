package app.positiveculture.com.agent.screen.bankdetails;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.dto.BankDetail;
import butterknife.BindView;
import butterknife.OnTouch;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The BankDetails Fragment
 */
public class BankDetailsFragmentAgent extends ViewFragment<BankDetailsContractAgent.Presenter> implements BankDetailsContractAgent.View {

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
  String mBankName;
  String mAccountNumber;
  String mAccountType;

  @OnTouch(R2.id.bank_details_agent_ll)
  boolean doTouchLayout() {
    getBaseActivity().hideKeyboard();
    return false;
  }

  public static BankDetailsFragmentAgent getInstance() {
    return new BankDetailsFragmentAgent();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_bank_details;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
    mBankNameEt.addTextChangedListener(createListener());
    mAccountNumberEt.addTextChangedListener(createListener());
    mAccountTypeEt.addTextChangedListener(createListener());
  }

  private TextWatcher createListener() {
    return new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (mBankName != null && mAccountType != null && mAccountNumber != null) {
          if (mBankNameEt.getText().toString().trim().compareTo(mBankName) != 0 ||
                  mAccountNumberEt.getText().toString().trim().compareTo(mAccountNumber) != 0
                  || mAccountTypeEt.getText().toString().trim().compareTo(mAccountType) != 0) {
            mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
          } else {
            mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
          }
        } else {
          if (!mBankNameEt.getText().toString().trim().equals("") && !mAccountNumberEt.getText().toString().trim().equals("") &&
                  !mAccountTypeEt.getText().toString().trim().equals("")) {
            mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
          } else {
            mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
          }
        }
      }
    };
  }

  @Override
  public void setupDataForEditProfile(BankDetail bankDetail) {
    mHeaderView.getLeftTextTv().setVisibility(View.GONE);
    mHeaderView.getRightTextTv().setVisibility(View.GONE);
    mHeaderView.getLeftIconIv().setVisibility(View.VISIBLE);
    mHeaderView.getLeftIconIv().setImageResource(R.drawable.ic_back);
    mHeaderView.getLeftIconIv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.back();
      }
    });
    mHeaderView.getRightTextTv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String bankName = mBankNameEt.getText().toString();
        String accountNumber = mAccountNumberEt.getText().toString();
        String accountType = mAccountTypeEt.getText().toString();
        if (!StringUtils.isEmpty(bankName) && !StringUtils.isEmpty(accountNumber) && !StringUtils.isEmpty(accountType)) {
          mPresenter.saveEditedBankDetails(bankName, accountNumber, accountType);
        }
      }
    });

    mDescriptionTv.setText(getString(R.string.bank_details_desc));
    mBankNameEt.setHint(getString(R.string.bank));
    mAccountNumberEt.setHint(getString(R.string.accout_no));
    mAccountTypeEt.setHint(getString(R.string.account_type));
    mBankNameEt.setText(bankDetail.getBankName());
    mAccountNumberEt.setText(bankDetail.getBankAcountNo());
    mAccountTypeEt.setText(bankDetail.getAcountType());
    this.mBankName = bankDetail.getBankName();
    this.mAccountNumber = bankDetail.getBankAcountNo();
    this.mAccountType = bankDetail.getAcountType();
  }
}
