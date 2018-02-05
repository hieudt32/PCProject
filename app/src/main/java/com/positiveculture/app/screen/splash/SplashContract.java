package com.positiveculture.app.screen.splash;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.SettingDTO;

/**
 * The Login Contract
 */
interface SplashContract {

  interface Interactor extends IInteractor<Presenter> {
    void getAppSetting(CommonCallback<SettingDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void getAppSettingSuccess();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void gotoLogin();

    void goToRegister();

    void getAppSetting();
  }
}



