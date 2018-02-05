package app.positiveculture.com.user.screen.main;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewTreeObserver;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.ViewFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.HashMap;
import java.util.Map;

import app.positiveculture.com.user.Constant;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.BindView;

import static com.gemvietnam.utils.DeviceUtils.dpToPx;

/**
 * The Main Fragment
 */
public class MainFragmentUser extends ViewFragment<MainContractUser.Presenter> implements MainContractUser.View {

  @BindView(R2.id.bottom_bar_user)
  BottomBar mBottomBar;
  private View activityRootView;

  private Map<Integer, Presenter> mMap = new HashMap<>();
  private static final int FRAME_CONTAINER_ID = R.id.main_content;

  public static final String TAB_POSITION = "tab_position";

  private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;

  public static MainFragmentUser getInstance() {
    return new MainFragmentUser();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_main_user;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    activityRootView = mRootView.findViewById(R.id.relative_view);

    mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {
        int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
        if (heightDiff > dpToPx(getActivity(), 200)) { // if more than 200 dp, it's probably a keyboard...
          mBottomBar.setVisibility(View.GONE);
        }
        else {
          mBottomBar.setVisibility(View.VISIBLE);
        }
      }
    };

    activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayoutListener);
    initBottomBarListener();

    Intent intent = getActivity().getIntent();
    if (intent != null) {
      mBottomBar.selectTabAtPosition(intent.getIntExtra(MainFragmentUser.TAB_POSITION, 0));
    }
  }

  @Override
  public void onStop() {
    super.onStop();
    activityRootView.getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
  }

  @Override
  public void onPause() {
    super.onPause();
    activityRootView.getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    activityRootView.getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
  }

  private void initBottomBarListener() {
    int position = 0;
    if(getActivity().getIntent().getExtras() != null){
      position = getActivity().getIntent().getExtras().getInt(TAB_POSITION, 0);
    }

    mBottomBar.selectTabAtPosition(Constant.TAB_POSITION.PROPERTIES_TAB);
    mBottomBar.setBadgeBackgroundColor(ContextCompat.getColor(getViewContext(), R.color.colorPrimary));
    mBottomBar.setBadgesHideWhenActive(false);
    //Setup tab
    mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
      @Override
      public void onTabSelected(@IdRes int tabId) {

        switchTabs(tabId);

      }
    });

    mBottomBar.setOnTabReselectListener(new OnTabReselectListener() {
      @Override
      public void onTabReSelected(@IdRes int tabId) {
        switchTabs(tabId);
      }
    });

  }

  private void switchTabs(int tabId) {
    showFragment(tabId);
    getActivity().invalidateOptionsMenu();
  }

  private void showFragment(int fragmentId) {
    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

    for (Presenter presenter : mMap.values()) {
      transaction.hide(presenter.getFragment());
    }

    if (mMap.containsKey(fragmentId)) {
      transaction.show(mMap.get(fragmentId).getFragment());
    } else {
      transaction.add(FRAME_CONTAINER_ID, createFragment(fragmentId));
    }
    transaction.commit();
  }

  private Fragment createFragment(int fragmentId) {
    Presenter presenter = mPresenter.getMainTab(fragmentId);
    mMap.put(fragmentId, presenter);
    return presenter.getFragment();
  }
}
