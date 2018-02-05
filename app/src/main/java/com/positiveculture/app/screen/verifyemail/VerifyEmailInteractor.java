package com.positiveculture.app.screen.verifyemail;

import com.gemvietnam.base.viper.Interactor;

/**
 * The VerifyEmail interactor
 */
class VerifyEmailInteractor extends Interactor<VerifyEmailContract.Presenter>
        implements VerifyEmailContract.Interactor {

  VerifyEmailInteractor(VerifyEmailContract.Presenter presenter) {
    super(presenter);
  }
}
