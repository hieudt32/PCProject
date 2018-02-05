package com.positiveculture.app.screen.createpassword;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.positiveculture.app.screen.verifyemail.VerifyEmailPresenter;

import app.positiveculture.com.agent.screen.nric.NricPresenterAgent;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.RegisterAgentDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The CreatePassword Presenter
 */
public class CreatePasswordPresenter extends Presenter<CreatePasswordContract.View, CreatePasswordContract.Interactor>
        implements CreatePasswordContract.Presenter {


  private String mSmsToken;

  public CreatePasswordPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public CreatePasswordContract.View onCreateView() {
    return CreatePasswordFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public CreatePasswordContract.Interactor onCreateInteractor() {
    return new CreatePasswordInteractor(this);
  }

  @Override
  public void goToNric() {
    new NricPresenterAgent(mContainerView)
            .pushView();
  }

  @Override
  public void changePassword(String email, String password) {
//    mView.showProgress();
    mView.getBaseActivity().hideKeyboard();

    RegisterAgentDTO registerAgentDTO = new RegisterAgentDTO();
    registerAgentDTO.setSmsToken(mSmsToken);
    registerAgentDTO.setEmail(email);
    registerAgentDTO.setPassword(password);

    mInteractor.createPassword(registerAgentDTO, new CommonCallback<User>(mView.getViewContext()) {
      @Override
      public void onSuccess(User data) {
        super.onSuccess(data);
//        mView.hideProgress();
        mView.onCreatePasswordSuccessful(data);
        PrefWrapper.saveUser(data);
        if (data instanceof AgentDTO) {
          PrefWrapper.saveAgent((AgentDTO) data);
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
//        mView.hideProgress();
        mView.onCreatePasswordFailed();
      }
    });
  }

  @Override
  public void goToConfirmEmail(String email) {
    new VerifyEmailPresenter(mContainerView)
            .setUserEmail(email)
            .pushView();
  }

  @Override
  public CreatePasswordPresenter setSmsToken(String data) {
    mSmsToken = data;
    return this;
  }
}
