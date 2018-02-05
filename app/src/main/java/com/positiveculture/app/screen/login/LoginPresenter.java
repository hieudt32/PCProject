package com.positiveculture.app.screen.login;

import android.util.Log;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.positiveculture.app.screen.changepassword.ChangePasswordPresenter;
import com.positiveculture.app.screen.forgotpassword.ForgotPasswordPresenter;

import app.positiveculture.com.agent.screen.main.MainPresenterAgent;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.ErrorMessage;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.user.screen.main.MainPresenterUser;
import retrofit2.Call;

/**
 * The login Presenter
 */
public class LoginPresenter extends Presenter<LoginContract.View, LoginContract.Interactor>
        implements LoginContract.Presenter {

  private int mFailedLoginCount = 0;

  public LoginPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public LoginContract.View onCreateView() {
    return LoginFragment.getInstance();
  }

  @Override
  public void start() {
    mView.checkSaveEmailAndPass();
  }

  @Override
  public void goToChangePassword() {
    mView.getBaseActivity().hideKeyboard();
    new ChangePasswordPresenter(mContainerView)
            .pushView();
  }

  @Override
  public void goToForgotPassword() {
    new ForgotPasswordPresenter(mContainerView).pushView();
  }

  @Override
  public void goBack() {
    mContainerView.back();
  }

  @Override
  public void login(final String userName, final String password) {
    mInteractor.login(userName, password, new CommonCallback<User>(getViewContext()) {
      @Override
      public void onSuccess(User data) {
        super.onSuccess(data);
        mView.changeStatusBt();
        Log.e("@@@@@", data.getToken());
        PrefWrapper.saveUser(data);
        mView.getBaseActivity().hideKeyboard();
        if (data instanceof AgentDTO) {
          PrefWrapper.saveAgent((AgentDTO) data);
          new MainPresenterAgent(mContainerView).pushView();
        } else if (data instanceof MemberDTO) {
          PrefWrapper.saveMember((MemberDTO) data);
          if (data.getIsFirstTimeLogin() == 1) {
            new ChangePasswordPresenter(mContainerView).pushView();
          } else {
            new MainPresenterUser(mContainerView).pushView();
          }
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        mView.onLoginFailed();
        if (errorCode.compareTo(ErrorMessage.ERROR_WRONG_CREDENTIAL.toString()) == 0) {
          mFailedLoginCount++;
          if (mFailedLoginCount >= 3) {
            mFailedLoginCount = 0;
            mView.disableLoginButton();
          } else {
            super.onError(errorCode, errorMessage);
          }
        } else if (errorCode.equals("ERROR_INVALID_TOKEN_HEADER") || errorCode.equals("ERROR_INVALID_TOKEN")) {
          PrefWrapper.remove(PrefWrapper.KEY_USER);
          mInteractor.login(userName, password, this);
        } else super.onError(errorCode, errorMessage);
      }

      @Override
      public void onFailure(Call<ResponseDTO<User>> call, Throwable t) {
        super.onFailure(call, t);
        mView.onLoginFailed();
      }
    });
  }

  @Override
  public LoginContract.Interactor onCreateInteractor() {
    return new LoginInteractor(this);
  }
}
