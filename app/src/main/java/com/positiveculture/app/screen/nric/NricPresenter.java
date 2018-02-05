package com.positiveculture.app.screen.nric;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.positiveculture.app.screen.bankdetails.BankDetailsPresenter;

import app.positiveculture.com.agent.screen.main.MainPresenterAgent;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The Nric Presenter
 */
public class NricPresenter extends Presenter<NricContract.View, NricContract.Interactor>
        implements NricContract.Presenter {

  private boolean isSetupprofile = false;

  public NricPresenter setSetupprofile(boolean setupprofile) {
    isSetupprofile = setupprofile;
    return this;
  }

  public NricPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public NricContract.View onCreateView() {
    return NricFragment.getInstance();
  }

  @Override
  public void start() {
    if (isSetupprofile) {
      MemberDTO memberDTO = PrefWrapper.getMember();
      if (memberDTO != null) {
        mView.showUserFrom(memberDTO.getIdType(), memberDTO.getmIdNumber());
      }
    }
  }

  @Override
  public void goToBankDetails() {
    new BankDetailsPresenter(mContainerView)
            .pushView();
  }

  @Override
  public void goToMainAgent() {
    new MainPresenterAgent(mContainerView).pushView();
  }

  @Override
  public void updateNric(String mNricNumber) {
    mView.getBaseActivity().hideKeyboard();
    mView.showProgress();
    mInteractor.updateNric(mNricNumber, new CommonCallback<User>(getViewContext()) {
      @Override
      public void onSuccess(User data) {
        super.onSuccess(data);
        mView.hideProgress();
        mView.getBaseActivity().hideKeyboard();
        if (data instanceof AgentDTO) PrefWrapper.saveAgent((AgentDTO) data);
        goToMainAgent();
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
        mView.hideProgress();
      }
    });
  }

  @Override
  public void updateUserId(String selectedType, String userid) {
    mView.showProgress();
    mInteractor.updateUserId(selectedType, userid, new CommonCallback<User>(getViewContext()) {
      @Override
      public void onSuccess(User data) {
        super.onSuccess(data);
        if (isSetupprofile) goToBankDetails();
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public void nextStep() {
    if (isSetupprofile) {
      goToBankDetails();
    } else {
      goToMainAgent();
    }
  }

  @Override
  public NricContract.Interactor onCreateInteractor() {
    return new NricInteractor(this);
  }
}
