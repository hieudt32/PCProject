package app.positiveculture.com.agent.screen.agentprofile.settings;

import android.content.Intent;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.ActivityUtils;


import app.positiveculture.com.agent.screen.agentprofile.changepassword.ChangePasswordPresenterAgent;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.ErrorDTO;

/**
 * The Settings Presenter
 */
public class SettingsPresenter extends Presenter<SettingsContract.View, SettingsContract.Interactor>
        implements SettingsContract.Presenter {

  public SettingsPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SettingsContract.View onCreateView() {
    return SettingsFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public SettingsContract.Interactor onCreateInteractor() {
    return new SettingsInteractor(this);
  }

  @Override
  public void goToChangePassword() {
    new ChangePasswordPresenterAgent(mContainerView).pushView();
  }

  @Override
  public void logout() {
    mView.showProgress();
    mInteractor.logout(new CommonCallback<ErrorDTO>(getViewContext()) {

      @Override
      public void onSuccess(ErrorDTO data) {
        super.onSuccess(data);
        PrefWrapper.remove(PrefWrapper.KEY_USER);
        PrefWrapper.remove(PrefWrapper.KEY_AGENT);
        ActivityUtils.restart(getViewContext());
      }
    });
  }
}
