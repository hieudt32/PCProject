package app.positiveculture.com.user.screen.userprocesstracker;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * The UserProcessTracker interactor
 */
class UserProcessTrackerInteractor extends Interactor<UserProcessTrackerContract.Presenter>
        implements UserProcessTrackerContract.Interactor {

  UserProcessTrackerInteractor(UserProcessTrackerContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getOTPOfProperty(String idProperty, CommonCallback<OtpDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().getOtpProperty(idProperty).enqueue(commonCallback);
  }
}
