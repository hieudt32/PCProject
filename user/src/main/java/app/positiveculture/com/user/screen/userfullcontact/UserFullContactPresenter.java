package app.positiveculture.com.user.screen.userfullcontact;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.user.screen.disclaimer.DisclaimerPresenter;

/**
 * The UserFullContact Presenter
 */
public class UserFullContactPresenter extends Presenter<UserFullContactContract.View, UserFullContactContract.Interactor>
        implements UserFullContactContract.Presenter {

  private OtpDTO mOTP;
  private TypeProcess mTypeProgress;
  private OnUserViewOtpContactListener mOnUserViewOtpContactListener;

  public UserFullContactPresenter setOnUserViewOtpContactListener(OnUserViewOtpContactListener mOnUserViewOtpContactListener) {
    this.mOnUserViewOtpContactListener = mOnUserViewOtpContactListener;
    return this;
  }

  public UserFullContactPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public UserFullContactContract.View onCreateView() {
    return UserFullContactFragment.getInstance();
  }

  @Override
  public void start() {
    if (mOTP != null) {
      mView.bindView(mOTP, mTypeProgress);
    }
  }

  @Override
  public UserFullContactContract.Interactor onCreateInteractor() {
    return new UserFullContactInteractor(this);
  }

  @Override
  public void viewDisclaimer() {
    new DisclaimerPresenter(mContainerView)
            .setOTP(mOTP, mTypeProgress)
            .setFromFullContact(true)
            .pushView();
  }

  @Override
  public void rejectOTP() {
    if (mOTP == null) return;
    mView.showProgress();
    mInteractor.rejectOTP(String.valueOf(mOTP.getId()), new CommonCallback<OtpDTO>(getViewContext()) {
      @Override
      public void onSuccess(OtpDTO data) {
        super.onSuccess(data);
        mOTP = data;
        mView.bindView(mOTP, mTypeProgress);
      }
    });
  }

  @Override
  public void backToOTP() {
    if (mOnUserViewOtpContactListener != null) {
      mOnUserViewOtpContactListener.onViewSuccess(mOTP);
    }
    back();
  }

  public UserFullContactPresenter setOTP(OtpDTO mOTP, TypeProcess mTypeProcess) {
    this.mOTP = mOTP;
    this.mTypeProgress = mTypeProcess;
    return this;
  }

  public interface OnUserViewOtpContactListener {
    void onViewSuccess(OtpDTO otpDTO);
  }
}
