package app.positiveculture.com.agent.screen.clientlist.clientprofile;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.screen.properties.PropertiesPagerAdapter;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.response.dto.MemberDTO;
import butterknife.BindView;
import customview.CustomHeaderView;

/**
 * The ClientProfile Fragment
 */
public class ClientProfileFragment extends ViewFragment<ClientProfileContract.Presenter> implements ClientProfileContract.View {

  @BindView(R2.id.client_profile_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.client_profile_layout)
  LinearLayout mClientProfileLayout;
  @BindView(R2.id.client_profile_avatar_sdv)
  SimpleDraweeView mAvatarSdv;
  @BindView(R2.id.client_profile_name_tv)
  TextView mNameTv;
  @BindView(R2.id.client_profile_property_tv)
  TextView mPropertyTv;
  @BindView(R2.id.client_profile_tab_layout)
  TabLayout mTabLayout;
  @BindView(R2.id.client_profile_pager)
  ViewPager mPager;

  public static ClientProfileFragment getInstance() {
    return new ClientProfileFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_client_profile;
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

    mClientProfileLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.goToClientDetails();
      }
    });
  }

  @Override
  public void setupData(MemberDTO client) {
    if (client.getAvatar() != null && client.getAvatar().getImgSmall() != null) {
      ViewUtils.loadImageWithFresco(mAvatarSdv, client.getAvatar().getImgSmall());
    }

    if (client.getUsers() != null) mNameTv.setText(client.getUsers().getFullName());
    String text = String.valueOf(client.getProperties());
    if (client.getProperties() > 1) {
      text += " " + getString(R.string.properties);
      mPropertyTv.setText(text);
    } else {
      text += " " + getString(R.string.property);
      mPropertyTv.setText(text);
    }
    if (client.getProperties() > 0) {
      PropertiesPagerAdapter mPagerAdapter = mPresenter.setupPager();
      mPager.setAdapter(mPagerAdapter);
      mTabLayout.setupWithViewPager(mPager);
    }else{
      mPager.setVisibility(View.GONE);
      mTabLayout.setVisibility(View.GONE);
    }
  }
}
