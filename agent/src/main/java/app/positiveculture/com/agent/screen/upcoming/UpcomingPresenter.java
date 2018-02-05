package app.positiveculture.com.agent.screen.upcoming;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

/**
 * The Upcoming Presenter
 */
public class UpcomingPresenter extends Presenter<UpcomingContract.View, UpcomingContract.Interactor>
        implements UpcomingContract.Presenter {

  public UpcomingPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public UpcomingContract.View onCreateView() {
    return UpcomingFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public UpcomingContract.Interactor onCreateInteractor() {
    return new UpcomingInteractor(this);
  }
}
