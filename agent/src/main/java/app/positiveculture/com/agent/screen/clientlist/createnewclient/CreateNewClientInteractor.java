package app.positiveculture.com.agent.screen.clientlist.createnewclient;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.MemberDTO;

/**
 * The CreateNewClient interactor
 */
class CreateNewClientInteractor extends Interactor<CreateNewClientContract.Presenter>
        implements CreateNewClientContract.Interactor {

  CreateNewClientInteractor(CreateNewClientContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void checkClient(String idType, String idNumber, CommonCallback<MemberDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().checkClient(idType, idNumber).enqueue(commonCallback);

  }
}
