package app.positiveculture.com.agent.screen.seller.otp;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.screen.selectowners.SelectOwnersPresenter;
import app.positiveculture.com.agent.screen.seller.otpcontract.OTPContractPresenter;
import app.positiveculture.com.agent.screen.seller.verifysigning.VerifySigningPresenter;
import app.positiveculture.com.agent.screen.seller.viewparties.ViewPartiesPresenter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The SignOTP Presenter
 */
public class OTPPresenter extends Presenter<OTPContract.View, OTPContract.Interactor>
        implements OTPContract.Presenter, VerifySigningPresenter.OnVerifySigningListener,
        SelectOwnersPresenter.OnAssignClientListener, ViewPartiesPresenter.OnReassignClientSuccessfulListener, OTPContractPresenter.OnVerifyOfflineSigningListener {

  private OtpDTO mOTP;
  private TypeProcess mTypeProcess;
  private OnOtpChangeListener mOnOtpChangeListener;

  public OTPPresenter setOnOtpChangeListener(OnOtpChangeListener mOnOtpChangeListener) {
    this.mOnOtpChangeListener = mOnOtpChangeListener;
    return this;
  }

  public OTPPresenter setOTP(OtpDTO mOTP, TypeProcess mTypeProcess) {
    this.mOTP = mOTP;
    this.mTypeProcess = mTypeProcess;
    return this;
  }

  public OTPPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public OTPContract.View onCreateView() {
    return OTPFragment.getInstance();
  }

  @Override
  public void start() {
    if (mOTP != null) {
      mView.bindView(mOTP, mTypeProcess);
    }
  }

  @Override
  public OTPContract.Interactor onCreateInteractor() {
    return new OTPInteractor(this);
  }

  @Override
  public void goToViewParties() {
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

    ViewPartiesPresenter viewPartiesPresenter = new ViewPartiesPresenter(mContainerView);
    viewPartiesPresenter.setListOwner(listOwners)
            .setTypeProcess(mTypeProcess, mOTP).setOnReassignClientSuccessfulListener(this);
    if (!listBuyer.isEmpty()) {
      viewPartiesPresenter.setListBuyer(listBuyer)
              .pushView();
    } else {
      viewPartiesPresenter.pushView();
    }
  }

  @Override
  public void goToOTPContract() {
    new OTPContractPresenter(mContainerView)
            .setOtpFromOTPScreen(mOTP, mTypeProcess)
            .setOnVerifyOfflineSigningListener(this)
            .pushView();
  }

  @Override
  public void goToVerifySigning() {
    new VerifySigningPresenter(mContainerView)
            .setOtp(mOTP)
            .setOnVerifySigningListener(this)
            .pushView();
  }

  @Override
  public void sendOutOTP() {
    mView.showProgress();
    mInteractor.sendOutOTP(String.valueOf(mOTP.getId()), new CommonCallback<OtpDTO>(getViewContext()) {
      @Override
      public void onSuccess(OtpDTO data) {
        super.onSuccess(data);
        mOTP = data;
        if (mOnOtpChangeListener != null) {
          mOnOtpChangeListener.onOtpChanged();
        }
        start();
      }
    });
  }

  @Override
  public void goToAssignBuyerScreen() {
    new SelectOwnersPresenter(mContainerView)
            .setOnAssignClientListener(this)
            .setOTP(mOTP)
            .pushView();
  }

  @Override
  public void goToCompleteScreen() {
    new VerifySigningPresenter(mContainerView)
            .setOtp(mOTP)
            .pushView();
  }

  @Override
  public void onVerifyOfflineSuccess(OtpDTO otpDTO) {
    this.mOTP = otpDTO;
    if (mOnOtpChangeListener != null) {
      mOnOtpChangeListener.onOtpChanged();
    }
    start();
  }

  @Override
  public void onAssignClientSuccess(OtpDTO otpDTO) {
    this.mOTP = otpDTO;
    if (mOnOtpChangeListener != null) {
      mOnOtpChangeListener.onOtpChanged();
    }
    start();
  }

  @Override
  public void onReassignSuccess(OtpDTO otpDTO) {
    this.mOTP = otpDTO;
    if (mOnOtpChangeListener != null) {
      mOnOtpChangeListener.onOtpChanged();
    }
    start();
  }

  @Override
  public void onVerifySuccess(OtpDTO otpDTO) {
    this.mOTP = otpDTO;
    if (mOnOtpChangeListener != null) {
      mOnOtpChangeListener.onOtpChanged();
    }
    start();
  }


  public interface OnOtpChangeListener {
    void onOtpChanged();
  }
}
