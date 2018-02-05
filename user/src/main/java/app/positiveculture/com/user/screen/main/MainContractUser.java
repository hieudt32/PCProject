package app.positiveculture.com.user.screen.main;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

/**
 * The Main Contract
 */
interface MainContractUser {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
  }

  interface Presenter extends IPresenter<View, Interactor> {

    com.gemvietnam.base.viper.Presenter getMainTab(int fragmentId);
  }
}



