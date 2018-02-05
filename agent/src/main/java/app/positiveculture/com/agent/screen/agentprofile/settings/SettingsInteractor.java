package app.positiveculture.com.agent.screen.agentprofile.settings;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.ErrorDTO;

/**
 * The Settings interactor
 */
class SettingsInteractor extends Interactor<SettingsContract.Presenter>
        implements SettingsContract.Interactor {

  SettingsInteractor(SettingsContract.Presenter presenter) {
    super(presenter);
  }


  @Override
  public void logout(CommonCallback<ErrorDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().logout().enqueue(commonCallback);
  }
}
