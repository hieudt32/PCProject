package com.positiveculture.app.screen.splash;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.SettingDTO;

/**
 * The Login interactor
 */
class SplashInteractor extends Interactor<SplashContract.Presenter>
        implements SplashContract.Interactor {

  SplashInteractor(SplashContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getAppSetting(CommonCallback<SettingDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().getAppSetting().enqueue(commonCallback);
  }
}
