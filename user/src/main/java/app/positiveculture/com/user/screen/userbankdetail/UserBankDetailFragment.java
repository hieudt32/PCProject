package app.positiveculture.com.user.screen.userbankdetail;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.dto.BankDetail;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomHeaderView;

/**
 * The UserBankDetail Fragment
 */
public class UserBankDetailFragment extends ViewFragment<UserBankDetailContract.Presenter> implements UserBankDetailContract.View {

  @BindView(R2.id.bank_details_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.bank_details_name_et)
  EditText mBankNameEt;
  @BindView(R2.id.bank_details_account_number_et)
  EditText mBankNumberEt;
  @BindView(R2.id.bank_details_account_type_et)
  EditText mBankTypeEt;
  @BindView(R2.id.bank_details_description)
  TextView mDescriptionTv;
  BankDetail mBankDetail;

  @OnClick(R2.id.left_icon_iv)
  void doBackClick() {
    mPresenter.back();
  }

  @OnClick(R2.id.right_text_tv)
  void doSaveClick() {
    mBankDetail.setBankName(mBankNameEt.getText().toString());
    mBankDetail.setBankAcountNo(mBankNumberEt.getText().toString());
    mBankDetail.setAcountType(mBankTypeEt.getText().toString());
    mPresenter.saveBankDetail(mBankDetail);
  }


  public static UserBankDetailFragment getInstance() {
    return new UserBankDetailFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_bank_details;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mBankNameEt.addTextChangedListener(createListener());
    mBankNumberEt.addTextChangedListener(createListener());
    mBankTypeEt.addTextChangedListener(createListener());
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
        if (mBankDetail != null && mBankDetail.getBankName() != null && mBankDetail.getBankAcountNo() != null &&
                mBankDetail.getAcountType() != null) {
          if (mBankNameEt.getText().toString().trim().compareTo(mBankDetail.getBankName()) != 0 ||
                  mBankNumberEt.getText().toString().trim().compareTo(mBankDetail.getBankAcountNo()) != 0
                  || mBankTypeEt.getText().toString().trim().compareTo(mBankDetail.getAcountType()) != 0) {
            mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
          } else {
            mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
          }
        } else {
          if (!mBankNameEt.getText().toString().trim().equals("") && !mBankNumberEt.getText().toString().trim().equals("") &&
                  !mBankTypeEt.getText().toString().trim().equals("")) {
            mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
          } else {
            mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
          }
        }
      }
    };
  }

  @Override
  public void bindView(BankDetail bankDetail) {
    mBankDetail = bankDetail;
    mHeaderView.getLeftIconIv().setVisibility(View.VISIBLE);
    mHeaderView.getRightTextTv().setVisibility(View.GONE);
    mHeaderView.getLeftTextTv().setVisibility(View.GONE);
    mHeaderView.getLeftIconIv().setImageResource(R.drawable.ic_back);
    mDescriptionTv.setText(getString(R.string.bank_details_desc));
    mBankNameEt.setHint(getString(R.string.bank));
    mBankNumberEt.setHint(getString(R.string.accout_no));
    mBankTypeEt.setHint(getString(R.string.account_type));
    mBankNameEt.setText(bankDetail.getBankName());
    mBankNumberEt.setText(bankDetail.getBankAcountNo());
    mBankTypeEt.setText(bankDetail.getAcountType());
  }
}
