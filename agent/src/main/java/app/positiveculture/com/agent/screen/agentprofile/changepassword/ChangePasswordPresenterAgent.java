package app.positiveculture.com.agent.screen.agentprofile.changepassword;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.data.response.dto.Users;

/**
 * The ChangePassword Presenter
 */
public class ChangePasswordPresenterAgent extends Presenter<ChangePasswordContractAgent.View, ChangePasswordContractAgent.Interactor>
        implements ChangePasswordContractAgent.Presenter {

  public ChangePasswordPresenterAgent(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public ChangePasswordContractAgent.View onCreateView() {
    return ChangePasswordFragmentAgent.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public ChangePasswordContractAgent.Interactor onCreateInteractor() {
    return new ChangePasswordInteractorAgent(this);
  }

  @Override
  public void saveNewPassword(String newPassword) {
    mView.showProgress();
    mInteractor.saveNewPassword(newPassword, new CommonCallback<Users>(getViewContext()) {
      @Override
      public void onSuccess(Users data) {
        super.onSuccess(data);
        mView.hideProgress();
        back();
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
        mView.hideProgress();
      }
    });
  }
}
