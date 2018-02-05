package app.positiveculture.com.agent.screen.agentprofile;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The AgentProfile Contract
 */
interface AgentProfileContract {

  interface Interactor extends IInteractor<Presenter> {
    void getAgentProfile(CommonCallback<AgentDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void bindData(AgentDTO mAgentDTO);

    void showProfileForUserView(AgentDTO mAgentDTO);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToEditProfile();

    void goToSettings();
  }
}



