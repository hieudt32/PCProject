package app.positiveculture.com.user.screen.userprocesstracker;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.agent.screen.properties.detail.DetailPresenter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.user.screen.userconveyancing.UserConveyancingPresenter;
import app.positiveculture.com.user.screen.userotp.UserOTPPresenter;

/**
 * The UserProcessTracker Presenter
 */
public class UserProcessTrackerPresenter extends Presenter<UserProcessTrackerContract.View, UserProcessTrackerContract.Interactor>
        implements UserProcessTrackerContract.Presenter, DetailPresenter.OnBackToProcessTrackerUserScreenListener, UserOTPPresenter.OnOTPChangeListener {

  private PropertyDTO mProperty;
  private OtpDTO mOTP;
  private TypeProcess mTypeProcess;

  public UserProcessTrackerPresenter setTypeProcess(TypeProcess mTypeProcess) {
    this.mTypeProcess = mTypeProcess;
    return this;
  }

  public UserProcessTrackerPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public UserProcessTrackerContract.View onCreateView() {
    return UserProcessTrackerFragment.getInstance();
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

  @Override
  public UserProcessTrackerContract.Interactor onCreateInteractor() {
    return new UserProcessTrackerInteractor(this);
  }

  @Override
  public void goToDetailScreen() {
    new DetailPresenter(mContainerView)
            .setProperty(mProperty)
            .setOnBackToProcessTrackerUserScreenListener(this)
            .pushView();
  }

  @Override
  public void goToConveyancing() {
    new UserConveyancingPresenter(mContainerView).pushView();
  }

  @Override
  public void signOTP() {
    new UserOTPPresenter(mContainerView)
            .setOTP(mOTP, mTypeProcess)
            .setOnOTPChangeListener(this)
            .pushView();
  }

  @Override
  public void onResetNextClickUser() {
    mView.enableNextButton();
  }

  public UserProcessTrackerPresenter setProperty(PropertyDTO propertyDTO) {
    this.mProperty = propertyDTO;
    return this;
  }

  @Override
  public void onChangeOTP() {
    start();
  }
}
