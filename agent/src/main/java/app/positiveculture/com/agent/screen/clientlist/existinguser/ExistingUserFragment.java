package app.positiveculture.com.agent.screen.clientlist.existinguser;

import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.enumdata.TypeID;
import app.positiveculture.com.data.response.dto.MemberDTO;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;
import customview.CustomHeaderView;
import dialog.CustomDialog;

/**
 * The ExistingUser Fragment
 */
public class ExistingUserFragment extends ViewFragment<ExistingUserContract.Presenter> implements ExistingUserContract.View, CustomDialog.OnConfirmSelected {

  @BindView(R2.id.existing_user_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.existing_user_avatar_sdv)
  SimpleDraweeView mAvatarSdv;
  @BindView(R2.id.existing_user_name_tv)
  TextView mNameTv;
  @BindView(R2.id.existing_user_id_type_tv)
  TextView mIdTypeTv;
  @BindView(R2.id.existing_user_nric_fin_tv)
  TextView mIdNumberTv;
  @BindView(R2.id.existing_user_send_request_btn)
  CustomButton mSendRequestBtn;

  private MemberDTO mMemberDTO;
  private CustomDialog mDialog;

  public static ExistingUserFragment getInstance() {
    return new ExistingUserFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_existing_user;
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

    mSendRequestBtn.statusEnableButton();
  }

  @OnClick(R2.id.existing_user_send_request_btn)
  public void sendRequestBtnOnClick() {
    mPresenter.addExistingClient(mMemberDTO.getId());
  }

  @Override
  public void onConfirmSelected() {
    mDialog.dismiss();
    mPresenter.backToClientList();
  }

  @Override
  public void setupData(MemberDTO memberDTO) {
    mMemberDTO = memberDTO;

    if (memberDTO.getAvatar() != null && memberDTO.getAvatar().getImgSmall() != null) {
      ViewUtils.loadImageWithFresco(mAvatarSdv, memberDTO.getAvatar().getImgSmall());
    } else {
      mAvatarSdv.setImageResource(R.drawable.ic_avatar);
    }
    mNameTv.setText(memberDTO.getUsers().getFullName());
    if (memberDTO.getIdType().equals(TypeID.nric)) {
      mIdTypeTv.setText(R.string.nric_fin);
    } else {
      mIdTypeTv.setText(R.string.co_reg_no);
    }
    mIdNumberTv.setText(memberDTO.getmIdNumber());
  }

  @Override
  public void showRequestSentDialog() {
    mDialog = new CustomDialog(getViewContext(), getString(R.string.bt_ok), null,
            getString(R.string.request_sent_title),
            getString(R.string.request_sent_desc),
            ExistingUserFragment.this
    );
    mDialog.show();
  }
}
