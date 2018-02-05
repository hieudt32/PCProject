package app.positiveculture.com.agent.screen.seller.processtracker;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.agent.screen.model.Property;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The ProcessTracker Contract
 */
interface ProcessTrackerContract {

  interface Interactor extends IInteractor<Presenter> {
    void getOTPOfProperty(String idProperty, CommonCallback<OtpDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void setupData(PropertyDTO mProperty, OtpDTO mOTP, TypeProcess mTypeProcess);

    void enableClickIconNext();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToOTP();

    void goToConveyancing();

    void goToPropertyDetail();

    void goToPaymentScreen();

    void goToCongratulationScreen();
  }
}



