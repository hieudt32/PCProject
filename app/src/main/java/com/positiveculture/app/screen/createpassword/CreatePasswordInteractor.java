package com.positiveculture.app.screen.createpassword;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.RegisterAgentDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The CreatePassword interactor
 */
class CreatePasswordInteractor extends Interactor<CreatePasswordContract.Presenter>
        implements CreatePasswordContract.Interactor {

  CreatePasswordInteractor(CreatePasswordContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void createPassword(RegisterAgentDTO registerAgentDTO, CommonCallback<User> commonCallback) {
    ServiceBuilder.getInstance().getService().createPassword(registerAgentDTO).enqueue(commonCallback);
  }
}
