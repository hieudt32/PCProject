package com.positiveculture.app.screen.verifyemail;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

/**
 * The VerifyEmail Contract
 */
interface VerifyEmailContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
      void setupData(String userEmail);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToUpdateNric();
  }
}



