package app.positiveculture.com.agent.screen.seller.verifysigning;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The VerifySigning Contract
 */
interface VerifySigningContract {

  interface Interactor extends IInteractor<Presenter> {
    void verifyOfflineSigning(String idOTP, CommonCallback<OtpDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void bindView(PropertyDTO property);

    void bindViewForCompleteOffline();

    void bindViewForVerifyOfflineSigning();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void verifyOfflineSigning();
  }
}



