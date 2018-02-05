package app.positiveculture.com.agent.screen.agentprofile;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.AgentDTO;

/**
 * The AgentProfile interactor
 */
class AgentProfileInteractor extends Interactor<AgentProfileContract.Presenter>
        implements AgentProfileContract.Interactor {

  AgentProfileInteractor(AgentProfileContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getAgentProfile(CommonCallback<AgentDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().getProfileAgent().enqueue(commonCallback);
  }
}
