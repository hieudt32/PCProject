package app.positiveculture.com.agent.screen.verifyaccount;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.AgentDTO;

/**
 * The VerifyAccount Presenter
 */
public class VerifyAccountPresenter extends Presenter<VerifyAccountContract.View, VerifyAccountContract.Interactor>
        implements VerifyAccountContract.Presenter {

  public VerifyAccountPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public VerifyAccountContract.View onCreateView() {
    return VerifyAccountFragment.getInstance();
  }

  @Override
  public void start() {
    AgentDTO mAgent = PrefWrapper.getAgent();
    if (mAgent == null || PrefWrapper.getSetting() == null) return;

    if (mAgent.getNeedVerifyPhoto() == 1) {
      if (mAgent.getVerifyEmail() == 1) {
        mView.hideEmail();
      }
      if (mAgent.getUploadNric() == 1) {
        mView.hideNric();
      }
    } else {
      mView.hideNric();
    }
  }

  @Override
  public VerifyAccountContract.Interactor onCreateInteractor() {
    return new VerifyAccountInteractor(this);
  }
}
