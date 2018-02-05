package app.positiveculture.com.agent.screen.clientlist.existinguser;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;

/**
 * The ExistingUser Contract
 */
interface ExistingUserContract {

  interface Interactor extends IInteractor<Presenter> {
    void addExistingClient(Long clientId, CommonCallback<AgentDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void setupData(MemberDTO memberDTO);

    void showRequestSentDialog();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void addExistingClient(Long clientId);

    void backToClientList();
  }
}



