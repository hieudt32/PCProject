package app.positiveculture.com.agent.screen.main;

import com.gemvietnam.base.viper.Interactor;

/**
 * The Main interactor
 */
class MainInteractorAgent extends Interactor<MainContractAgent.Presenter>
        implements MainContractAgent.Interactor {

  MainInteractorAgent(MainContractAgent.Presenter presenter) {
    super(presenter);
  }
}
