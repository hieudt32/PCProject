package app.positiveculture.com.agent.screen.seller.verifysigning;

import android.support.v4.app.Fragment;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.eventbus.EventBusWrapper;

import app.positiveculture.com.agent.event.OtpCompleteEvent;
import app.positiveculture.com.agent.screen.seller.processtracker.ProcessTrackerPresenter;
import app.positiveculture.com.agent.utils.FragmentUtils;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * The VerifySigning Presenter
 */
public class VerifySigningPresenter extends Presenter<VerifySigningContract.View, VerifySigningContract.Interactor>
        implements VerifySigningContract.Presenter {

  private OnVerifySigningListener mOnVerifySigningListener;
  private OtpDTO mOTP;

  public VerifySigningPresenter setOnVerifySigningListener(OnVerifySigningListener mOnVerifySigningListener) {
    this.mOnVerifySigningListener = mOnVerifySigningListener;
    return this;
  }

  public VerifySigningPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public VerifySigningContract.View onCreateView() {
    return VerifySigningFragment.getInstance();
  }

  @Override
  public void start() {
    if (mOTP != null && mOTP.getProperty() != null) {
      mView.bindView(mOTP.getProperty());
    }

    if (mOnVerifySigningListener != null) {
      mView.bindViewForVerifyOfflineSigning();
    } else {
      mView.bindViewForCompleteOffline();
    }
  }

  @Override
  public void verifyOfflineSigning() {
    if (mOTP == null) return;
    mView.showProgress();
    mInteractor.verifyOfflineSigning(String.valueOf(mOTP.getId()), new CommonCallback<OtpDTO>(getViewContext()) {
      @Override
      public void onSuccess(OtpDTO data) {
        super.onSuccess(data);
        if (mOnVerifySigningListener != null) {
          mOnVerifySigningListener.onVerifySuccess(data);
          back();
        } else {
          EventBusWrapper.post(new OtpCompleteEvent());
          Fragment fragment = FragmentUtils.getProcessTrackerScreen(mView.getBaseActivity());
          if (fragment != null) {
            mContainerView.getBaseActivity().getSupportFragmentManager().popBackStackImmediate(fragment.getClass().getSimpleName(), 0);
          } else {
            new ProcessTrackerPresenter(mContainerView).pushView();
          }
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public VerifySigningContract.Interactor onCreateInteractor() {
    return new VerifySigningInteractor(this);
  }

  public VerifySigningPresenter setOtp(OtpDTO mOtpDTo) {
    this.mOTP = mOtpDTo;
    return this;
  }

  public interface OnVerifySigningListener {
    void onVerifySuccess(OtpDTO otpDTO);
  }
}
