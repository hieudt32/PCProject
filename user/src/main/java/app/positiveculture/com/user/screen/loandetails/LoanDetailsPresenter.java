package app.positiveculture.com.user.screen.loandetails;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.StringUtils;

/**
 * The LoanDetails Presenter
 */
public class LoanDetailsPresenter extends Presenter<LoanDetailsContract.View, LoanDetailsContract.Interactor>
        implements LoanDetailsContract.Presenter {

  private String mBankName;
  private String mBankReference;

  private OnSaveLoanDetailsListener mOnSaveLoanDetailsListener;

  public LoanDetailsPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public LoanDetailsContract.View onCreateView() {
    return LoanDetailsFragment.getInstance();
  }

  @Override
  public void start() {
    if (!StringUtils.isEmpty(mBankName) && !StringUtils.isEmpty(mBankReference)) {
      mView.bindBankDetails(mBankName, mBankReference);
    }
  }

  @Override
  public LoanDetailsContract.Interactor onCreateInteractor() {
    return new LoanDetailsInteractor(this);
  }

  public LoanDetailsPresenter setOnSaveLoanDetailsListener(OnSaveLoanDetailsListener onSaveLoanDetailsListener) {
    mOnSaveLoanDetailsListener = onSaveLoanDetailsListener;
    return this;
  }

  @Override
  public void saveLoanDetails(String bank, String reference) {
    if (mOnSaveLoanDetailsListener != null) {
      mOnSaveLoanDetailsListener.onSaveLoanDetails(bank, reference);
    }
    back();
  }

  public LoanDetailsPresenter setLoanDetails(String bankName, String bankReference) {
    if (!StringUtils.isEmpty(bankName) && !StringUtils.isEmpty(bankReference)) {
      mBankName = bankName;
      mBankReference = bankReference;
    }
    return this;
  }

  public interface OnSaveLoanDetailsListener {
    void onSaveLoanDetails(String bank, String reference);
  }

}
