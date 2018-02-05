package com.positiveculture.app.screen.register;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.positiveculture.app.screen.confirmprofile.ConfirmProfilePresenter;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.CEAProfileDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;

/**
 * The Register Presenter
 */
public class RegisterPresenter extends Presenter<RegisterContract.View, RegisterContract.Interactor>
        implements RegisterContract.Presenter {

  public RegisterPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public RegisterContract.View onCreateView() {
    return RegisterFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public void gotoConfirmProfile(CEAProfileDTO data) {
    mContainerView.getBaseActivity().hideKeyboard();
    new ConfirmProfilePresenter(mContainerView)
            .setCEAProfileDTO(data)
            .pushView();
  }

  @Override
  public RegisterContract.Interactor onCreateInteractor() {
    return new RegisterInteractor(this);
  }

  @Override
  public void goBack() {
    mContainerView.back();
  }

  @Override
  public void onNextSelected(final String ceaNumber) {
    mInteractor.checkCeaNumber(ceaNumber, new CommonCallback<CEAProfileDTO>(mView.getViewContext()) {
      @Override
      public void onSuccess(CEAProfileDTO data) {
        super.onSuccess(data);
        mView.onCheckCeaNumberSuccessful(data);
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        if (errorCode.equals("ERROR_INVALID_TOKEN_HEADER") || errorCode.equals("ERROR_INVALID_TOKEN")) {
          PrefWrapper.saveUser(null);
          mInteractor.checkCeaNumber(ceaNumber, this);
        } else {
          super.onError(errorCode, error);
          mView.onCheckCeaNumberFailed();
        }
      }
    });
  }
}
