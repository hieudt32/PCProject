package app.positiveculture.com.user.screen.userconveyancing;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.user.screen.loandetails.LoanDetailsPresenter;

/**
 * The UserConveyancing Presenter
 */
public class UserConveyancingPresenter extends Presenter<UserConveyancingContract.View, UserConveyancingContract.Interactor>
        implements UserConveyancingContract.Presenter, LoanDetailsPresenter.OnSaveLoanDetailsListener {

  private String mBankName;
  private String mBankReference;

  public UserConveyancingPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public UserConveyancingContract.View onCreateView() {
    return UserConveyancingFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public UserConveyancingContract.Interactor onCreateInteractor() {
    return new UserConveyancingInteractor(this);
  }

  @Override
  public void goToLoanDetails() {
    new LoanDetailsPresenter(mContainerView)
            .setOnSaveLoanDetailsListener(this)
            .setLoanDetails(mBankName, mBankReference)
            .pushView();
  }

  @Override
  public void onSaveLoanDetails(String bank, String reference) {
    mView.bindBankInfo(bank);
    mBankName = bank;
    mBankReference = reference;
  }
}
