package app.positiveculture.com.user.screen.loandetails;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.BindView;
import customview.CustomHeaderView;

/**
 * The LoanDetails Fragment
 */
public class LoanDetailsFragment extends ViewFragment<LoanDetailsContract.Presenter> implements LoanDetailsContract.View {

  @BindView(R2.id.loan_details_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.loan_details_bank_et)
  EditText mBankEt;
  @BindView(R2.id.loan_details_bank_reference)
  EditText mReferenceEt;

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_loan_details;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
    setupListeners();
  }

  private void setupListeners() {
    mHeaderView.getLeftIconIv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.back();
      }
    });

    mHeaderView.getRightTextTv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.saveLoanDetails(mBankEt.getText().toString(), mReferenceEt.getText().toString());
      }
    });

    mBankEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (!StringUtils.isEmpty(mBankEt.getText().toString().trim())
                && !StringUtils.isEmpty(mReferenceEt.getText().toString().trim())) {
          mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
        } else {
          mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
        }
      }
    });

    mReferenceEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (!StringUtils.isEmpty(mBankEt.getText().toString().trim())
                && !StringUtils.isEmpty(mReferenceEt.getText().toString().trim())) {
          mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
        } else {
          mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
        }
      }
    });
  }

  public static LoanDetailsFragment getInstance() {
    return new LoanDetailsFragment();
  }

  @Override
  public void bindBankDetails(String bankName, String bankReference) {
    mBankEt.setText(bankName);
    mReferenceEt.setText(bankReference);
  }
}
