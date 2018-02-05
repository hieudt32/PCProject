package app.positiveculture.com.user.screen.loanformdetails;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.utils.CountryCode;
import app.positiveculture.com.agent.utils.NumberUtils;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.user.Constant;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.BindView;
import butterknife.OnTouch;
import customview.CustomButton;
import customview.CustomHeaderView;
import customview.CustomPropertyDetails;

/**
 * The LoanFormDetails Fragment
 */
public class LoanFormDetailsFragment extends ViewFragment<LoanFormDetailsContract.Presenter> implements LoanFormDetailsContract.View {

  @BindView(R2.id.loan_form_details_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.loan_form_details_private_residential_rb)
  RadioButton mPrivateResidentialRb;
  @BindView(R2.id.loan_form_details_hdb_rb)
  RadioButton mHdbRb;
  @BindView(R2.id.loan_form_details_construction_status_layout)
  LinearLayout mConstructionStatusLayout;
  @BindView(R2.id.loan_form_details_completed_rb)
  RadioButton mCompletedRb;
  @BindView(R2.id.loan_form_details_uncomplete_rb)
  RadioButton mUncompleteRb;
  @BindView(R2.id.loan_form_details_fixed_rb)
  RadioButton mFixedRb;
  @BindView(R2.id.loan_form_details_floating_Rb)
  RadioButton mFloatingRb;
  @BindView(R2.id.loan_form_details_both_rb)
  RadioButton mBothRb;
  @BindView(R2.id.loan_form_details_loan_amount_tv)
  TextView mLoanAmountTv;
  @BindView(R2.id.loan_form_details_loan_amount_et)
  EditText mLoanAmountEt;
  @BindView(R2.id.loan_form_details_current_financer_layout)
  LinearLayout mCurrentFinancerLayout;
  @BindView(R2.id.loan_form_details_current_financer_sp)
  Spinner mCurrentFinancerSpinner;
  @BindView(R2.id.loan_form_details_current_interest_rate_layout)
  LinearLayout mCurrentInterestRateLayout;
  @BindView(R2.id.loan_form_details_current_interest_rate_et)
  EditText mCurrentInterestRateEt;
  @BindView(R2.id.loan_form_details_loan_tenure_cpd)
  CustomPropertyDetails mTenureCpd;
  @BindView(R2.id.loan_form_details_name_et)
  EditText mNameEt;
  @BindView(R2.id.loan_form_details_phone_code_sp)
  Spinner mPhoneCodeSpinner;
  @BindView(R2.id.loan_form_details_phone_et)
  EditText mPhoneEt;
  @BindView(R2.id.loan_form_details_email_et)
  EditText mEmailEt;
  @BindView(R2.id.loan_form_details_term_of_condition_rb)
  RadioButton mTermAndConditionRb;
  @BindView(R2.id.loan_form_details_submit_btn)
  CustomButton mSubmitBtn;

  private String mLoanType;
  private User mUser;
  private int mTenureValue = 30;
  private ArrayAdapter<String> mPhoneCodeAdapter;
  private List<String> mPhoneCodeList;
  private TextWatcher mTextWatcher = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
      if (isValidateForm()) {
        mSubmitBtn.statusEnableButton();
      } else {
        mSubmitBtn.statusDisabledButton();
      }
    }
  };

  @OnTouch(R2.id.loan_form_details_layout)
  public boolean onLayoutTouched() {
    getBaseActivity().hideKeyboard();
    return false;
  }

  @OnTouch(R2.id.loan_form_details_scroll_view)
  public boolean onScrollViewTouched() {
    getBaseActivity().hideKeyboard();
    return false;
  }

  public static LoanFormDetailsFragment getInstance() {
    return new LoanFormDetailsFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_loan_form_details;
  }

  @Override
  public void initLayout() {
    setupPhoneCodeSpinner();
    addListeners();
  }

  private void setupPhoneCodeSpinner() {
    mPhoneCodeList = CountryCode.getCode();
    mPhoneCodeAdapter = new ArrayAdapter<String>(getViewContext(), R.layout.item_phone_code, mPhoneCodeList);
    mPhoneCodeSpinner.setAdapter(mPhoneCodeAdapter);
  }

  private void addListeners() {
    mHeaderView.getLeftIconIv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.back();
      }
    });

    addRadioGroupListeners();

    mTenureCpd.getMinusView().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mTenureCpd.getAddView().setVisibility(View.VISIBLE);
        if (mTenureValue > 0) mTenureValue--;
        mTenureCpd.getValueView().setText(String.valueOf(mTenureValue));
      }
    });
    mTenureCpd.getAddView().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (mTenureValue < 30) mTenureValue++;
        mTenureCpd.getValueView().setText(String.valueOf(mTenureValue));
        if (mTenureValue >= 30) {
          mTenureCpd.getAddView().setVisibility(View.INVISIBLE);
        }
      }
    });

    mLoanAmountEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        mLoanAmountEt.removeTextChangedListener(this);
        NumberUtils.formatIntegerEditTextRunTime(mLoanAmountEt);
        mLoanAmountEt.addTextChangedListener(this);
        if (isValidateForm()) {
          mSubmitBtn.statusEnableButton();
        } else {
          mSubmitBtn.statusDisabledButton();
        }
      }
    });
    mCurrentInterestRateEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        mCurrentInterestRateEt.removeTextChangedListener(this);
        String interestRate = mCurrentInterestRateEt.getText().toString().replace("%", "");
        if (!StringUtils.isEmpty(interestRate)) {
          mCurrentInterestRateEt.setText(interestRate.concat("%"));
        } else {
          mCurrentInterestRateEt.setText("");
        }
        mCurrentInterestRateEt.setSelection(interestRate.length());
        mCurrentInterestRateEt.addTextChangedListener(this);
      }
    });
    mNameEt.addTextChangedListener(mTextWatcher);
    mPhoneEt.addTextChangedListener(mTextWatcher);
    mEmailEt.addTextChangedListener(mTextWatcher);
  }

  private void addRadioGroupListeners() {
    ArrayList<RadioButton> propertyTypeRadioList = new ArrayList<>();
    propertyTypeRadioList.add(mPrivateResidentialRb);
    propertyTypeRadioList.add(mHdbRb);
    addRadioButtonClickListener(mPrivateResidentialRb, propertyTypeRadioList);
    addRadioButtonClickListener(mHdbRb, propertyTypeRadioList);

    ArrayList<RadioButton> constructionStatusRadioList = new ArrayList<>();
    constructionStatusRadioList.add(mCompletedRb);
    constructionStatusRadioList.add(mUncompleteRb);
    addRadioButtonClickListener(mCompletedRb, constructionStatusRadioList);
    addRadioButtonClickListener(mUncompleteRb, constructionStatusRadioList);

    ArrayList<RadioButton> ratesRadioList = new ArrayList<>();
    ratesRadioList.add(mFixedRb);
    ratesRadioList.add(mFloatingRb);
    ratesRadioList.add(mBothRb);
    addRadioButtonClickListener(mFixedRb, ratesRadioList);
    addRadioButtonClickListener(mFloatingRb, ratesRadioList);
    addRadioButtonClickListener(mBothRb, ratesRadioList);

    mTermAndConditionRb.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mTermAndConditionRb.setSelected(!mTermAndConditionRb.isSelected());
        if (isValidateForm()) {
          mSubmitBtn.statusEnableButton();
        } else {
          mSubmitBtn.statusDisabledButton();
        }
      }
    });
  }

  private void addRadioButtonClickListener(final RadioButton radioButton, final ArrayList<RadioButton> radioButtonArrayList) {
    radioButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        radioButton.setSelected(!radioButton.isSelected());
        for (RadioButton rb : radioButtonArrayList) {
          if (rb != radioButton) {
            rb.setSelected(false);
          }
        }
        if (isValidateForm()) {
          mSubmitBtn.statusEnableButton();
        }
      }
    });
  }

  private boolean isValidateForm() {
    if (!mPrivateResidentialRb.isSelected() && !mHdbRb.isSelected()) {
      return false;
    }
    if (!mFixedRb.isSelected() && !mFloatingRb.isSelected() && !mBothRb.isSelected()) {
      return false;
    }
    if (!mTermAndConditionRb.isSelected()) {
      return false;
    }
    if (StringUtils.isEmpty(mLoanAmountEt.getText().toString().trim())) {
      return false;
    }
    double loanAmount = Double.parseDouble(mLoanAmountEt.getText().toString().trim().replace(",", ""));
    if (loanAmount == 0) {
      return false;
    }

    if (StringUtils.isEmpty(mNameEt.getText().toString().trim())) {
      return false;
    }
    if (StringUtils.isEmpty(mPhoneEt.getText().toString().trim())
            || mPhoneEt.getText().toString().trim().length() < 7) {
      return false;
    }
    if (!StringUtils.isEmailValid(mEmailEt.getText().toString())) {
      return false;
    }

    switch (mLoanType) {
      case Constant.LoanType.NEW_LOAN:
        if (!mCompletedRb.isSelected() && !mUncompleteRb.isSelected()) {
          return false;
        }
        break;
      case Constant.LoanType.REFINANCE:
        if (StringUtils.isEmpty(mCurrentInterestRateEt.getText().toString())) {
          return false;
        }
        break;
    }
    return true;
  }

  @Override
  public void setupUI(String loanType, User user) {
    mLoanType = loanType;
    mUser = user;
    setupCommonUI(mUser);
    switch (loanType) {
      case Constant.LoanType.NEW_LOAN:
        setupNewLoanUI();
        break;
      case Constant.LoanType.REFINANCE:
        setupRefinanceUI();
        break;
    }
  }

  private void setupCommonUI(User user) {
    mTenureCpd.setValue(mTenureValue);
    mTenureCpd.getAddView().setVisibility(View.INVISIBLE);

    if (user != null) {
      mNameEt.setText(user.getUsers().getFullName());
      mPhoneEt.setText(user.getUsers().getPhoneNumber());
      mEmailEt.setText(user.getUsers().getEmail());
      for (int i = 0; i < mPhoneCodeList.size(); i++) {
        if (mUser.getUsers().getPhoneCountryCode().equals(mPhoneCodeList.get(i))) {
          mPhoneCodeSpinner.setSelection(i);
        }
      }
    }
  }

  private void setupNewLoanUI() {
    mHeaderView.setTitle(getResources().getString(R.string.new_loan));
    mConstructionStatusLayout.setVisibility(View.VISIBLE);
    mLoanAmountTv.setText(getResources().getString(R.string.loan_amount));
    mCurrentFinancerLayout.setVisibility(View.GONE);
    mCurrentInterestRateLayout.setVisibility(View.GONE);
  }

  private void setupRefinanceUI() {
    mHeaderView.setTitle(getResources().getString(R.string.refinance));
    mConstructionStatusLayout.setVisibility(View.GONE);
    mLoanAmountTv.setText(getResources().getString(R.string.existing_loan_amount));
    mCurrentFinancerLayout.setVisibility(View.VISIBLE);
    mCurrentInterestRateLayout.setVisibility(View.VISIBLE);
  }

}
