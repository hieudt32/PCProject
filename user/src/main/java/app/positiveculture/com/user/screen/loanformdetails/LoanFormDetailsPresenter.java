package app.positiveculture.com.user.screen.loanformdetails;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.user.screen.loan.LoanPresenter;

/**
 * The LoanFormDetails Presenter
 */
public class LoanFormDetailsPresenter extends Presenter<LoanFormDetailsContract.View, LoanFormDetailsContract.Interactor>
        implements LoanFormDetailsContract.Presenter {

  private String mLoanType;

  public LoanFormDetailsPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public LoanFormDetailsContract.View onCreateView() {
    return LoanFormDetailsFragment.getInstance();
  }

  @Override
  public void start() {
    User user = PrefWrapper.getUser();
    mView.setupUI(mLoanType, user);
  }

  public LoanFormDetailsPresenter setLoanType(String type) {
    mLoanType = type;
    return this;
  }

  @Override
  public LoanFormDetailsContract.Interactor onCreateInteractor() {
    return new LoanFormDetailsInteractor(this);
  }
}
