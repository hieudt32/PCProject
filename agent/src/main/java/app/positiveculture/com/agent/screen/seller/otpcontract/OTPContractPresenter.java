package app.positiveculture.com.agent.screen.seller.otpcontract;


import android.support.v4.app.Fragment;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.eventbus.EventBusWrapper;

import app.positiveculture.com.agent.event.DeleteOtpEvent;
import app.positiveculture.com.agent.event.OtpSentOutEvent;
import app.positiveculture.com.agent.screen.main.MainPresenterAgent;
import app.positiveculture.com.agent.screen.properties.createotp.CreateOtpPresenter;
import app.positiveculture.com.agent.screen.seller.verifysigning.VerifySigningPresenter;
import app.positiveculture.com.agent.utils.FragmentUtils;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;

/**
 * The VerifyOTP Presenter
 */
public class OTPContractPresenter extends Presenter<OTPContractContract.View, OTPContractContract.Interactor>
        implements OTPContractContract.Presenter, VerifySigningPresenter.OnVerifySigningListener {

  private OtpDTO mOtpDTo;
  private TypeProcess mTypeProcess;
  private OnVerifyOfflineSigningListener mOnVerifyOfflineSigningListener;

  public OTPContractPresenter setOnVerifyOfflineSigningListener(OnVerifyOfflineSigningListener mOnVerifyOfflineSigningListener) {
    this.mOnVerifyOfflineSigningListener = mOnVerifyOfflineSigningListener;
    return this;
  }

  public OTPContractPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public OTPContractContract.View onCreateView() {
    return OTPContractFragment.getInstance();
  }

  @Override
  public void start() {
    if (mOtpDTo != null) {
      mView.bindDataFromCreateOTP(mOtpDTo);
    }
    if (mTypeProcess != null) {
      mView.bindViewFromOTPScreen(mOtpDTo, mTypeProcess);
    } else {
      mView.binViewFromCreateOTP();
    }
  }

  @Override
  public OTPContractContract.Interactor onCreateInteractor() {
    return new OTPContractInteractor(this);
  }

  @Override
  public void goToVerifySigning() {
    new VerifySigningPresenter(mContainerView)
            .setOtp(mOtpDTo)
            .setOnVerifySigningListener(this)
            .pushView();
  }

  @Override
  public void sendOutOTP() {
    mView.showProgress();
    mInteractor.sendOutOTP(String.valueOf(mOtpDTo.getId()), new CommonCallback<OtpDTO>(getViewContext()) {
      @Override
      public void onSuccess(OtpDTO data) {
        super.onSuccess(data);
        mView.hideProgress();
        mOtpDTo = data;
        if (mTypeProcess == null) {
          EventBusWrapper.post(new OtpSentOutEvent());
          backToHome();
        } else {
          if (mOnVerifyOfflineSigningListener != null) {
            mOnVerifyOfflineSigningListener.onVerifyOfflineSuccess(data);
          }
          back();
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public void goToCompleteOffline() {
    new VerifySigningPresenter(mContainerView)
            .setOtp(mOtpDTo)
            .pushView();
  }

  @Override
  public void deleteOTP() {
    mView.showProgress();
    mInteractor.deleteOTP(String.valueOf(mOtpDTo.getId()), new CommonCallback<ResponseDTO>(getViewContext()) {
      @Override
      public void onSuccess(ResponseDTO data) {
        super.onSuccess(data);
        backToHome();
      }
    });
  }

  @Override
  public void goToCreateOTP() {
    new CreateOtpPresenter(mContainerView)
            .setPropertyDTO(mOtpDTo.getProperty())
            .setOTP(mOtpDTo)
            .pushView();
  }

  @Override
  public void backToHome() {
    Fragment fragment = FragmentUtils.getFragmentMain(mContainerView.getBaseActivity());
    EventBusWrapper.post(new DeleteOtpEvent());
    if (fragment != null) {
      mContainerView.getBaseActivity().getSupportFragmentManager().popBackStackImmediate(fragment.getClass().getSimpleName(), 0);
    } else {
      new MainPresenterAgent(mContainerView)
              .pushView();
    }
  }

  public OTPContractPresenter setOtpDTO(OtpDTO data) {
    mOtpDTo = data;
    return this;
  }

  public OTPContractPresenter setOtpFromOTPScreen(OtpDTO mOTP, TypeProcess typeProcess) {
    this.mOtpDTo = mOTP;
    this.mTypeProcess = typeProcess;
    return this;
  }

  @Override
  public void onVerifySuccess(OtpDTO otpDTO) {
    this.mOtpDTo = otpDTO;
    if (mOnVerifyOfflineSigningListener != null) {
      mOnVerifyOfflineSigningListener.onVerifyOfflineSuccess(mOtpDTo);
    }
    start();
  }

  public interface OnVerifyOfflineSigningListener {
    void onVerifyOfflineSuccess(OtpDTO otpDTO);
  }
}
