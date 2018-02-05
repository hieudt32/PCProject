package app.positiveculture.com.agent.screen.properties;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * PropertiesPagerAdapter
 * Created by hieudt on 8/23/2017.
 */

public class PropertiesPagerAdapter extends FragmentStatePagerAdapter {

  private final List<Fragment> mFragmentList = new ArrayList<>();
  private final List<String> mFragmentTitleList = new ArrayList<>();

  public PropertiesPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  public void addFragment(Fragment fragment, String title) {
    mFragmentList.add(fragment);
    mFragmentTitleList.add(title);
  }

  public void removeFragment(int index) {
    mFragmentList.remove(index);
    mFragmentTitleList.remove(index);
  }

  @Override
  public Fragment getItem(int position) {
    return mFragmentList.get(position);
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return mFragmentTitleList.get(position);
  }

  public void changePageTitle(int position, String title) {
    mFragmentTitleList.set(position, title);
  }

  @Override
  public int getCount() {
    return mFragmentList.size();
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    super.destroyItem(container, position, object);
  }
}
