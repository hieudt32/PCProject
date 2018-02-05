package app.positiveculture.com.agent.screen.properties;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.CustomViewPager;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomHeaderView;

/**
 * The Properties Fragment
 */
public class PropertiesFragment extends ViewFragment<PropertiesContract.Presenter> implements PropertiesContract.View {

  @BindView(R2.id.pager)
  CustomViewPager mPager;
  @BindView(R2.id.tab_layout_properties)
  TabLayout tab_Properties;
  @BindView(R2.id.properties_agent_hd)
  CustomHeaderView mHeader;
  @BindView(R2.id.listing_create_tv)
  TextView mListingCreate;
  @BindView(R2.id.tab_layout_properties_rl)
  LinearLayout mTabLayout;
  @BindView(R2.id.properties_empty_layout)
  RelativeLayout mEmptyLayout;
  @BindView(R2.id.empty_layout_tv)
  TextView mEmptyTv;
  @BindView(R2.id.right_icon2_iv)
  ImageView mIconPayed;

  @OnClick(R2.id.right_icon2_iv)
  public void onClickPayer() {
    if (mIconPayed.isSelected()) {
      mIconPayed.setSelected(false);
      mPagerAdapter = mPresenter.showProperties();
    } else {
      mIconPayed.setSelected(true);
      mPagerAdapter = mPresenter.showPayingClient();
    }
    mPager.setAdapter(mPagerAdapter);
    tab_Properties.setupWithViewPager(mPager);
  }

  private PropertiesPagerAdapter mPagerAdapter;

  public static PropertiesFragment getInstance() {
    return new PropertiesFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_properties_agent;
  }

  @SuppressLint("ClickableViewAccessibility")
  @Override
  public void initLayout() {
    super.initLayout();
    mHeader.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.goToSearchProperty();
      }

      @Override
      public void onRightIconClick() {
        mPresenter.goToSelectOwners();
      }
    });
    mPager.setSwipeable(false);
  }

  @Override
  public void showPropertiesEmptyList() {
    mHeader.getRightIcon2Iv().setVisibility(View.INVISIBLE);
    mEmptyLayout.setVisibility(View.VISIBLE);
    mTabLayout.setVisibility(View.GONE);
    mEmptyTv.setText(getString(R.string.empty_properties));
  }

  @Override
  public void setupTabLayout() {
    mHeader.getRightIcon2Iv().setVisibility(View.VISIBLE);
    mPagerAdapter = mPresenter.showProperties();
    if (mPager.getAdapter() == null) {
      mPager.setAdapter(mPagerAdapter);
      tab_Properties.setupWithViewPager(mPager);
    }
  }

  @Override
  public void listingCreated() {
    mListingCreate.setVisibility(View.VISIBLE);
    Animation animation = AnimationUtils.loadAnimation(getViewContext(), R.anim.anim_facein);
    mListingCreate.startAnimation(animation);
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        mListingCreate.setVisibility(View.GONE);
        mPresenter.start();
      }
    }, 2000);
  }
}
