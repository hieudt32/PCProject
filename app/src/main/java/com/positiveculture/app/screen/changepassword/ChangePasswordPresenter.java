package com.positiveculture.app.screen.changepassword;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.positiveculture.app.screen.setupprofile.SetupProfilePresenter;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.data.response.dto.Users;

/**
 * The ChangePassword Presenter
 */
public class ChangePasswordPresenter extends Presenter<ChangePasswordContract.View, ChangePasswordContract.Interactor>
        implements ChangePasswordContract.Presenter {

  private Users mUsers;

  public ChangePasswordPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public ChangePasswordContract.View onCreateView() {
    return ChangePasswordFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public void changePassword(String newPass, String confirmPass) {
    getView().getBaseActivity().hideKeyboard();
    mInteractor.changePassword(newPass, confirmPass, new CommonCallback<Users>(mView.getViewContext()) {
              @Override
              public void onSuccess(Users data) {
                super.onSuccess(data);
                mUsers = data;
                mView.onChangePasswordSuccess();
              }

              @Override
              public void onError(String errorCode, ErrorDTO errorMessage) {
                super.onError(errorCode, errorMessage);
                mView.onChangePasswordFailed();
              }
            }
    );
  }

  @Override
  public ChangePasswordContract.Interactor onCreateInteractor() {
    return new ChangePasswordInteractor(this);
  }

  @Override
  public void goToSetupProfileScreen() {
    new SetupProfilePresenter(mContainerView)
            .setUser(mUsers)
            .pushView();
  }
}
