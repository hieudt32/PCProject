package app.positiveculture.com.agent.utils;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.customview.ProcessTracker;
import app.positiveculture.com.data.enumdata.OTPStatus;
import app.positiveculture.com.data.enumdata.StatusSignOTP;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.SignOtpDTO;
import customview.CustomButton;
import customview.CustomHeaderView;

/**
 * OtpUtils
 * Created by hieudt on 11/27/2017.
 */

public class OtpUtils {
  public static void showSideOTP(List<SimpleDraweeView> listAvatar, List<MemberDTO> listMember, AgentDTO agent,
                                 RelativeLayout moreRl, TextView moreTv) {
    showAllSide(listAvatar, moreRl, moreTv);
    if (listMember.size() > 2) {
      for (int i = 0; i <= 2; i++) {
        if (listMember.get(i).getAvatar() != null) {
          ViewUtils.loadAvatarWithFresco(listAvatar.get(i), listMember.get(i).getAvatar().getImgSmall());
        } else {
          listAvatar.get(i).setImageResource(R.drawable.ic_avatar);
        }
      }
      String moreOwners = "+" + (listMember.size() - 2);
      moreTv.setText(moreOwners);
    } else if (listMember.size() == 2) {
      for (int i = 0; i < 2; i++) {
        if (listMember.get(i).getAvatar() != null) {
          ViewUtils.loadAvatarWithFresco(listAvatar.get(i), listMember.get(i).getAvatar().getImgSmall());
        }
      }
      if (agent != null && agent.getAvatar() != null)
        ViewUtils.loadAvatarWithFresco(listAvatar.get(2), agent.getAvatar().getImgSmall());
      moreRl.setVisibility(View.GONE);
      moreTv.setVisibility(View.GONE);
    } else {
      if (listMember.get(0).getAvatar() != null)
        ViewUtils.loadAvatarWithFresco(listAvatar.get(0), listMember.get(0).getAvatar().getImgSmall());
      if (agent != null && agent.getAvatar() != null)
        ViewUtils.loadAvatarWithFresco(listAvatar.get(1), agent.getAvatar().getImgSmall());
      listAvatar.get(2).setVisibility(View.GONE);
      moreRl.setVisibility(View.GONE);
      moreTv.setVisibility(View.GONE);
    }
  }

  private static void showAllSide(List<SimpleDraweeView> listAvatar, RelativeLayout moreRl, TextView moreTv) {
    for (SimpleDraweeView simpleDraweeView : listAvatar) {
      simpleDraweeView.setVisibility(View.VISIBLE);
    }
    moreRl.setVisibility(View.VISIBLE);
    moreTv.setVisibility(View.VISIBLE);
  }

  public static void userViewOTP(Context context, OTPStatus otpStatus, TypeProcess mTypeProcess,
                                 CustomButton button, CustomHeaderView mHeaderView,
                                 MemberDTO memberDTO, List<SignOtpDTO> listSign) {
    switch (otpStatus) {
      case new_otp:
        button.setVisibility(View.GONE);
        break;
      case owners_assigned:
        if (mTypeProcess == TypeProcess.Selling) {
          if (checkUserSigned(memberDTO, listSign)) {
            button.setTextButton(context.getString(R.string.you_signed_otp));
            button.statusDisabledButton();
          } else {
            button.setTextButton(context.getString(R.string.sign_otp));
            button.statusEnableButton();
          }
        } else {
          button.setVisibility(View.GONE);
        }
        break;
      case owners_reject:
        if (mTypeProcess == TypeProcess.Selling) {
          button.setTextButton(context.getString(R.string.pending_agent_update));
          button.statusDisabledButton();
          mHeaderView.getRightTextTv().setVisibility(View.GONE);
        } else {
          button.setVisibility(View.GONE);
        }
        break;
      case buyer_agent_assigned:
        if (mTypeProcess == TypeProcess.Selling) {
          button.setTextButton(context.getString(R.string.pending_buyers_signing));
          button.statusDisabledButton();
          mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
        } else {
          button.setVisibility(View.GONE);
        }
        break;
      case buyer_agent_assigned_hardcopy:
        mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
        if (mTypeProcess == TypeProcess.Selling) {
          button.setTextButton(context.getString(R.string.pending_buyers_signing));
          button.statusDisabledButton();
          mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
        } else {
          button.setVisibility(View.GONE);
        }
        break;
      case buyers_assigned:
        if (mTypeProcess == TypeProcess.Selling) {
          button.setTextButton(context.getString(R.string.pending_buyers_signing));
          button.statusDisabledButton();
          mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
        } else if (mTypeProcess == TypeProcess.Buying) {
          if (checkUserSigned(memberDTO, listSign)) {
            button.setTextButton(context.getString(R.string.you_signed_otp));
            button.statusDisabledButton();
          } else {
            button.setTextButton(context.getString(R.string.sign_otp));
            button.statusEnableButton();
          }
        } else {
          button.setVisibility(View.GONE);
        }
        break;
      case buyers_assigned_hardcopy:
        if (mTypeProcess == TypeProcess.Selling) {
          button.setTextButton(context.getString(R.string.pending_buyers_signing_hard_copy));
          button.statusDisabledButton();
          mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
        } else if (mTypeProcess == TypeProcess.Buying) {
          button.setTextButton(context.getString(R.string.sign_otp_hard_copy));
          button.statusDisabledButton();
        } else {
          button.setVisibility(View.GONE);
        }
        break;
      case complete:
        mHeaderView.getRightTextTv().setVisibility(View.GONE);
        if (mTypeProcess == TypeProcess.Completed) {
          button.setVisibility(View.GONE);
        } else {
          button.statusDisabledButton();
          button.setTextButton(context.getString(R.string.completed));
        }
        break;
      case complete_hardcopy:
        mHeaderView.getRightTextTv().setVisibility(View.GONE);
        if (mTypeProcess == TypeProcess.Completed) {
          button.setVisibility(View.GONE);
        } else {
          button.statusDisabledButton();
          button.setTextButton(context.getString(R.string.complete_offline));
        }
        break;
      case not_excerised:
        mHeaderView.getRightTextTv().setVisibility(View.GONE);
        button.setVisibility(View.GONE);
        break;
      default:
        mHeaderView.getRightTextTv().setVisibility(View.GONE);
        button.setVisibility(View.GONE);
        break;
    }
  }

  private static boolean checkUserSigned(MemberDTO memberDTO, List<SignOtpDTO> listSign) {
    if (memberDTO == null) return true;
    for (SignOtpDTO signOtpDTO : listSign) {
      if (memberDTO.getId() == signOtpDTO.getIdMember() && signOtpDTO.getStatus() == StatusSignOTP.completed) {
        return true;
      }
    }
    return false;
  }

  public static void showProcessTracker(Context context, ProcessTracker signTracker, ProcessTracker paymentTracker,
                                        ProcessTracker conveyancingTracker, ProcessTracker completeTracker, OTPStatus otpStatus) {
    switch (otpStatus) {
      case new_otp:
        break;
      case owners_assigned:
        signTracker.setInProgressStatus(signTracker.getOverallStatusIv());
        signTracker.setInProgressStatus(signTracker.getFirstInnerStatusIv());
        paymentTracker.setClickable(false);
        conveyancingTracker.setClickable(false);
        completeTracker.setClickable(false);
        break;
      case owners_reject:
        break;
      case buyer_agent_assigned:
        signTracker.setInProgressStatus(signTracker.getOverallStatusIv());
        signTracker.setCompletedStatus(signTracker.getFirstInnerStatusIv());
        signTracker.setNoProgressStatus(signTracker.getSecondInnerStatusIv());
        paymentTracker.setClickable(false);
        conveyancingTracker.setClickable(false);
        completeTracker.setClickable(false);
        break;
      case buyer_agent_assigned_hardcopy:
        signTracker.setInProgressStatus(signTracker.getOverallStatusIv());
        signTracker.setCompletedStatus(signTracker.getFirstInnerStatusIv());
        signTracker.setNoProgressStatus(signTracker.getSecondInnerStatusIv());
        signTracker.getOverallTitleTv().setText(context.getText(R.string.sign_otp_hard_copy));
        paymentTracker.setClickable(false);
        conveyancingTracker.setClickable(false);
        completeTracker.setClickable(false);
        break;
      case buyers_assigned:
        signTracker.setInProgressStatus(signTracker.getOverallStatusIv());
        signTracker.setCompletedStatus(signTracker.getFirstInnerStatusIv());
        signTracker.setInProgressStatus(signTracker.getSecondInnerStatusIv());
        paymentTracker.setClickable(false);
        conveyancingTracker.setClickable(false);
        completeTracker.setClickable(false);
        break;
      case buyers_assigned_hardcopy:
        signTracker.setInProgressStatus(signTracker.getOverallStatusIv());
        signTracker.setCompletedStatus(signTracker.getFirstInnerStatusIv());
        signTracker.setInProgressStatus(signTracker.getSecondInnerStatusIv());
        signTracker.getOverallTitleTv().setText(context.getText(R.string.sign_otp_hard_copy));
        paymentTracker.setClickable(false);
        conveyancingTracker.setClickable(false);
        completeTracker.setClickable(false);
        break;
      case complete:
        signTracker.setCompletedStatus(signTracker.getOverallStatusIv());
        signTracker.setCompletedStatus(signTracker.getFirstInnerStatusIv());
        signTracker.setCompletedStatus(signTracker.getSecondInnerStatusIv());
        paymentTracker.setInProgressStatus(paymentTracker.getOverallStatusIv());
        paymentTracker.setClickable(true);
        conveyancingTracker.setClickable(false);
        completeTracker.setClickable(false);
        break;
      case complete_hardcopy:
        signTracker.setCompletedStatus(signTracker.getOverallStatusIv());
        signTracker.setCompletedStatus(signTracker.getFirstInnerStatusIv());
        signTracker.setCompletedStatus(signTracker.getSecondInnerStatusIv());
        paymentTracker.setInProgressStatus(paymentTracker.getOverallStatusIv());
        signTracker.getOverallTitleTv().setText(context.getText(R.string.sign_otp_hard_copy));
        paymentTracker.setClickable(true);
        conveyancingTracker.setClickable(false);
        completeTracker.setClickable(false);
        break;
      case not_excerised:
        break;
      default:
        break;
    }
  }
}
