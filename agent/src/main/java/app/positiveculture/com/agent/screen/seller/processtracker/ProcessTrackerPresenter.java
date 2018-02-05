package app.positiveculture.com.agent.screen.seller.processtracker;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import org.greenrobot.eventbus.Subscribe;

import app.positiveculture.com.agent.event.OtpCompleteEvent;
import app.positiveculture.com.agent.screen.properties.detail.DetailPresenter;
import app.positiveculture.com.agent.screen.seller.congratulation.CongratulationPresenter;
import app.positiveculture.com.agent.screen.seller.conveyancing.ConveyancingPresenter;
import app.positiveculture.com.agent.screen.seller.otp.OTPPresenter;
import app.positiveculture.com.agent.screen.seller.payment.PaymentPresenter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The ProcessTracker Presenter
 */
public class ProcessTrackerPresenter extends Presenter<ProcessTrackerContract.View, ProcessTrackerContract.Interactor>
        implements ProcessTrackerContract.Presenter, DetailPresenter.OnBackToProcessTrackerAgentScreenListener, OTPPresenter.OnOtpChangeListener {

  private PropertyDTO mProperty;
  private OtpDTO mOTP;
  private TypeProcess mTypeProcess;

  public ProcessTrackerPresenter setTypeProcess(TypeProcess mTypeProcess) {
    this.mTypeProcess = mTypeProcess;
    return this;
  }

  public ProcessTrackerPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public ProcessTrackerContract.View onCreateView() {
    return ProcessTrackerFragment.getInstance();
  }

  @Override
  public void start() {
    if (mProperty == null) return;
    getOtpOfProperty(mProperty);
  }

  private void getOtpOfProperty(PropertyDTO propertyDTO) {
    mView.showProgress();
    mInteractor.getOTPOfProperty(String.valueOf(propertyDTO.getmId()), new CommonCallback<OtpDTO>(getViewContext()) {
      @Override
      public void onSuccess(OtpDTO data) {
        super.onSuccess(data);
        mView.hideProgress();
        mOTP = data;
        mView.setupData(mProperty, mOTP, mTypeProcess);
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  public ProcessTrackerPresenter setProperty(PropertyDTO property) {
    mProperty = property;
    return this;
  }

  @Override
  public ProcessTrackerContract.Interactor onCreateInteractor() {
    return new ProcessTrackerInteractor(this);
  }

  @Override
  public void goToOTP() {
    new OTPPresenter(mContainerView)
            .setOTP(mOTP, mTypeProcess)
            .setOnOtpChangeListener(this)
            .pushView();
  }

  @Override
  public void goToConveyancing() {
    new ConveyancingPresenter(mContainerView).pushView();
  }

  @Override
  public void goToPropertyDetail() {
    new DetailPresenter(mContainerView)
            .setProperty(mProperty)
            .setOTP(mOTP)
            .setOnBackToProcessTrackerAgentScreenListener(this)
            .pushView();

  }

  @Override
  public void goToPaymentScreen() {
    new PaymentPresenter(mContainerView).pushView();
  }

  @Override
  public void goToCongratulationScreen() {
    new CongratulationPresenter(mContainerView)
            .setThumbUrlProperty(mProperty.getmFeatrureImage().getImgLarge())
            .pushView();
  }

  @Override
  public void onResetNextClickAgent() {
    start();
    mView.enableClickIconNext();
  }

  @Subscribe
  public void onOtpComplete(OtpCompleteEvent otpCompleteEvent) {
    start();
  }

  @Override
  public void onOtpChanged() {
    start();
  }
}
