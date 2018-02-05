package app.positiveculture.com.user.screen.userprocesstracker;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The UserProcessTracker Contract
 */
interface UserProcessTrackerContract {

  interface Interactor extends IInteractor<Presenter> {
    void getOTPOfProperty(String s, CommonCallback<OtpDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void enableNextButton();

    void setupData(PropertyDTO mProperty, OtpDTO mOTP, TypeProcess mTypeProcess);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void signOTP();

    void goToDetailScreen();

    void goToConveyancing();
  }
}



