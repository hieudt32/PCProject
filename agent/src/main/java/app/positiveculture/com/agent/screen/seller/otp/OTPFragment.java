package app.positiveculture.com.agent.screen.seller.otp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gemvietnam.base.viper.ViewFragment;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.ItemSummaryCustom;
import app.positiveculture.com.agent.utils.NumberUtils;
import app.positiveculture.com.agent.utils.OtpUtils;
import app.positiveculture.com.data.enumdata.MemberType;
import app.positiveculture.com.data.enumdata.OTPStatus;
import app.positiveculture.com.data.enumdata.StatusSignOTP;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.data.response.dto.SignOtpDTO;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DateTimeUtils;
import utils.DialogUtils;

/**
 * The SignOTP Fragment
 */
public class OTPFragment extends ViewFragment<OTPContract.Presenter> implements OTPContract.View {

  @BindView(R2.id.otp_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.signatures_rv)
  RecyclerView mSignaturesRv;

  @BindView(R2.id.otp_expiry_date_item)
  ItemSummaryCustom mOtpExpiryDateItem;
  @BindView(R2.id.otp_completion_date_item)
  ItemSummaryCustom mOtpCompletionDateItem;
  @BindView(R2.id.otp_total_price_item)
  ItemSummaryCustom mOtpTotalPriceItem;
  @BindView(R2.id.otp_option_fee_item)
  ItemSummaryCustom mOtpOptionFeeItem;
  @BindView(R2.id.otp_exercise_fee_item)
  ItemSummaryCustom mOtpExerciseFeeItem;

  @BindView(R2.id.view_parties_container)
  LinearLayout mViewPartiesContainer;

  @BindView(R2.id.first_owner_avatar)
  SimpleDraweeView mFirstOwnerAvt;
  @BindView(R2.id.second_owner_avatar)
  SimpleDraweeView mSecondOwnerAvt;
  @BindView(R2.id.third_owner_avatar)
  SimpleDraweeView mThirdOwnerAvt;
  @BindView(R2.id.more_owner_avatar_rl)
  RelativeLayout mMoreOwnerRl;
  @BindView(R2.id.more_owner_avatar_tv)
  TextView mMoreOwnerTv;

  @BindView(R2.id.otp_buyer_side_rl)
  RelativeLayout mBuyerSideRl;
  @BindView(R2.id.first_buyer_avatar)
  SimpleDraweeView mFirstBuyerAvt;
  @BindView(R2.id.second_buyer_avatar)
  SimpleDraweeView mSecondBuyerAvt;
  @BindView(R2.id.third_buyer_avatar)
  SimpleDraweeView mThirdBuyerAvt;
  @BindView(R2.id.more_buyer_avatar_rl)
  RelativeLayout mMoreBuyerRl;
  @BindView(R2.id.more_buyer_avatar_tv)
  TextView mMoreBuyerTv;
  @BindView(R2.id.verify_signing_btn)
  CustomButton mVerifySigningBtn;
  @BindView(R2.id.item_assign_buyer)
  LinearLayout mItemAssignBuyer;

  private OTPSignaturesAdapter mSignaturesAdapter;
  private List<MemberDTO> mListMembers = new ArrayList<>();
  private List<SimpleDraweeView> listAvatarOwners = new ArrayList<>();
  private List<SimpleDraweeView> listAvatarBuyer = new ArrayList<>();

  @OnClick(R2.id.otp_view_full_contract_ll)
  void doViewOTPContract() {
    mPresenter.goToOTPContract();
  }

  @OnClick(R2.id.item_assign_buyer)
  void doAssignBuyerClick() {
    mPresenter.goToAssignBuyerScreen();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_agent_otp;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    listAvatarOwners.add(mFirstOwnerAvt);
    listAvatarOwners.add(mSecondOwnerAvt);
    listAvatarOwners.add(mThirdOwnerAvt);
    listAvatarBuyer.add(mFirstBuyerAvt);
    listAvatarBuyer.add(mSecondBuyerAvt);
    listAvatarBuyer.add(mThirdBuyerAvt);
    mHeaderView.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.back();
      }

      @Override
      public void onRightIconClick() {

      }
    });
    mHeaderView.getRightTextTv().setVisibility(View.GONE);
    mViewPartiesContainer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.goToViewParties();
      }
    });

  }


  public static OTPFragment getInstance() {
    return new OTPFragment();
  }

  @Override
  public void bindView(OtpDTO mOTP, TypeProcess mTypeProcess) {

    String otpExpiryDate = DateTimeUtils.convertDateFormat(mOTP.getOtpDateExpiry(),
            DateTimeUtils.dateFormatFour(), DateTimeUtils.dateFormatOne());
    String otpCompletionDate = DateTimeUtils.convertDateFormat(mOTP.getOtpDateCompletion(),
            DateTimeUtils.dateFormatFour(), DateTimeUtils.dateFormatOne());
    String totalPrice = getString(R.string.ss) + NumberUtils.formatNumberTwo(mOTP.getTotalPrice());
    String optionFree = getString(R.string.ss) + NumberUtils.formatNumberTwo(mOTP.getOptionFee());
    String exerciseFee = getString(R.string.ss) + NumberUtils.formatNumberTwo(mOTP.getExerciseFee());

    List<MemberDTO> listBuyer = mOTP.getPropertyBuyer();
    List<SignOtpDTO> listSign = mOTP.getListSign();
    mOtpExpiryDateItem.setContent(otpExpiryDate);
    mOtpCompletionDateItem.setContent(otpCompletionDate);
    mOtpTotalPriceItem.setContent(totalPrice);
    mOtpOptionFeeItem.setContent(optionFree);
    mOtpExerciseFeeItem.setContent(exerciseFee);
    PropertyDTO property = mOTP.getProperty();
    if (property != null) {
      List<MemberDTO> listOwners = property.getPropertySeller();
      if (listOwners != null && !listOwners.isEmpty()) {
        for (MemberDTO memberDTO : listOwners) {
          memberDTO.setTypeInOTP(MemberType.seller);
          for (SignOtpDTO signOtpDTO : listSign) {
            if (memberDTO.getId() == signOtpDTO.getIdMember() && signOtpDTO.getStatus() == StatusSignOTP.completed) {
              memberDTO.setSelected(true);
            }
          }
        }
        mListMembers.clear();
        mListMembers.addAll(listOwners);
        mSignaturesAdapter = new OTPSignaturesAdapter(mListMembers);
        mSignaturesRv.setAdapter(mSignaturesAdapter);
        mSignaturesRv.setLayoutManager(new LinearLayoutManager(getViewContext()));
        OtpUtils.showSideOTP(listAvatarOwners, listOwners, mOTP.getSellerAgent(), mMoreOwnerRl, mMoreOwnerTv);
      }
    }

    if (listBuyer != null && !listBuyer.isEmpty()) {
      mBuyerSideRl.setVisibility(View.VISIBLE);
      for (MemberDTO memberDTO : listBuyer) {
        memberDTO.setTypeInOTP(MemberType.buyer);
        for (SignOtpDTO signOtpDTO : listSign) {
          if (memberDTO.getId() == signOtpDTO.getIdMember() && signOtpDTO.getStatus() == StatusSignOTP.completed) {
            memberDTO.setSelected(true);
          }
        }
      }
      mListMembers.addAll(listBuyer);
      mSignaturesAdapter.notifyDataSetChanged();
      OtpUtils.showSideOTP(listAvatarBuyer, listBuyer, mOTP.getBuyerAgent(), mMoreBuyerRl, mMoreBuyerTv);
    } else {
      mBuyerSideRl.setVisibility(View.GONE);
    }

    OTPStatus otpStatus = mOTP.getStatus();
    if (otpStatus != null) {
      switch (otpStatus) {
        case new_otp:
          mItemAssignBuyer.setVisibility(View.GONE);
          if (mTypeProcess == TypeProcess.Selling) {
            sellerAgentViewStatusNewOTP();
          } else {
            mVerifySigningBtn.setVisibility(View.GONE);
          }
          break;
        case owners_assigned:
          mItemAssignBuyer.setVisibility(View.GONE);
          if (mTypeProcess == TypeProcess.Selling) {
            mVerifySigningBtn.setTextButton(getString(R.string.verify_offline_signing));
            mVerifySigningBtn.statusEnableButton();
            mVerifySigningBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                mPresenter.goToVerifySigning();
              }
            });
          } else if (mTypeProcess == TypeProcess.Buying) {
            mVerifySigningBtn.setVisibility(View.GONE);
          }
          break;
        case owners_reject:
          mVerifySigningBtn.statusDisabledButton();
          mVerifySigningBtn.setTextButton(getString(R.string.owner_rejected));
          mItemAssignBuyer.setVisibility(View.GONE);
          break;
        case buyer_agent_assigned:
          if (mTypeProcess == TypeProcess.Selling) {
            mVerifySigningBtn.setTextButton(getString(R.string.pending_buyers_signing));
            mVerifySigningBtn.statusDisabledButton();
          } else if (mTypeProcess == TypeProcess.Buying) {
            buyerAgentViewStatusBuyerAgentAssigned(listBuyer);
          }
          break;
        case buyer_agent_assigned_hardcopy:
          if (mTypeProcess == TypeProcess.Selling) {
            mVerifySigningBtn.setTextButton(getString(R.string.pending_buyers_signing));
            mVerifySigningBtn.statusDisabledButton();
          } else if (mTypeProcess == TypeProcess.Buying) {
            buyerAgentViewStatusBuyerAgentAssigned(listBuyer);
          }
          break;
        case buyers_assigned:
          if (mTypeProcess == TypeProcess.Selling) {
            mVerifySigningBtn.setTextButton(getString(R.string.pending_buyers_signing));
            mVerifySigningBtn.statusDisabledButton();
          } else if (mTypeProcess == TypeProcess.Buying) {
            buyerAgentViewStatusBuyersAssigned();
          }
          break;
        case buyers_assigned_hardcopy:
          if (mTypeProcess == TypeProcess.Selling) {
            mVerifySigningBtn.setTextButton(getString(R.string.pending_buyers_signing));
            mVerifySigningBtn.statusDisabledButton();
          } else {
            buyerAgentViewStatusBuyersAssigned();
          }
          break;
        case complete:
          mVerifySigningBtn.setTextButton(getString(R.string.completed));
          mVerifySigningBtn.statusDisabledButton();
          break;
        case complete_hardcopy:
          mVerifySigningBtn.setTextButton(getString(R.string.complete_offline));
          mVerifySigningBtn.statusDisabledButton();
          break;
        case not_excerised:
          break;
        default:
          mVerifySigningBtn.setVisibility(View.GONE);
          break;
      }
    }
  }

  private void sellerAgentViewStatusNewOTP() {
    mVerifySigningBtn.setTextButton(getString(R.string.send_out_otp));
    mVerifySigningBtn.statusEnableButton();
    mVerifySigningBtn.setOnClickListener(new View.OnClickListener() {
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

  private void buyerAgentViewStatusBuyerAgentAssigned(List<MemberDTO> listBuyer) {
    mItemAssignBuyer.setVisibility(View.VISIBLE);
    if (listBuyer != null && !listBuyer.isEmpty()) {
      mVerifySigningBtn.setVisibility(View.VISIBLE);
      mVerifySigningBtn.statusEnableButton();
      mVerifySigningBtn.setTextButton(getString(R.string.send_out_otp));
      mVerifySigningBtn.setOnClickListener(new View.OnClickListener() {
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
      mVerifySigningBtn.setVisibility(View.GONE);
    }
  }

  private void buyerAgentViewStatusBuyersAssigned() {
    mItemAssignBuyer.setVisibility(View.GONE);
    mVerifySigningBtn.setTextButton(getString(R.string.complete_offline));
    mVerifySigningBtn.statusEnableButton();
    mVerifySigningBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.goToCompleteScreen();
      }
    });
  }
}
