package com.positiveculture.app.screen.forgotpassword;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.google.gson.JsonElement;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;
import retrofit2.Call;

/**
 * The ForgotPassword Presenter
 */
public class ForgotPasswordPresenter extends Presenter<ForgotPasswordContract.View, ForgotPasswordContract.Interactor>
        implements ForgotPasswordContract.Presenter {

  public ForgotPasswordPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public ForgotPasswordContract.View onCreateView() {
    return ForgotPasswordFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public ForgotPasswordContract.Interactor onCreateInteractor() {
    return new ForgotPasswordInteractor(this);
  }

  @Override
  public void goBack() {
    mContainerView.back();
  }

  @Override
  public void sendPassword(String email) {
    mInteractor.forgotPassword(email, new CommonCallback<JsonElement>(getViewContext()) {
      @Override
      public void onSuccess(JsonElement data) {
        super.onSuccess(data);
        mView.sendMailSuccess();
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
        mView.onSendMailFail();
      }

      @Override
      public void onFailure(Call<ResponseDTO<JsonElement>> call, Throwable t) {
        super.onFailure(call, t);
        mView.onSendMailFail();
      }
    });
  }

}
