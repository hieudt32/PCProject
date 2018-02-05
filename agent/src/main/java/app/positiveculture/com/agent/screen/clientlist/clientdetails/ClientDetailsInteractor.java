package app.positiveculture.com.agent.screen.clientlist.clientdetails;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.MemberDTO;

/**
 * The ClientDetails interactor
 */
class ClientDetailsInteractor extends Interactor<ClientDetailsContract.Presenter>
        implements ClientDetailsContract.Interactor {

  ClientDetailsInteractor(ClientDetailsContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void saveNoteClient(String idMember, String note, CommonCallback<MemberDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().agentNoteMember(idMember, note).enqueue(commonCallback);
  }
}
