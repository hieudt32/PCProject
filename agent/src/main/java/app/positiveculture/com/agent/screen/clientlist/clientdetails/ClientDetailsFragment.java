package app.positiveculture.com.agent.screen.clientlist.clientdetails;

import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.ItemSummaryCustom;
import app.positiveculture.com.agent.utils.PropertyUtils;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.enumdata.TypeID;
import app.positiveculture.com.data.response.dto.MemberDTO;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomHeaderView;

/**
 * The ClientDetails Fragment
 */
public class ClientDetailsFragment extends ViewFragment<ClientDetailsContract.Presenter> implements ClientDetailsContract.View {

  @BindView(R2.id.client_details_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.client_details_avatar_sdv)
  SimpleDraweeView mAvatarSdv;
  @BindView(R2.id.client_details_name_tv)
  TextView mNameTv;
  @BindView(R2.id.client_details_phone_isc)
  ItemSummaryCustom mPhoneView;
  @BindView(R2.id.client_details_email_isc)
  ItemSummaryCustom mEmailView;
  @BindView(R2.id.client_details_address_isc)
  ItemSummaryCustom mAddressView;
  @BindView(R2.id.client_details_nric_isc)
  ItemSummaryCustom mNricView;
  @BindView(R2.id.client_details_note_et)
  EditText mNoteEt;
  @BindView(R2.id.client_detail_note_ll)
  LinearLayout mNoteLayout;
  String mNote = "";

  @OnClick(R2.id.right_text_tv)
  void doSaveNoteMember() {
    mPresenter.saveNoteMember(mNoteEt.getText().toString());
  }

  public static ClientDetailsFragment getInstance() {
    return new ClientDetailsFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_client_details;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    addListeners();
  }

  private void addListeners() {
    mHeaderView.getRightTextTv().setVisibility(View.GONE);
    mHeaderView.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.back();
      }

      @Override
      public void onRightIconClick() {

      }
    });

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

  @Override
  public void setupData(MemberDTO client, boolean editable) {
    if (client.getAvatar() != null && client.getAvatar().getImgSmall() != null) {
      ViewUtils.loadImageWithFresco(mAvatarSdv, client.getAvatar().getImgSmall());
    }
    if (client.getUsers() != null) {
      mNameTv.setText(client.getUsers().getFullName());
      mPhoneView.setContent(client.getUsers().getPhoneCountryCode() + " " + client.getUsers().getPhoneNumber());
      mEmailView.setContent(client.getUsers().getEmail());
    }
    if (client.getIdType() != null && client.getIdType() == TypeID.co_reg_no) {
      mNricView.setTitle(getString(R.string.co_reg_no));
    }
    mNricView.setContent(client.getmIdNumber());
    mAddressView.setContent(PropertyUtils.getDisplayAddressUser(client.getmAddrFloor(), client.getmAddrUnit(),
            client.getmAddrBuilding(), client.getmAddrStreetLineOne(), client.getmAddrStreetLineTwo(),
            client.getmAddrCountry(), client.getmAddrPostalCode()));
    if (!StringUtils.isEmpty(client.getNote())) {
      mNote = client.getNote();
      mNoteEt.setText(mNote);
    } else {
      mNote = "";
    }
    if (editable) {
      mNoteLayout.setVisibility(View.VISIBLE);
      mNoteEt.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
          if (!StringUtils.isEmpty(s.toString().trim()) && !s.toString().trim().equals(mNote)) {
            mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
          } else {
            mHeaderView.getRightTextTv().setVisibility(View.GONE);
          }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
      });
    } else {
      mNoteEt.setEnabled(false);
      mHeaderView.getRightTextTv().setVisibility(View.GONE);
      mNoteLayout.setVisibility(View.GONE);
    }
  }
}
