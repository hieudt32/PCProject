package com.positiveculture.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.gemvietnam.base.BaseActivity;
import com.gemvietnam.base.ContainerActivity;
import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.DialogUtils;
import com.positiveculture.app.screen.setupprofile.SetupProfilePresenter;
import com.positiveculture.app.screen.splash.SplashPresenter;
import com.positiveculture.app.screen.verifyemail.VerifyEmailFragment;
import com.positiveculture.app.screen.verifymobilenumber.VerifyMobileNumberFragment;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

import java.util.List;

import app.positiveculture.com.agent.screen.main.MainFragmentAgent;
import app.positiveculture.com.agent.screen.main.MainPresenterAgent;
import app.positiveculture.com.agent.screen.seller.otpcontract.OTPContractFragment;
import app.positiveculture.com.agent.screen.summary.SummaryFragment;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.user.screen.main.MainFragmentUser;
import app.positiveculture.com.user.screen.main.MainPresenterUser;


public class MainActivity extends ContainerActivity {

  @Override
  public ViewFragment onCreateFirstFragment() {
    User user = PrefWrapper.getUser();
    return (ViewFragment) ((user == null) ? new SplashPresenter(this).getFragment() : (user.getType().equals("agent")) ? new MainPresenterAgent(this).getFragment() : new MainPresenterUser(this).getFragment());
  }

  @Override
  public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
    checkForUpdates();
  }

  @Override
  protected void onResume() {
    super.onResume();
    checkForCrashes();
  }

  @Override
  public void onPause() {
    super.onPause();
    unregisterManagers();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    unregisterManagers();
  }

  private boolean doubleBackToExitPressedOnce;

  @Override
  public void onBackPressed() {
    int count = getSupportFragmentManager().getBackStackEntryCount();
    Fragment fragment = getFragmentMain(getBaseActivity());
    if (count > 0) {
      FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(count - 1);
      // Prevent user from press back on Verify Email Screen
      String tag = backEntry.getName();
      Fragment frag = getSupportFragmentManager().findFragmentByTag(tag);
      if (frag instanceof VerifyEmailFragment || frag instanceof OTPContractFragment || frag instanceof SummaryFragment) {
        return;
      }
      // Tap back twice to exit the application
      if (fragment != null) {
        if (backEntry.getName().compareTo(fragment.getClass().getSimpleName()) != 0) {
          getSupportFragmentManager().popBackStack();
        } else {
          if (!doubleBackToExitPressedOnce) {
            handleExit();
          } else {
            finish();
          }
        }
      } else {
        getSupportFragmentManager().popBackStack();
      }

    } else if (!doubleBackToExitPressedOnce) {
      handleExit();
    } else {
      finish();
    }
  }

  public Fragment getFragmentMain(BaseActivity activity) {
    FragmentManager fragmentManager = activity.getSupportFragmentManager();
    @SuppressLint("RestrictedApi") List<Fragment> fragments = fragmentManager.getFragments();
    if (fragments != null) {
      for (Fragment fragment : fragments) {
        if (fragment != null) {
          if (fragment instanceof MainFragmentAgent || fragment instanceof MainFragmentUser)
            return fragment;
        }
      }
    }
    return null;
  }

  private void handleExit() {
    this.doubleBackToExitPressedOnce = true;
    Toast.makeText(getViewContext(), "Please click BACK again to exit.", Toast.LENGTH_SHORT).show();
    new Handler().postDelayed(new Runnable() {

      @Override
      public void run() {
        doubleBackToExitPressedOnce = false;
      }
    }, 2000);
  }

  private void checkForCrashes() {
    CrashManager.register(this);
  }

  private void checkForUpdates() {
    // Remove this for store builds!
    UpdateManager.register(this);
  }

  private void unregisterManagers() {
    UpdateManager.unregister();
  }
}