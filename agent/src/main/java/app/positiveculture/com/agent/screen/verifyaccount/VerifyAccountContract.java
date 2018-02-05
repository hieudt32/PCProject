package app.positiveculture.com.agent.screen.verifyaccount;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

/**
 * The VerifyAccount Contract
 */
interface VerifyAccountContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
    void hideNric();

    void hideEmail();

  }

  interface Presenter extends IPresenter<View, Interactor> {
  }
}



