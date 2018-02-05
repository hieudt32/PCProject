package app.positiveculture.com.user.screen.extentotp;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * The ExtentOTP Presenter
 */
public class ExtentOTPPresenter extends Presenter<ExtentOTPContract.View, ExtentOTPContract.Interactor>
        implements ExtentOTPContract.Presenter {

  private OtpDTO mOtpDTO;
  private OnExtendOTPListener mOnExtendOTPListener;

  public ExtentOTPPresenter setOnExtendOTPListener(OnExtendOTPListener mOnExtendOTPListener) {
    this.mOnExtendOTPListener = mOnExtendOTPListener;
    return this;
  }

  public ExtentOTPPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public ExtentOTPContract.View onCreateView() {
    return ExtentOTPFragment.getInstance();
  }

  @Override
  public void start() {
    if (mOtpDTO != null) {
      mView.bindOtpInfo(mOtpDTO.getOtpDateCompletion());
    }
  }

  public ExtentOTPPresenter setOTP(OtpDTO otpDTO) {
    mOtpDTO = otpDTO;
    return this;
  }

  @Override
  public void extendOTP(String newDateCompletion) {
    mView.showProgress();
    mInteractor.extendOTP(String.valueOf(mOtpDTO.getId()), newDateCompletion, new CommonCallback<OtpDTO>(getViewContext()) {
      @Override
      public void onSuccess(OtpDTO data) {
        super.onSuccess(data);
        mOtpDTO = data;
        if (mOnExtendOTPListener != null) {
          mOnExtendOTPListener.onExtendSuccess(data);
        }
        back();
      }
    });
  }

  @Override
  public ExtentOTPContract.Interactor onCreateInteractor() {
    return new ExtentOTPInteractor(this);
  }

  public interface OnExtendOTPListener {
    void onExtendSuccess(OtpDTO otpDTO);
  }
}
