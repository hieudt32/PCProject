package app.positiveculture.com.user.screen.properties;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import app.positiveculture.com.user.screen.properties.adapter.PropertiesPagerAdapter;
import butterknife.BindView;
import customview.CustomHeaderView;


/**
 * The Properties Fragment
 */
public class PropertiesFragment extends ViewFragment<PropertiesContract.Presenter> implements PropertiesContract.View {

  @BindView(R2.id.header_view)
  CustomHeaderView headerView;
  @BindView(R2.id.properties_tab_layout)
  TabLayout mPropertiesTabLayout;
  @BindView(R2.id.properties_pager)
  ViewPager mPropertiesPager;
  @BindView(R2.id.user_properties_empty)
  RelativeLayout mEmptyLayout;
  @BindView(R2.id.empty_layout_tv)
  TextView mMessageEmpty;

  private PropertiesPagerAdapter propertiesPagerAdapter;

  @Override
  public void initLayout() {
    super.initLayout();
  }

  public static PropertiesFragment getInstance() {
    return new PropertiesFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_properties_user;
  }

  @Override
  public void showEmptyProperty() {
    mPropertiesPager.setVisibility(View.GONE);
    mPropertiesTabLayout.setVisibility(View.GONE);
    mEmptyLayout.setVisibility(View.VISIBLE);

  }

  @Override
  public void setupTabLayout() {
    mPropertiesTabLayout.setVisibility(View.VISIBLE);
    mPropertiesPager.setVisibility(View.VISIBLE);
    mPropertiesTabLayout.setTabTextColors(ContextCompat.getColor(getViewContext(), (R.color.gray)), ContextCompat.getColor(getViewContext(), R.color.colorPrimary));
    mPropertiesTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getViewContext(), R.color.colorPrimary));
    mPropertiesTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    propertiesPagerAdapter = mPresenter.getViewPageAdapter();
    mPropertiesPager.setAdapter(propertiesPagerAdapter);

    mPropertiesTabLayout.setupWithViewPager(mPropertiesPager);

    mPropertiesPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mPropertiesTabLayout));
    mPropertiesTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        mPropertiesPager.setCurrentItem(tab.getPosition());
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });
  }
}
