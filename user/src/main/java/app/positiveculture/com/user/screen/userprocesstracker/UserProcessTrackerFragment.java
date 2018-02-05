package app.positiveculture.com.user.screen.userprocesstracker;

import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.customview.ProcessTracker;
import app.positiveculture.com.agent.utils.OtpUtils;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.enumdata.OTPStatus;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomHeaderView;
import utils.DateTimeUtils;

/**
 * The UserProcessTracker Fragment
 */
public class UserProcessTrackerFragment extends ViewFragment<UserProcessTrackerContract.Presenter> implements UserProcessTrackerContract.View {

  @BindView(R2.id.process_tracker_property_iv)
  SimpleDraweeView mTrackerIv;
  @BindView(R2.id.right_icon_iv)
  ImageView mNextIv;
  @BindView(R2.id.process_tracker_header)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.process_tracker_sign_otp)
  ProcessTracker mSignOTPTracker;
  @BindView(R2.id.process_tracker_payment)
  ProcessTracker mPaymentTracker;
  @BindView(R2.id.status_process_tracker_tv)
  TextView mStatusTv;
  @BindView(R2.id.process_tracker_conveyancing)
  ProcessTracker mConveyancingTracker;
  @BindView(R2.id.process_tracker_complete)
  ProcessTracker mCompleteTracker;
  @BindView(R2.id.process_tracker_otp_expire_date_tv)
  TextView mOTPExpireTv;

  @OnClick(R2.id.left_icon_iv)
  public void back() {
    mPresenter.back();
  }

  @OnClick(R2.id.process_tracker_sign_otp)
  public void signOTP() {
    mPresenter.signOTP();
  }

  @OnClick(R2.id.process_tracker_conveyancing)
  public void onConveyancingClick() {
    mPresenter.goToConveyancing();
  }

  @OnClick(R2.id.right_icon_iv)
  void goToDetailScreen() {
    mNextIv.setClickable(false);
    mPresenter.goToDetailScreen();
  }

  public static UserProcessTrackerFragment getInstance() {
    return new UserProcessTrackerFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_process_tracker;
  }

  @Override
  public void initLayout() {
    super.initLayout();
  }

  @Override
  public void enableNextButton() {
    mNextIv.setClickable(true);
  }

  @Override
  public void setupData(PropertyDTO mProperty, OtpDTO mOTP, TypeProcess mTypeProcess) {
    if (mProperty.getmFeatrureImage() != null) {
      ViewUtils.loadImageWithFresco(mTrackerIv, mProperty.getmFeatrureImage().getImgMedium());
    }

    if (!StringUtils.isEmpty(mProperty.getmDisplayOne())) {
      mHeaderView.setTitle(mProperty.getmDisplayOne());
    } else if (!StringUtils.isEmpty(mProperty.getmDisplayTwo())) {
      mHeaderView.setTitle(mProperty.getmDisplayTwo());
    } else if (!StringUtils.isEmpty(mProperty.getmDisplayThree())) {
      mHeaderView.setTitle(mProperty.getmDisplayThree());
    } else if (!StringUtils.isEmpty(mProperty.getmDisplayFour())) {
      mHeaderView.setTitle(mProperty.getmDisplayFour());
    }

    if (mTypeProcess != null) {
      if (mTypeProcess == TypeProcess.Selling) {
        mStatusTv.setText(TypeProcess.Selling.toString());
      } else if (mTypeProcess == TypeProcess.Buying) {
        mStatusTv.setText(TypeProcess.Buying.toString());
      } else {
        mStatusTv.setText(TypeProcess.Completed.toString());
      }
    } else {
      mStatusTv.setText("");
    }

    if (mOTP != null) {
      String expireText = getString(R.string.otp_expires) + " " + DateTimeUtils.convertDateFormat(mOTP.getOtpDateExpiry(),
              DateTimeUtils.dateFormatFour(), DateTimeUtils.dateFormatOne());
      mOTPExpireTv.setText(expireText);
      OTPStatus otpStatus = mOTP.getStatus();
      if (otpStatus != null) {
        OtpUtils.showProcessTracker(getViewContext(),mSignOTPTracker, mPaymentTracker,
                mConveyancingTracker, mCompleteTracker, otpStatus);
      }
    }


  }
}
