package app.positiveculture.com.agent.screen.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.ViewFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.HashMap;
import java.util.Map;

import app.positiveculture.com.agent.Constant;
import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.BindView;

import static com.gemvietnam.utils.DeviceUtils.dpToPx;

/**
 * The Main Fragment
 */
public class MainFragmentAgent extends ViewFragment<MainContractAgent.Presenter> implements MainContractAgent.View {

  @BindView(R2.id.bottom_bar_agent)
  BottomBar mBottomBar;
  boolean doubleBackToExitPressedOnce = false;

  private Map<Integer, Presenter> mMap = new HashMap<>();
  private static final int FRAME_CONTAINER_ID = R.id.main_content;

  public static final String TAB_POSITION = "tab_position";

  public static MainFragmentAgent getInstance() {
    return new MainFragmentAgent();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_main_agent;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    final View activityRootView = mRootView.findViewById(R.id.relative_view);
    activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {
        activityRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
        if (heightDiff > dpToPx(getActivity(), 200)) { // if more than 200 dp, it's probably a keyboard...
          mBottomBar.setVisibility(View.GONE);
        } else {
          mBottomBar.setVisibility(View.VISIBLE);
        }
      }
    });
    initBottomBarListener();

    Intent intent = getActivity().getIntent();
    if (intent != null) {
      mBottomBar.selectTabAtPosition(intent.getIntExtra(MainFragmentAgent.TAB_POSITION, 0));
    }

    if (getView() != null) {
      getView().setOnKeyListener(new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

          if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!doubleBackToExitPressedOnce) {
              doubleBackToExitPressedOnce = true;
              Toast.makeText(getViewContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

              new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                  doubleBackToExitPressedOnce = false;
                }
              }, 2000);
            }
          }
          return false;
        }
      });
    }
  }

  private void initBottomBarListener() {
//    int position = 0;
//    if(getActivity().getIntent().getExtras() != null){
//      position = getActivity().getIntent().getExtras().getInt(TAB_POSITION, 0);
//    }

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
        mPresenter.onReloadClientListClient();
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
