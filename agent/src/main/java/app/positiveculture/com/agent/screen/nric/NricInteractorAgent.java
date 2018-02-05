package app.positiveculture.com.agent.screen.nric;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.User;

/**
 * The Nric interactor
 */
class NricInteractorAgent extends Interactor<NricContractAgent.Presenter>
        implements NricContractAgent.Interactor {

  NricInteractorAgent(NricContractAgent.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void saveNric(String nric, CommonCallback<User> commonCallback) {
    ServiceBuilder.getInstance().getService().saveNric(nric).enqueue(commonCallback);
  }
}
