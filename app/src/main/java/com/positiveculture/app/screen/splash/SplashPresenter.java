package com.positiveculture.app.screen.splash;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.positiveculture.app.screen.login.LoginPresenter;

import com.positiveculture.app.screen.register.RegisterPresenter;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.SettingDTO;


/**
 * The Login Presenter
 */
public class SplashPresenter extends Presenter<SplashContract.View, SplashContract.Interactor>
        implements SplashContract.Presenter {

  public SplashPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SplashContract.View onCreateView() {
    return SplashFragment.getInstance();
  }

  @Override
  public void start() {
    getAppSetting();
  }

  public void getAppSetting() {
    mInteractor.getAppSetting(new CommonCallback<SettingDTO>(getViewContext()) {
      @Override
      public void onSuccess(SettingDTO data) {
        super.onSuccess(data);
        PrefWrapper.saveSetting(data);
        mView.getAppSettingSuccess();
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        mView.hideProgress();
        if (errorCode.equals("ERROR_INVALID_TOKEN_HEADER") || errorCode.equals("ERROR_INVALID_TOKEN")) {
          PrefWrapper.remove(PrefWrapper.KEY_USER);
          mInteractor.getAppSetting(this);
        } else super.onError(errorCode, errorMessage);
      }
    });
  }

  @Override
  public void gotoLogin() {
    new LoginPresenter(mContainerView).pushView();
  }

  @Override
  public void goToRegister() {
    new RegisterPresenter(mContainerView).pushView();
  }

  @Override
  public SplashContract.Interactor onCreateInteractor() {
    return new SplashInteractor(this);
  }
}
