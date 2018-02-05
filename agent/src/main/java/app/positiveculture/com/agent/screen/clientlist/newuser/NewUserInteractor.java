package app.positiveculture.com.agent.screen.clientlist.newuser;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.CreateMemberDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;

/**
 * The NewUser interactor
 */
class NewUserInteractor extends Interactor<NewUserContract.Presenter>
        implements NewUserContract.Interactor {

  NewUserInteractor(NewUserContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void createNewClient(CreateMemberDTO createMemberDTO, CommonCallback<MemberDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().createNewClient(createMemberDTO).enqueue(commonCallback);
  }

}
