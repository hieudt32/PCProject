package com.positiveculture.app.screen.bankdetails;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.agent.ObjectType;
import app.positiveculture.com.agent.screen.main.MainPresenterAgent;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.user.screen.main.MainPresenterUser;

/**
 * The BankDetails Presenter
 */
public class BankDetailsPresenter extends Presenter<BankDetailsContract.View, BankDetailsContract.Interactor>
        implements BankDetailsContract.Presenter {


  public BankDetailsPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public BankDetailsContract.View onCreateView() {
    return BankDetailsFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public BankDetailsContract.Interactor onCreateInteractor() {
    return new BankDetailsInteractor(this);
  }

  @Override
  public void goToMainScreenUser() {
    new MainPresenterUser(mContainerView).pushView();
  }

  @Override
  public void saveBankDetail(String bank, String accountNumber, String accountType) {
    mView.showProgress();
    mInteractor.saveBankDetail(bank, accountNumber, accountType, new CommonCallback<User>(getViewContext()) {
      @Override
      public void onSuccess(User data) {
        super.onSuccess(data);
        mView.hideProgress();
        if (data instanceof MemberDTO) PrefWrapper.saveMember((MemberDTO) data);
        goToMainScreenUser();
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
        mView.hideProgress();
      }
    });
  }

}
