package app.positiveculture.com.user.screen.disclaimer;

import android.os.Bundle;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.eventbus.EventBusWrapper;
import com.gemvietnam.utils.ActivityUtils;
import com.google.gson.Gson;

import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.user.Constant;
import app.positiveculture.com.user.screen.signotp.SignOTPActivity;
import app.positiveculture.com.user.userevent.SignOtpEvent;

/**
 * The Disclaimer Presenter
 */
public class DisclaimerPresenter extends Presenter<DisclaimerContract.View, DisclaimerContract.Interactor>
        implements DisclaimerContract.Presenter {

  private OtpDTO mOTP;
  private static final String KEY_OTP_OBJECT = Constant.KEY_OTP_OBJECT;
  private static final String KEY_TYPE_PROCESS = Constant.KEY_TYPE_PROCESS;

  private TypeProcess mTypeProcess;
  private boolean isFromFullContact;

  public DisclaimerPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public boolean getFromFullContact() {
    return this.isFromFullContact;
  }

  @Override
  public void backTwice() {
    mContainerView.backTwiceTimes();
  }

  @Override
  public void postEvent(OtpDTO mOTP) {
    EventBusWrapper.post(new SignOtpEvent(mOTP));
  }

  @Override
  public DisclaimerContract.View onCreateView() {
    return DisclaimerFragment.getInstance();
  }

  @Override
  public void start() {
    if (mOTP != null) mView.bindView(mOTP);
  }

  @Override
  public DisclaimerContract.Interactor onCreateInteractor() {
    return new DisclaimerInteractor(this);
  }

  @Override
  public void viewSignOTP() {
    Bundle extras = new Bundle();
    extras.putString(KEY_OTP_OBJECT, new Gson().toJson(mOTP));
    extras.putString(KEY_TYPE_PROCESS, mTypeProcess.toString());
    ActivityUtils.startActivityForResult(getViewContext(), SignOTPActivity.class, extras, Constant.SIGNED_OTP_SUCCESS);
  }

  public DisclaimerPresenter setOTP(OtpDTO mOTP, TypeProcess mTypeProcess) {
    this.mOTP = mOTP;
    this.mTypeProcess = mTypeProcess;
    return this;
  }


  public DisclaimerPresenter setFromFullContact(boolean isfromFullContact) {
    this.isFromFullContact = isfromFullContact;
    return this;
  }
}
