package app.positiveculture.com.agent.screen.properties.selectagent;

import com.gemvietnam.base.viper.Interactor;

import java.util.List;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.AgentDTO;

/**
 * The SelectAgent interactor
 */
class SelectAgentInteractor extends Interactor<SelectAgentContract.Presenter>
        implements SelectAgentContract.Interactor {

  SelectAgentInteractor(SelectAgentContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getListAgent(int offset, int limit, String searchKey, CommonCallback<List<AgentDTO>> commonCallback) {
    ServiceBuilder.getInstance().getService().getListAgent(offset, limit, searchKey).enqueue(commonCallback);

  }

}
