package app.positiveculture.com.agent.screen.bankdetails;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.agent.ObjectType;
import app.positiveculture.com.agent.dto.BankDetail;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The BankDetails Presenter
 */
public class BankDetailsPresenterAgent extends Presenter<BankDetailsContractAgent.View, BankDetailsContractAgent.Interactor>
        implements BankDetailsContractAgent.Presenter {

  private OnSaveBankDetails mOnSaveBankDetails;
  private BankDetail mBankDetail;

  public BankDetailsPresenterAgent setBankDetail(BankDetail mBankDetail) {
    this.mBankDetail = mBankDetail;
    return this;
  }

  public BankDetailsPresenterAgent(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public BankDetailsContractAgent.View onCreateView() {
    return BankDetailsFragmentAgent.getInstance();
  }

  @Override
  public void start() {
    if(mBankDetail!= null)
    mView.setupDataForEditProfile(mBankDetail);
  }

  @Override
  public BankDetailsContractAgent.Interactor onCreateInteractor() {
    return new BankDetailsInteractorAgent(this);
  }

  public BankDetailsPresenterAgent setOnSaveBankDetails(OnSaveBankDetails onSaveBankDetails) {
    mOnSaveBankDetails = onSaveBankDetails;
    return this;
  }

  @Override
  public void saveEditedBankDetails(final String bankName, final String accountNumber, final String accountType) {
    mView.showProgress();
    mInteractor.updateBankDetail(bankName, accountNumber, accountType, new CommonCallback<User>(getViewContext()) {
      @Override
      public void onSuccess(User data) {
        super.onSuccess(data);
        mView.hideProgress();
        if (mOnSaveBankDetails != null) {
          mOnSaveBankDetails.onSaveBankDetailsClick(bankName, accountNumber, accountType);
          back();
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
        mView.hideProgress();
      }
    });
  }

  public interface OnSaveBankDetails {
    void onSaveBankDetailsClick(String bankName, String accountNumber, String accountType);
  }

}
