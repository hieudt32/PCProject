package app.positiveculture.com.agent.screen.main;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.eventbus.EventBusWrapper;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.event.ReloadClientListEvent;
import app.positiveculture.com.agent.screen.agentprofile.AgentProfilePresenter;
import app.positiveculture.com.agent.screen.clientlist.ClientListPresenter;
import app.positiveculture.com.agent.screen.properties.PropertiesPresenter;

/**
 * The Main Presenter
 */
public class MainPresenterAgent extends Presenter<MainContractAgent.View, MainContractAgent.Interactor>
        implements MainContractAgent.Presenter {

  public MainPresenterAgent(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public MainContractAgent.View onCreateView() {
    return MainFragmentAgent.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public MainContractAgent.Interactor onCreateInteractor() {
    return new MainInteractorAgent(this);
  }

  @Override
  public Presenter getMainTab(int fragmentId) {
    Presenter presenter;
    if (fragmentId == R.id.tab_properties_agent) {
      presenter = new PropertiesPresenter(mContainerView);
    } else if (fragmentId == R.id.tab_clients) {
      presenter = new ClientListPresenter(mContainerView);
    } else {
      presenter = new AgentProfilePresenter(mContainerView);
    }

    return presenter;
  }

  @Override
  public void onReloadClientListClient() {
    EventBusWrapper.post(new ReloadClientListEvent());
  }
}
