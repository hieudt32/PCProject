package app.positiveculture.com.agent.screen.clientlist;

import com.gemvietnam.base.viper.Interactor;

import java.util.List;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;

/**
 * The ClientList interactor
 */
class ClientListInteractor extends Interactor<ClientListContract.Presenter>
        implements ClientListContract.Interactor {

  ClientListInteractor(ClientListContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getListClient(int limit, int offset, String nameSearch, CommonCallback<List<MemberDTO>> commonCallback) {
    ServiceBuilder.getInstance().getService().getListOwner(limit, offset, nameSearch).enqueue(commonCallback);
  }

  @Override
  public void getProfileAgent(CommonCallback<AgentDTO> verified) {
    ServiceBuilder.getInstance().getService().getProfileAgent().enqueue(verified);
  }
}
