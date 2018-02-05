package app.positiveculture.com.user.screen.userfullcontact;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.Constant;
import app.positiveculture.com.agent.utils.OtpUtils;
import app.positiveculture.com.agent.utils.PropertyUtils;
import app.positiveculture.com.data.enumdata.OTPStatus;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

import static app.positiveculture.com.agent.Constant.LINK_LOAD_PDF;

/**
 * The UserFullContact Fragment
 */
public class UserFullContactFragment extends ViewFragment<UserFullContactContract.Presenter> implements UserFullContactContract.View {

  @BindView(R2.id.verify_otp_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.building_otp_contact_tv)
  TextView mBuildingTv;
  @BindView(R2.id.address_otp_contact_tv)
  TextView mAddressTv;
  @BindView(R2.id.verify_otp_btn)
  CustomButton mSignBt;
  @BindView(R2.id.otp_contract_wb)
  WebView mWebView;

  @OnClick(R2.id.left_icon_iv)
  public void back() {
    mPresenter.backToOTP();
  }

  @OnClick(R2.id.verify_otp_btn)
  public void viewDisclaimer() {
    mPresenter.viewDisclaimer();
  }

  @OnClick(R2.id.right_text_tv)
  public void showDialogReject() {
    DialogUtils.showDialog(getViewContext(), getViewContext().getString(R.string.reject_otp), getViewContext().getString(R.string.reject_otp_des), new CustomDialog.OnConfirmSelected() {
      @Override
      public void onConfirmSelected() {
        mPresenter.rejectOTP();
      }
    }, new CustomDialog.OnCancelSelected() {
      @Override
      public void onCancelSelected() {

      }
    });
  }

  public static UserFullContactFragment getInstance() {
    return new UserFullContactFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_verify_otp;
  }

  @SuppressLint("SetJavaScriptEnabled")
  @Override
  public void initLayout() {
    super.initLayout();
    mSignBt.setTextButton(getString(R.string.sign_otp));
    mHeaderView.getRightTextTv().setVisibility(View.GONE);
    mWebView.getSettings().setJavaScriptEnabled(true);
  }

  @Override
  public void bindView(OtpDTO mOTP, TypeProcess mTypeProgress) {
    PropertyDTO propertyDTO = mOTP.getProperty();
    if (propertyDTO != null) {
      PropertyUtils.displayProperty(propertyDTO, mBuildingTv, mAddressTv);
    }

    OTPStatus otpStatus = mOTP.getStatus();
    if (otpStatus != null) {
      OtpUtils.userViewOTP(getViewContext(), otpStatus, mTypeProgress, mSignBt, mHeaderView, PrefWrapper.getMember(), mOTP.getListSign());
    }

    //todo set link otp contact

    final String pdf = "http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf";

    mWebView.setWebViewClient(new WebViewClient() {
      @Override
      public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        showProgress();
      }

      @Override
      public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        hideProgress();
      }
    });
    mWebView.loadUrl(LINK_LOAD_PDF + pdf);
  }
}
