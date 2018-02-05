package app.positiveculture.com.user.screen.userotp;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.user.screen.disclaimer.DisclaimerPresenter;
import app.positiveculture.com.user.screen.extentotp.ExtentOTPPresenter;
import app.positiveculture.com.user.screen.userfullcontact.UserFullContactPresenter;
import app.positiveculture.com.user.screen.userviewparties.UserViewPartiesPresenter;
import app.positiveculture.com.user.userevent.SignOtpEvent;

/**
 * The UserOTP Presenter
 */
public class UserOTPPresenter extends Presenter<UserOTPContract.View, UserOTPContract.Interactor>
        implements UserOTPContract.Presenter, UserFullContactPresenter.OnUserViewOtpContactListener, ExtentOTPPresenter.OnExtendOTPListener {

  private OtpDTO mOTP;
  private TypeProcess mTypeProcess;
  private OnOTPChangeListener mOnOTPChangeListener;

  public UserOTPPresenter setOnOTPChangeListener(OnOTPChangeListener mOnOTPChangeListener) {
    this.mOnOTPChangeListener = mOnOTPChangeListener;
    return this;
  }

  public UserOTPPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public UserOTPContract.View onCreateView() {
    return UserOTPFragment.getInstance();
  }

  @Override
  public void start() {
    if (mOTP != null) {
      mView.bindView(mOTP, mTypeProcess);
    }
  }

  @Override
  public UserOTPContract.Interactor onCreateInteractor() {
    return new UserOTPInteractor(this);
  }


  @Override
  public void showDisclaimer() {
    new DisclaimerPresenter(mContainerView)
            .setOTP(mOTP, mTypeProcess)
            .pushView();
  }

  @Override
  public void viewFullContact() {
    new UserFullContactPresenter(mContainerView)
            .setOTP(mOTP, mTypeProcess)
            .setOnUserViewOtpContactListener(this)
            .pushView();
  }

  @Override
  public void extentOTP() {
    new ExtentOTPPresenter(mContainerView)
            .setOTP(mOTP)
            .setOnExtendOTPListener(this)
            .pushView();
  }

  public UserOTPPresenter setOTP(OtpDTO mOTP, TypeProcess mTypeProcess) {
    this.mOTP = mOTP;
    this.mTypeProcess = mTypeProcess;
    return this;
  }

  @Override
  public void onViewSuccess(OtpDTO otpDTO) {
    this.mOTP = otpDTO;
    if (mOTP != null) mView.bindView(mOTP, mTypeProcess);
  }

  @Override
  public void goBack() {
    if (mOnOTPChangeListener != null) {
      mOnOTPChangeListener.onChangeOTP();
    }
    back();
  }

  @Override
  public void goToUserViewParties() {
    if (mOTP == null) return;
    List<User> listOwners = new ArrayList<>();
    if (mOTP.getProperty() != null) {
      if (mOTP.getProperty().getPropertySeller() != null) {
        listOwners.addAll(mOTP.getProperty().getPropertySeller());
      }
      if (mOTP.getProperty().getmAgent() != null) {
        listOwners.add(mOTP.getProperty().getmAgent());
      }
    }

    List<User> listBuyer = new ArrayList<>();
    if (mOTP.getPropertyBuyer() != null && !mOTP.getPropertyBuyer().isEmpty()) {
      listBuyer.addAll(mOTP.getPropertyBuyer());
      if (mOTP.getBuyerAgent() != null) {
        listBuyer.add(mOTP.getBuyerAgent());
      }
    }

    UserViewPartiesPresenter userPartiesPresenter = new UserViewPartiesPresenter(mContainerView);
    userPartiesPresenter.setListOwner(listOwners)
            .setOTP(mOTP, mTypeProcess);
    if (!listBuyer.isEmpty()) {
      userPartiesPresenter.setListBuyer(listBuyer)
              .pushView();
    } else {
      userPartiesPresenter.pushView();
    }
  }

  @Subscribe
  public void onUserSignedOTP(SignOtpEvent signOtpEvent) {
    if (signOtpEvent != null && signOtpEvent.getOTP() != null) {
      this.mOTP = signOtpEvent.getOTP();
      mView.bindView(mOTP, mTypeProcess);
    }
  }

  @Override
  public void onExtendSuccess(OtpDTO otpDTO) {
    this.mOTP = otpDTO;
    start();
  }

  public interface OnOTPChangeListener {
    void onChangeOTP();
  }
}
