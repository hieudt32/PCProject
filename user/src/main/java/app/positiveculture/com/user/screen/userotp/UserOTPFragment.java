package app.positiveculture.com.user.screen.userotp;

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

import app.positiveculture.com.agent.customview.ItemSummaryCustom;
import app.positiveculture.com.agent.screen.seller.otp.OTPSignaturesAdapter;
import app.positiveculture.com.agent.utils.NumberUtils;
import app.positiveculture.com.agent.utils.OtpUtils;
import app.positiveculture.com.data.enumdata.MemberType;
import app.positiveculture.com.data.enumdata.OTPStatus;
import app.positiveculture.com.data.enumdata.StatusSignOTP;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.data.response.dto.SignOtpDTO;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;
import customview.CustomHeaderView;
import utils.DateTimeUtils;

/**
 * The UserOTP Fragment
 */
public class UserOTPFragment extends ViewFragment<UserOTPContract.Presenter> implements UserOTPContract.View {

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
  CustomButton mVerifySigningBt;
  List<MemberDTO> mListMembers = new ArrayList<>();
  private OTPSignaturesAdapter mSignaturesAdapter;
  private List<SimpleDraweeView> listAvatarOwners = new ArrayList<>();
  private List<SimpleDraweeView> listAvatarBuyer = new ArrayList<>();

  public static UserOTPFragment getInstance() {
    return new UserOTPFragment();
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
    mVerifySigningBt.setTextButton(getString(R.string.sign_otp));
    mVerifySigningBt.statusDisabledButton();
    mHeaderView.getRightTextTv().setVisibility(View.GONE);
  }

  @OnClick(R2.id.view_parties_container)
  void showUserViewParties() {
    mPresenter.goToUserViewParties();
  }

  @OnClick(R2.id.verify_signing_btn)
  public void showDisclaimer() {
    mPresenter.showDisclaimer();
  }

  @OnClick(R2.id.left_icon_iv)
  public void back() {
    mPresenter.goBack();
  }

  @OnClick(R2.id.otp_view_full_contract_ll)
  public void viewFullContact() {
    mPresenter.viewFullContact();
  }

  @OnClick(R2.id.right_text_tv)
  public void extentOTP() {
    mPresenter.extentOTP();
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

    mOtpExpiryDateItem.setContent(otpExpiryDate);
    mOtpCompletionDateItem.setContent(otpCompletionDate);
    mOtpTotalPriceItem.setContent(totalPrice);
    mOtpOptionFeeItem.setContent(optionFree);
    mOtpExerciseFeeItem.setContent(exerciseFee);
    List<MemberDTO> listBuyer = mOTP.getPropertyBuyer();
    List<SignOtpDTO> listSign = mOTP.getListSign();
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
      OtpUtils.userViewOTP(getViewContext(), otpStatus, mTypeProcess, mVerifySigningBt, mHeaderView, PrefWrapper.getMember(), listSign);
    }
  }
}
