package com.positiveculture.app.screen.verifyemail;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.positiveculture.app.screen.nric.NricPresenter;
import com.positiveculture.app.screen.nricupload.NricUploadPresenter;

import app.positiveculture.com.data.pref.PrefWrapper;

/**
 * The VerifyEmail Presenter
 */
public class VerifyEmailPresenter extends Presenter<VerifyEmailContract.View, VerifyEmailContract.Interactor>
        implements VerifyEmailContract.Presenter {

  private String mUserEmail;

  public VerifyEmailPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public VerifyEmailContract.View onCreateView() {
    return VerifyEmailFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
    mView.setupData(mUserEmail);
  }

  public VerifyEmailPresenter setUserEmail(String userEmail) {
    mUserEmail = userEmail;
    return this;
  }

  @Override
  public VerifyEmailContract.Interactor onCreateInteractor() {
    return new VerifyEmailInteractor(this);
  }

  @Override
  public void goToUpdateNric() {
    if (PrefWrapper.getSetting().getNeedUploadImage() == 1) {
      new NricUploadPresenter(mContainerView).pushView();
    } else {
      new NricPresenter(mContainerView).pushView();
    }
  }
}
