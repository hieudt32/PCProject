package app.positiveculture.com.user.screen.properties;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.PropertiesCountDTO;

/**
 * The Properties interactor
 */
class PropertiesInteractor extends Interactor<PropertiesContract.Presenter>
        implements PropertiesContract.Interactor {

  PropertiesInteractor(PropertiesContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getPropertiesCount(CommonCallback<PropertiesCountDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().getPropertiesCount().enqueue(commonCallback);
  }
}
