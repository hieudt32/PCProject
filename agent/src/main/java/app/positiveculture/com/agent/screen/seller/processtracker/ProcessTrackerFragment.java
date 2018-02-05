package app.positiveculture.com.agent.screen.seller.processtracker;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;

import java.util.Date;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.ProcessTracker;
import app.positiveculture.com.agent.utils.OtpUtils;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.enumdata.OTPStatus;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomHeaderView;
import utils.DateTimeUtils;

/**
 * The ProcessTracker Fragment
 */
public class ProcessTrackerFragment extends ViewFragment<ProcessTrackerContract.Presenter> implements ProcessTrackerContract.View {

  @BindView(R2.id.process_tracker_header)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.process_tracker_property_iv)
  SimpleDraweeView mPropertyIv;
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
  @BindView(R2.id.right_icon_iv)
  ImageView mNextIconIv;
  @BindView(R2.id.process_tracker_otp_expire_date_tv)
  TextView mOTPExpireTv;

  @OnClick(R2.id.right_icon_iv)
  void doNextIconClick() {
    mNextIconIv.setClickable(false);
    mPresenter.goToPropertyDetail();
  }

  @OnClick(R2.id.process_tracker_payment)
  void doPaymentTrackerClick() {
    mPresenter.goToPaymentScreen();
  }

  @OnClick(R2.id.process_tracker_complete)
  void doCompleteTrackerClick() {
    mPresenter.goToCongratulationScreen();
  }

  public static ProcessTrackerFragment getInstance() {
    return new ProcessTrackerFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_process_tracker;
  }

  @Override
  public void initLayout() {
    super.initLayout();

    mHeaderView.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.back();
      }

      @Override
      public void onRightIconClick() {

      }
    });
    mSignOTPTracker.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.goToOTP();
      }
    });

    mConveyancingTracker.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.goToConveyancing();
      }
    });

    mCompleteTracker.getNextIv().setVisibility(View.GONE);

  }

  @Override
  public void setupData(PropertyDTO property, OtpDTO otpDTO, TypeProcess typeProcess) {

    if (typeProcess != null) {
      if (typeProcess == TypeProcess.Selling) {
        mStatusTv.setText(TypeProcess.Selling.toString());
      } else if (typeProcess == TypeProcess.Buying) {
        mStatusTv.setText(TypeProcess.Buying.toString());
      } else {
        mStatusTv.setText(TypeProcess.Completed.toString());
      }
    } else {
      mStatusTv.setText("");
    }

    ViewUtils.loadImageWithFresco(mPropertyIv, property.getmFeatrureImage().getImgMedium());
    if (!StringUtils.isEmpty(property.getmDisplayOne())) {
      mHeaderView.setTitle(property.getmDisplayOne());
    } else if (!StringUtils.isEmpty(property.getmDisplayTwo())) {
      mHeaderView.setTitle(property.getmDisplayTwo());
    } else if (!StringUtils.isEmpty(property.getmDisplayThree())) {
      mHeaderView.setTitle(property.getmDisplayThree());
    } else if (!StringUtils.isEmpty(property.getmDisplayFour())) {
      mHeaderView.setTitle(property.getmDisplayFour());
    }

    if (otpDTO != null) {
      String expireText = getString(R.string.otp_expires) + " " + DateTimeUtils.convertDateFormat(otpDTO.getOtpDateExpiry(),
              DateTimeUtils.dateFormatFour(), DateTimeUtils.dateFormatOne());
      mOTPExpireTv.setText(expireText);
      OTPStatus otpStatus = otpDTO.getStatus();
      if (otpStatus != null) {
        OtpUtils.showProcessTracker(getViewContext(), mSignOTPTracker, mPaymentTracker,
                mConveyancingTracker, mCompleteTracker, otpStatus);
      }
    }
  }

  @Override
  public void enableClickIconNext() {
    mNextIconIv.setClickable(true);
  }
}
