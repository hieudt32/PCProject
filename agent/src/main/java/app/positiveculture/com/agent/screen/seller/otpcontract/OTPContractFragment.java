package app.positiveculture.com.agent.screen.seller.otpcontract;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;

import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.utils.PropertyUtils;
import app.positiveculture.com.data.enumdata.OTPStatus;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

import static app.positiveculture.com.agent.Constant.LINK_LOAD_PDF;

/**
 * The VerifyOTP Fragment
 */
public class OTPContractFragment extends ViewFragment<OTPContractContract.Presenter> implements OTPContractContract.View {

  @BindView(R2.id.verify_otp_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.building_otp_contact_tv)
  TextView mBuildingTv;
  @BindView(R2.id.address_otp_contact_tv)
  TextView mAddressTv;
  @BindView(R2.id.verify_otp_btn)
  CustomButton mButtonCb;
  @BindView(R2.id.otp_contract_wb)
  WebView mWebView;


  @OnClick(R2.id.right_text_tv)
  void doEditClick() {
    mPresenter.goToCreateOTP();
  }

  @OnClick(R2.id.left_text_tv)
  void doDeleteClick() {
    DialogUtils.showDialog(getViewContext(), getString(R.string.delete), getString(R.string.delete_otp_mes),
            new CustomDialog.OnConfirmSelected() {
              @Override
              public void onConfirmSelected() {
                mPresenter.deleteOTP();
              }
            },
            new CustomDialog.OnCancelSelected() {
              @Override
              public void onCancelSelected() {

              }
            });
  }

  @OnClick(R2.id.left_icon_iv)
  void doBackIconClick() {
    mPresenter.back();
  }

  public static OTPContractFragment getInstance() {
    return new OTPContractFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_verify_otp;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mHeaderView.getRightTextTv().setVisibility(View.GONE);
    mWebView.getSettings().setJavaScriptEnabled(true);
  }

  @Override
  public void bindDataFromCreateOTP(OtpDTO mOtpDTo) {
    PropertyDTO propertyDTO = mOtpDTo.getProperty();
    if (propertyDTO != null) {
      PropertyUtils.displayProperty(propertyDTO, mBuildingTv, mAddressTv);
    }

  }

  @Override
  public void bindViewFromOTPScreen(OtpDTO otpDTO, TypeProcess mTypeProcess) {
    mButtonCb.setTextButton(getString(R.string.verify_offline_signing));
    mButtonCb.statusEnableButton();
    mHeaderView.getLeftTextTv().setVisibility(View.GONE);
    mHeaderView.getLeftIconIv().setVisibility(View.VISIBLE);
    mHeaderView.getRightTextTv().setVisibility(View.GONE);
    OTPStatus otpStatus = otpDTO.getStatus();
    List<MemberDTO> listBuyer = otpDTO.getPropertyBuyer();
    if (otpStatus != null) {
      switch (otpStatus) {
        case new_otp:
          if (mTypeProcess == TypeProcess.Selling) {
            sellerBuyerShowStatusNewOTP();
          } else {
            mButtonCb.setVisibility(View.GONE);
          }
          break;
        case owners_assigned:
          if (mTypeProcess == TypeProcess.Selling) {
            sellerAgentShowStatusOwnersAssigned();
          } else {
            mButtonCb.setVisibility(View.GONE);
          }
          break;
        case owners_reject:
          if (mTypeProcess == TypeProcess.Selling) {
            mButtonCb.statusDisabledButton();
            mButtonCb.setTextButton(getString(R.string.owner_rejected));
          } else {
            mButtonCb.setVisibility(View.GONE);
          }
          break;
        case buyer_agent_assigned:
          if (mTypeProcess == TypeProcess.Selling) {
            mButtonCb.setTextButton(getString(R.string.pending_buyers_signing));
            mButtonCb.statusDisabledButton();
          } else {
            buyerAgentShowStatusBuyerAgentAssigned(listBuyer);
          }
          break;
        case buyer_agent_assigned_hardcopy:
          if (mTypeProcess == TypeProcess.Selling) {
            mButtonCb.setTextButton(getString(R.string.pending_buyers_signing));
            mButtonCb.statusDisabledButton();
          } else {
            buyerAgentShowStatusBuyerAgentAssigned(listBuyer);
          }
          break;
        case buyers_assigned:
          if (mTypeProcess == TypeProcess.Selling) {
            mButtonCb.setTextButton(getString(R.string.pending_buyers_signing));
            mButtonCb.statusDisabledButton();
          } else {
            buyerAgentShowStatusBuyerAssigned();
          }
          break;
        case buyers_assigned_hardcopy:
          if (mTypeProcess == TypeProcess.Selling) {
            mButtonCb.setTextButton(getString(R.string.pending_buyers_signing));
            mButtonCb.statusDisabledButton();
          } else {
            buyerAgentShowStatusBuyerAssigned();
          }
          break;
        case complete:
          mButtonCb.setTextButton(getString(R.string.completed));
          mButtonCb.statusDisabledButton();
          break;
        case complete_hardcopy:
          mButtonCb.setTextButton(getString(R.string.complete_offline));
          mButtonCb.statusDisabledButton();
          break;
        case not_excerised:
          break;
        default:
          mButtonCb.setVisibility(View.GONE);
          break;
      }
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

  private void buyerAgentShowStatusBuyerAssigned() {
    mButtonCb.setTextButton(getString(R.string.complete_offline));
    mButtonCb.statusEnableButton();
    mButtonCb.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.goToCompleteOffline();
      }
    });
  }

  private void buyerAgentShowStatusBuyerAgentAssigned(List<MemberDTO> listBuyer) {
    if (listBuyer != null && !listBuyer.isEmpty()) {
      mButtonCb.setVisibility(View.VISIBLE);
      mButtonCb.statusEnableButton();
      mButtonCb.setTextButton(getString(R.string.send_out_otp));
      mButtonCb.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          DialogUtils.showDialog(getViewContext(), getString(R.string.send_out_otp),
                  getString(R.string.this_will_assign_otp_to_the_selected),
                  new CustomDialog.OnConfirmSelected() {
                    @Override
                    public void onConfirmSelected() {
                      mPresenter.sendOutOTP();
                    }
                  }, new CustomDialog.OnCancelSelected() {
                    @Override
                    public void onCancelSelected() {

                    }
                  });
        }
      });
    } else {
      mButtonCb.setVisibility(View.GONE);
    }
  }

  private void sellerBuyerShowStatusNewOTP() {
    mButtonCb.setTextButton(getString(R.string.send_out_otp));
    mButtonCb.statusEnableButton();
    mButtonCb.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DialogUtils.showDialog(getViewContext(), getString(R.string.send_out_otp), getString(R.string.owner_will_receive_otp),
                new CustomDialog.OnConfirmSelected() {
                  @Override
                  public void onConfirmSelected() {
                    mPresenter.sendOutOTP();
                  }
                }, new CustomDialog.OnCancelSelected() {
                  @Override
                  public void onCancelSelected() {

                  }
                });
      }
    });
  }

  private void sellerAgentShowStatusOwnersAssigned() {
    mButtonCb.setTextButton(getString(R.string.verify_offline_signing));
    mButtonCb.statusEnableButton();
    mButtonCb.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.goToVerifySigning();
      }
    });
  }

  @Override
  public void binViewFromCreateOTP() {
    mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
    mHeaderView.getRightTextTv().setText(getString(R.string.edit));
    mButtonCb.setTextButton(getString(R.string.send_out_otp));
    mButtonCb.statusEnableButton();
    mHeaderView.getLeftIconIv().setVisibility(View.GONE);
    mHeaderView.getLeftTextTv().setVisibility(View.VISIBLE);
    mHeaderView.getLeftTextTv().setText(getString(R.string.delete));
    mButtonCb.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DialogUtils.showDialog(getViewContext(), getString(R.string.send_out_otp), getString(R.string.owner_will_receive_otp),
                new CustomDialog.OnConfirmSelected() {
                  @Override
                  public void onConfirmSelected() {
                    mPresenter.sendOutOTP();
                  }
                }, new CustomDialog.OnCancelSelected() {
                  @Override
                  public void onCancelSelected() {

                  }
                });
      }
    });
  }
}
