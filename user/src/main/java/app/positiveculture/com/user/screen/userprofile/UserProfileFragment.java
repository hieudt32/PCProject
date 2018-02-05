package app.positiveculture.com.user.screen.userprofile;

import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.customview.ItemSummaryCustom;
import app.positiveculture.com.agent.utils.PropertyUtils;
import app.positiveculture.com.data.enumdata.TypeID;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * The UserProfile Fragment
 */
public class UserProfileFragment extends ViewFragment<UserProfileContract.Presenter> implements UserProfileContract.View {

  @BindView(R2.id.user_profile_avatar_sdv)
  SimpleDraweeView mAvatarIv;
  @BindView(R2.id.user_profile_name_tv)
  TextView mNameTv;
  @BindView(R2.id.user_profile_phone_isc)
  ItemSummaryCustom mPhoneIcs;
  @BindView(R2.id.user_profile_email_isc)
  ItemSummaryCustom mEmailIcs;
  @BindView(R2.id.user_profile_addr_isc)
  ItemSummaryCustom mAddressIcs;
  @BindView(R2.id.user_profile_nric_isc)
  ItemSummaryCustom mNricIcs;

  public static UserProfileFragment getInstance() {
    return new UserProfileFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_user_profile;
  }

  @OnClick(R2.id.right_text_tv)
  public void editProfile() {
    mPresenter.showEditProfil();
  }

  @OnClick(R2.id.need_a_loan_bt)
  public void showLoanForm() {
    mPresenter.showLoanForm();
  }

  @Override
  public void initLayout() {
    super.initLayout();
  }

  @Override
  public void creatProfile(MemberDTO memberDTO) {
    if (memberDTO.getAvatar() != null) {
      mAvatarIv.setImageURI(memberDTO.getAvatar().getImgSmall());
    }
    mNameTv.setText(memberDTO.getUsers().getFullName());
    mPhoneIcs.setContent(memberDTO.getUsers().getPhoneCountryCode() + " " + memberDTO.getUsers().getPhoneNumber());
    mEmailIcs.setContent(memberDTO.getUsers().getEmail());
    mAddressIcs.setContent(PropertyUtils.getDisplayAddressUser(memberDTO.getmAddrFloor(), memberDTO.getmAddrUnit(),
            memberDTO.getmAddrBuilding(), memberDTO.getmAddrStreetLineOne(), memberDTO.getmAddrStreetLineTwo(),
            memberDTO.getmAddrCountry(), memberDTO.getmAddrPostalCode()));
    if (memberDTO.getIdType().equals(TypeID.co_reg_no)) {
      mNricIcs.setTitle(getString(R.string.co_reg_no));
    } else {
      mNricIcs.setTitle(getString(R.string.nric));
    }
    if (memberDTO.getmIdNumber() != null) {
      mNricIcs.setContent(memberDTO.getmIdNumber());
    }

    mPhoneIcs.getFirstImageView().setVisibility(View.GONE);
    mPhoneIcs.getSecondImageView().setVisibility(View.GONE);
    mEmailIcs.getFirstImageView().setVisibility(View.GONE);
  }

}
