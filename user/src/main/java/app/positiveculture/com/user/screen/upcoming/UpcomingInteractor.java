package app.positiveculture.com.user.screen.upcoming;

import com.gemvietnam.base.viper.Interactor;

/**
 * The Upcoming interactor
 */
class UpcomingInteractor extends Interactor<UpcomingContract.Presenter>
        implements UpcomingContract.Interactor {

  UpcomingInteractor(UpcomingContract.Presenter presenter) {
    super(presenter);
  }
}
