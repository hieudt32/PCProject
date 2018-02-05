package app.positiveculture.com.agent.screen.nric;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.User;

/**
 * The Nric Contract
 */
interface NricContractAgent {

  interface Interactor extends IInteractor<Presenter> {
    void saveNric(String nric, CommonCallback<User> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void setupDataForEditProfile(String mNricNumber);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void saveEditedNricNumber(String nric);
  }
}



