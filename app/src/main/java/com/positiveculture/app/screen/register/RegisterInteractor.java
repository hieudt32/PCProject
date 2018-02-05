package com.positiveculture.app.screen.register;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.CEAProfileDTO;

/**
 * The Register interactor
 */
class RegisterInteractor extends Interactor<RegisterContract.Presenter>
        implements RegisterContract.Interactor {

  RegisterInteractor(RegisterContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void checkCeaNumber(String ceaNumber, CommonCallback<CEAProfileDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().checkCEANumber(ceaNumber).enqueue(commonCallback);
  }
}
