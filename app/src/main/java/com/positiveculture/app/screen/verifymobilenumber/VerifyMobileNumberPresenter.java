package com.positiveculture.app.screen.verifymobilenumber;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.positiveculture.app.screen.createpassword.CreatePasswordPresenter;
import com.positiveculture.app.screen.nric.NricPresenter;
import com.positiveculture.app.screen.nricupload.NricUploadPresenter;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.CeaSmsDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.MemberVerifySmsDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;

/**
 * The VerifyMobileNumber Presenter
 */
public class VerifyMobileNumberPresenter extends Presenter<VerifyMobileNumberContract.View, VerifyMobileNumberContract.Interactor>
        implements VerifyMobileNumberContract.Presenter {

  private String mCeaNumber;
  private boolean isFromSetupProfile = false;
  private int mOTPSendCount = 0;

  public VerifyMobileNumberPresenter setFromSetupProfile(boolean fromSetupProfile) {
    isFromSetupProfile = fromSetupProfile;
    return this;
  }

  public VerifyMobileNumberPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public VerifyMobileNumberContract.View onCreateView() {
    return VerifyMobileNumberFragment.getInstance();
  }

  @Override
  public void start() {
    if (isFromSetupProfile) {
      sendSMSMember(false);
    }
  }

  private void sendSMSMember(final boolean isResend) {
    mView.showProgress();
    mInteractor.sendSMSMember(new CommonCallback<MemberVerifySmsDTO>(getViewContext()) {
      @Override
      public void onSuccess(MemberVerifySmsDTO data) {
        super.onSuccess(data);
        mView.hideProgress();
        if (isResend) mView.onResendOnTimePinSuccess();
        mOTPSendCount = 0;
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
        mView.hideProgress();
      }
    });
  }

  @Override
  public VerifyMobileNumberContract.Interactor onCreateInteractor() {
    return new VerifyMobileNumberInteractor(this);
  }

  @Override
  public void goToCreatePassword(String smsToken) {
    new CreatePasswordPresenter(mContainerView)
            .setSmsToken(smsToken)
            .pushView();
  }

  @Override
  public void goBack() {
    mContainerView.back();
  }

  @Override
  public void checkSMSAgent(String code) {
    if (mOTPSendCount < 2) {
      if (mCeaNumber != null && !isFromSetupProfile) {
        mContainerView.getBaseActivity().hideKeyboard();
        mView.showProgress();
        mInteractor.checkSMSAgent(mCeaNumber, code, new CommonCallback<CeaSmsDTO>(mView.getViewContext()) {

                  @Override
                  public void onSuccess(CeaSmsDTO data) {
                    super.onSuccess(data);
                    mView.onCheckSmsSuccess(data.getSmsToken());
                  }

                  @Override
                  public void onError(String errorCode, ErrorDTO errorMessage) {
                    mView.hideProgress();
                    mOTPSendCount++;
                    if (mOTPSendCount == 2) {
                      mView.showResendRequestDialog();
                    } else {
                      super.onError(errorCode, errorMessage);
                    }
                  }
                }
        );
      }

      if (isFromSetupProfile && mCeaNumber == null) {
        mContainerView.getBaseActivity().hideKeyboard();
        mView.showProgress();
        mInteractor.checkSMSMember(code, new CommonCallback<MemberVerifySmsDTO>(getViewContext()) {
          @Override
          public void onSuccess(MemberVerifySmsDTO data) {
            super.onSuccess(data);
            mView.hideProgress();
            if (PrefWrapper.getSetting().getNeedUploadImage() == 1) {
              new NricUploadPresenter(mContainerView)
                      .setSetupProfile(true)
                      .pushView();
            } else {
              new NricPresenter(mContainerView)
                      .setSetupprofile(true)
                      .pushView();
            }
          }

          @Override
          public void onError(String errorCode, ErrorDTO errorMessage) {
            mView.hideProgress();
            mOTPSendCount++;
            if (mOTPSendCount == 2) {
              mView.showResendRequestDialog();
            } else {
              super.onError(errorCode, errorMessage);
            }
          }
        });
      }
    } else {
      mView.showResendRequestDialog();
    }
  }

  @Override
  public void resendOneTimePin() {
    mView.showProgress();
    if (!isFromSetupProfile && mCeaNumber != null) {
      mInteractor.resendOneTimePinAgent(mCeaNumber, new CommonCallback<ResponseDTO>(getViewContext()) {

        @Override
        public void onSuccess(ResponseDTO data) {
          super.onSuccess(data);
          mView.onResendOnTimePinSuccess();
          mOTPSendCount = 0;
        }

        @Override
        public void onError(String errorCode, ErrorDTO errorMessage) {
          super.onError(errorCode, errorMessage);
          mView.hideProgress();
        }
      });
    } else if (isFromSetupProfile && mCeaNumber == null) {
      sendSMSMember(true);
    }
  }

  @Override
  public VerifyMobileNumberPresenter setCEAProfileDTO(String data) {
    this.mCeaNumber = data;
    return this;
  }

}
