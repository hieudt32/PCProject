package com.positiveculture.app.screen.nric;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.User;

/**
 * The Nric interactor
 */
class NricInteractor extends Interactor<NricContract.Presenter>
        implements NricContract.Interactor {

  NricInteractor(NricContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void updateNric(String mNricNumber, CommonCallback<User> commonCallback) {
    ServiceBuilder.getInstance().getService().saveNric(mNricNumber).enqueue(commonCallback);
  }

  @Override
  public void updateUserId(String idType, String userid, CommonCallback<User> commonCallback) {
    ServiceBuilder.getInstance().getService().saveIdUser(userid, idType, -1, -1).enqueue(commonCallback);
  }
}
