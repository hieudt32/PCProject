package com.positiveculture.app.screen.confirmprofile;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.CeaSmsDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;

/**
 * The ConfirmProfile interactor
 */
class ConfirmProfileInteractor extends Interactor<ConfirmProfileContract.Presenter>
        implements ConfirmProfileContract.Interactor {

  ConfirmProfileInteractor(ConfirmProfileContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void sendSMSAgent(String ceaNumber, CommonCallback<ResponseDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().sendSMSAgent(ceaNumber).enqueue(commonCallback);
  }
}
