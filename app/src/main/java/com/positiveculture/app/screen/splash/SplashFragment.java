package com.positiveculture.app.screen.splash;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.positiveculture.app.R;
import com.positiveculture.app.R2;
import com.positiveculture.app.screen.splash.adapter.ViewPagerAdapter;

import java.util.logging.Handler;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * The Login Fragment
 */
public class SplashFragment extends ViewFragment<SplashContract.Presenter> implements SplashContract.View {
  private PagerAdapter mPagerAdapter;
  private int[] imageResources = {
          R.drawable.hdb,
          R.drawable.landed,
          R.drawable.condo,
  };

  @BindView(R2.id.viewpager)
  ViewPager mPager;
  @BindView(R2.id.ic_loading_splash_pg)
  ProgressBar mLoadingPb;
  @BindView(R2.id.splash_screen_rl)
  RelativeLayout mLoadingScreen;
  @BindView(R2.id.terms_conditions_tv)
  TextView mTermsConditions;
  @BindView(R2.id.privacy_policy_tv)
  TextView mPrivacyPolicy;

  @OnClick(R.id.login_btn)
  void onLogin() {
    mPresenter.gotoLogin();
  }

  @OnClick(R.id.register_btn)
  void onRegister() {
    mPresenter.goToRegister();
  }

  public static SplashFragment getInstance() {
    return new SplashFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_splash;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mTermsConditions.setPaintFlags(mTermsConditions.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    mPrivacyPolicy.setPaintFlags(mPrivacyPolicy.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    Context mContext = getContext();
    mLoadingPb.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(mContext, R.color.white), PorterDuff.Mode.MULTIPLY);
    mLoadingScreen.setVisibility(View.VISIBLE);
    mPagerAdapter = new ViewPagerAdapter(mContext, imageResources);
    mPager.setAdapter(mPagerAdapter);
    mLoadingPb.setVisibility(View.VISIBLE);
    mPresenter.getAppSetting();
  }

  @Override
  public void getAppSettingSuccess() {
    Animation mAnimation = AnimationUtils.loadAnimation(getContext(), font.app.gem.widgetlibrary.R.anim.slide_left_out);
    mAnimation.setDuration(1290);
    mLoadingScreen.startAnimation(mAnimation);
    new android.os.Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        mLoadingScreen.clearAnimation();
        mLoadingPb.setVisibility(View.GONE);
        mLoadingScreen.setVisibility(View.GONE);
      }
    }, 1300);
  }
}
