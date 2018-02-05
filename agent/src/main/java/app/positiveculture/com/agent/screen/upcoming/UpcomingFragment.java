package app.positiveculture.com.agent.screen.upcoming;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;

/**
 * The Upcoming Fragment
 */
public class UpcomingFragment extends ViewFragment<UpcomingContract.Presenter> implements UpcomingContract.View {

  public static UpcomingFragment getInstance() {
    return new UpcomingFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_upcoming;
  }
}
