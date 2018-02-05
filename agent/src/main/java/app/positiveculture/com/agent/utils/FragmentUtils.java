package app.positiveculture.com.agent.utils;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.gemvietnam.base.BaseActivity;

import java.util.List;

import app.positiveculture.com.agent.screen.main.MainFragmentAgent;
import app.positiveculture.com.agent.screen.properties.detail.DetailFragment;
import app.positiveculture.com.agent.screen.seller.processtracker.ProcessTrackerFragment;

/**
 * FragmentUtils
 * Created by hieudt on 9/12/2017.
 */

public class FragmentUtils {

  public static Fragment getFragmentMain(BaseActivity activity) {
    FragmentManager fragmentManager = activity.getSupportFragmentManager();
    @SuppressLint("RestrictedApi") List<Fragment> fragments = fragmentManager.getFragments();
    if (fragments != null) {
      for (Fragment fragment : fragments) {
        if (fragment != null && fragment instanceof MainFragmentAgent)
          return fragment;
      }
    }
    return null;
  }

  public static Fragment getFragmentDetailProperty(BaseActivity activity) {
    FragmentManager fragmentManager = activity.getSupportFragmentManager();
    @SuppressLint("RestrictedApi") List<Fragment> fragments = fragmentManager.getFragments();
    if (fragments != null) {
      for (Fragment fragment : fragments) {
        if (fragment != null && fragment instanceof DetailFragment)
          return fragment;
      }
    }
    return null;
  }

  public static Fragment getProcessTrackerScreen(BaseActivity activity) {
    FragmentManager fragmentManager = activity.getSupportFragmentManager();
    @SuppressLint("RestrictedApi") List<Fragment> fragments = fragmentManager.getFragments();
    if (fragments != null) {
      for (Fragment fragment : fragments) {
        if (fragment != null && fragment instanceof ProcessTrackerFragment)
          return fragment;
      }
    }
    return null;
  }

}
