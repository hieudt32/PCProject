package app.positiveculture.com.agent.screen.properties;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.agent.Constant;
import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.PropertiesCountDTO;
import app.positiveculture.com.data.response.dto.AgentDTO;

/**
 * The Properties interactor
 */
class PropertiesInteractor extends Interactor<PropertiesContract.Presenter>
        implements PropertiesContract.Interactor {

  PropertiesInteractor(PropertiesContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void checkListProperties(CommonCallback<PropertiesCountDTO> commonCallback) {
    int limit = Constant.PAGE_LIMIT;
    ServiceBuilder.getInstance().getService().getPropertiesCount().enqueue(commonCallback);
  }

  @Override
  public void getProfileAgent(CommonCallback<AgentDTO> verified) {
    ServiceBuilder.getInstance().getService().getProfileAgent().enqueue(verified);
  }
}
