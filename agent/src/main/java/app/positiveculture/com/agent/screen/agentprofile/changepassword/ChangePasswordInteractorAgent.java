package app.positiveculture.com.agent.screen.agentprofile.changepassword;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.data.response.dto.Users;

/**
 * The ChangePassword interactor
 */
class ChangePasswordInteractorAgent extends Interactor<ChangePasswordContractAgent.Presenter>
        implements ChangePasswordContractAgent.Interactor {

  ChangePasswordInteractorAgent(ChangePasswordContractAgent.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void saveNewPassword(String newPassword, CommonCallback<Users> commonCallback) {
    ServiceBuilder.getInstance().getService().changePassword(newPassword, newPassword).enqueue(commonCallback);
  }
}
