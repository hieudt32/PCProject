package app.positiveculture.com.agent.screen.agentprofile;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.DialogUtils;
import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.ItemSummaryCustom;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.response.dto.AgentDTO;
import butterknife.BindView;
import customview.CustomHeaderView;

/**
 * The AgentProfile Fragment
 */
public class AgentProfileFragment extends ViewFragment<AgentProfileContract.Presenter> implements AgentProfileContract.View {

  @BindView(R2.id.agent_profile_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.agent_profile_avatar_sdv)
  SimpleDraweeView mAvatarSdv;
  @BindView(R2.id.agent_profile_name_tv)
  TextView mNameTv;
  @BindView(R2.id.agent_profile_phone_isc)
  ItemSummaryCustom mPhoneView;
  @BindView(R2.id.agent_profile_email_isc)
  ItemSummaryCustom mEmailView;
  @BindView(R2.id.agent_profile_cea_isc)
  ItemSummaryCustom mCeaView;
  @BindView(R2.id.agent_profile_agency_isc)
  ItemSummaryCustom mAgencyIsc;
  @BindView(R2.id.agent_profile_bank_details_isc)
  ItemSummaryCustom mBankDetailsIsc;
  @BindView(R2.id.agent_profile_nric_isc)
  ItemSummaryCustom mNricView;


  public static AgentProfileFragment getInstance() {
    return new AgentProfileFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_agent_profile;
  }

  @Override
  public void initLayout() {
    super.initLayout();
  }

  @Override
  public void bindData(AgentDTO mAgentDTO) {
    DialogUtils.showProgressDialog(getViewContext());
    mHeaderView.getLeftIconIv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.goToSettings();
      }
    });
    mHeaderView.getRightTextTv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.goToEditProfile();
      }
    });
    mPhoneView.setContent(mAgentDTO.getUsers().getPhoneCountryCode() + " " + mAgentDTO.getUsers().getPhoneNumber());
    mPhoneView.getFirstImageView().setVisibility(View.INVISIBLE);
    mPhoneView.getSecondImageView().setVisibility(View.INVISIBLE);
    mEmailView.setContent(mAgentDTO.getUsers().getEmail());
    mEmailView.getFirstImageView().setVisibility(View.INVISIBLE);
    if (!StringUtils.isEmpty(mAgentDTO.getBankName())) {
      mBankDetailsIsc.setContent(mAgentDTO.getBankName());
    }
    mCeaView.setContent(mAgentDTO.getCEA().getCEANumber());
    if (!StringUtils.isEmpty(mAgentDTO.getNric())) {
      mNricView.setContent(mAgentDTO.getNric());
    } else {
      mNricView.getStatusImageView().setImageDrawable(getResources().getDrawable(R.drawable.yellow_circle_bg));
    }
    mAgencyIsc.setContent(mAgentDTO.getAgency());
    mNameTv.setText(mAgentDTO.getUsers().getFullName());
    if (mAgentDTO.getAvatar() != null && mAgentDTO.getAvatar().getImgSmall() != null) {
      ViewUtils.loadImageWithFresco(mAvatarSdv, mAgentDTO.getAvatar().getImgSmall());
    }
    DialogUtils.dismissProgressDialog();
  }

  @Override
  public void onDisplay() {
    super.onDisplay();
  }

  @Override
  public void showProfileForUserView(AgentDTO mAgentDTO) {
    mHeaderView.getRightTextTv().setVisibility(View.GONE);
    mHeaderView.getLeftIconIv().setImageResource(R.drawable.ic_back);
    mHeaderView.getLeftIconIv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.back();
      }
    });
    DialogUtils.showProgressDialog(getViewContext());
    mPhoneView.setContent(mAgentDTO.getUsers().getPhoneCountryCode() + " " + mAgentDTO.getUsers().getPhoneNumber());
    mEmailView.setContent(mAgentDTO.getUsers().getEmail());
    if (!StringUtils.isEmpty(mAgentDTO.getBankName())) {
      mBankDetailsIsc.setContent(mAgentDTO.getBankName());
    }
    mCeaView.setContent(mAgentDTO.getCEA().getCEANumber());
    if (!StringUtils.isEmpty(mAgentDTO.getNric())) {
      mNricView.setContent(mAgentDTO.getNric());
    } else {
      mNricView.getStatusImageView().setImageDrawable(getResources().getDrawable(R.drawable.yellow_circle_bg));
    }
    mAgencyIsc.setContent(mAgentDTO.getAgency());
    mNameTv.setText(mAgentDTO.getUsers().getFullName());
    if (mAgentDTO.getAvatar() != null && mAgentDTO.getAvatar().getImgSmall() != null) {
      ViewUtils.loadImageWithFresco(mAvatarSdv, mAgentDTO.getAvatar().getImgSmall());
    }
    DialogUtils.dismissProgressDialog();

    mPhoneView.getFirstImageView().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(
                new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", mPhoneView.getContent(), null))
        );
      }
    });

    mPhoneView.getSecondImageView().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(
                new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", mPhoneView.getContent(), null))
        );
      }
    });

    mEmailView.getFirstImageView().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(
                new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mEmailView.getContent(), null))
        );
      }
    });
  }
}
