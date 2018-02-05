package app.positiveculture.com.user.screen.disclaimer;

import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.google.gson.Gson;

import app.positiveculture.com.agent.utils.PropertyUtils;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.user.Constant;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;
import customview.CustomHeaderView;

/**
 * The Disclaimer Fragment
 */
public class DisclaimerFragment extends ViewFragment<DisclaimerContract.Presenter> implements DisclaimerContract.View {

  @BindView(R2.id.verify_signing_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.verify_btn)
  CustomButton mNextbt;
  @BindView(R2.id.verify_signing_check_iv)
  ImageView mCheckView;
  @BindView(R2.id.verify_signing_property_name_tv)
  TextView mBuildingTv;
  @BindView(R2.id.verify_signing_property_address_tv)
  TextView mAddressTv;
  @BindView(R2.id.verify_signing_message)
  TextView mMessage;
  @BindView(R2.id.verify_signing_des)
  TextView mDes;


  @OnClick(R2.id.left_icon_iv)
  void back() {
    mPresenter.back();
  }

  @OnClick(R2.id.verify_btn)
  void viewSignOTP() {
    mPresenter.viewSignOTP();
  }

  @OnClick(R2.id.verify_signing_check_iv)
  void changeStatusButton() {
    mCheckView.setSelected(!mCheckView.isSelected());
    if (mCheckView.isSelected()) {
      mNextbt.statusEnableButton();
      mCheckView.setImageResource(R.drawable.ic_radio_selected);
    } else {
      mNextbt.statusDisabledButton();
      mCheckView.setImageResource(R.drawable.ic_radio_default);
    }
  }

  public static DisclaimerFragment getInstance() {
    return new DisclaimerFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_verify_signing;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mHeaderView.setTitle(getString(R.string.disclaimer));
    mMessage.setText(getString(R.string.declaration_owner));
    mDes.setText(getString(R.string.check_before_signing));
    mHeaderView.getLeftIconIv().setImageDrawable(getResources().getDrawable(R.drawable.ic_close_image));

    mNextbt.setTextButton(getString(R.string.next));
    mNextbt.statusDisabledButton();
  }

  @Override
  public void bindView(OtpDTO mOTP) {
    PropertyDTO propertyDTO = mOTP.getProperty();
    if (propertyDTO != null) {
      PropertyUtils.displayProperty(propertyDTO, mBuildingTv, mAddressTv);
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == Constant.SIGNED_OTP_SUCCESS) {
      if (resultCode == Activity.RESULT_OK) {
        String otpString = data.getExtras().getString(Constant.KEY_OTP_OBJECT);
        if(otpString!=null){
          mPresenter.postEvent(new Gson().fromJson(otpString, OtpDTO.class));
        }
        if (mPresenter.getFromFullContact()) {
          mPresenter.backTwice();
        } else {
          mPresenter.back();
        }
      } else if (resultCode == Activity.RESULT_CANCELED) {
        mPresenter.back();
      }
    }
  }
}
