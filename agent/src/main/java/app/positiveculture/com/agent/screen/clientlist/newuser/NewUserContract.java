package app.positiveculture.com.agent.screen.clientlist.newuser;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.CreateMemberDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;

/**
 * The NewUser Contract
 */
interface NewUserContract {

  interface Interactor extends IInteractor<Presenter> {
    void createNewClient(CreateMemberDTO createMemberDTO, CommonCallback<MemberDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void setupData(String idType, String idNumber);

    void showCreateSuccessDialog();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToClientList();

    void createNewClient(CreateMemberDTO createMemberDTO);
  }
}



