package app.positiveculture.com.user.screen.upcoming;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.user.R;

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
