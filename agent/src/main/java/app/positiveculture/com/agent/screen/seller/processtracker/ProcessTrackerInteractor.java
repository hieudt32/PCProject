package app.positiveculture.com.agent.screen.seller.processtracker;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * The ProcessTracker interactor
 */
class ProcessTrackerInteractor extends Interactor<ProcessTrackerContract.Presenter>
        implements ProcessTrackerContract.Interactor {

  ProcessTrackerInteractor(ProcessTrackerContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getOTPOfProperty(String idProperty, CommonCallback<OtpDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().getOtpProperty(idProperty).enqueue(commonCallback);
  }
}
