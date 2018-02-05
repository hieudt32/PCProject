package app.positiveculture.com.agent.screen.agentprofile;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.agent.screen.agentprofile.editprofile.EditProfilePresenter;
import app.positiveculture.com.agent.screen.agentprofile.settings.SettingsPresenter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The AgentProfile Presenter
 */
public class AgentProfilePresenter extends Presenter<AgentProfileContract.View, AgentProfileContract.Interactor>
        implements AgentProfileContract.Presenter, EditProfilePresenter.OnEditProfileAgentListener {

  private AgentDTO mAgentDTO;

  public AgentProfilePresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public AgentProfileContract.View onCreateView() {
    return AgentProfileFragment.getInstance();
  }

  @Override
  public void start() {
    if (mAgentDTO == null) {
      getAgentProfile();
    } else {
      mView.showProfileForUserView(mAgentDTO);
    }
  }

  private void getAgentProfile() {
    mView.showProgress();
    mInteractor.getAgentProfile(new CommonCallback<AgentDTO>(getViewContext()) {
      @Override
      public void onSuccess(AgentDTO data) {
        super.onSuccess(data);
        PrefWrapper.saveAgent(data);
        mAgentDTO = data;
        mView.bindData(mAgentDTO);
        mView.hideProgress();
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        mAgentDTO = PrefWrapper.getAgent();
        mView.bindData(mAgentDTO);
        mView.hideProgress();
      }
    });
  }

  @Override
  public AgentProfileContract.Interactor onCreateInteractor() {
    return new AgentProfileInteractor(this);
  }

  @Override
  public void goToEditProfile() {
    new EditProfilePresenter(mContainerView)
            .setAgent(mAgentDTO)
            .setmOnEditProfileAgentListener(this)
            .pushView();
  }

  @Override
  public void goToSettings() {
    new SettingsPresenter(mContainerView).pushView();
  }


  @Override
  public void onEditSuccessful() {
    getAgentProfile();
  }

  public AgentProfilePresenter setAgentProfile(AgentDTO agentDTO) {
    this.mAgentDTO = agentDTO;
    return this;
  }
}
