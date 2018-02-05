package app.positiveculture.com.user.screen.usersettings;

import android.view.View;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomHeaderView;

/**
 * The UserSettings Fragment
 */
public class UserSettingsFragment extends ViewFragment<UserSettingsContract.Presenter> implements UserSettingsContract.View {

  @BindView(R2.id.settings_header_view)
  CustomHeaderView mHeaderView;

  public static UserSettingsFragment getInstance() {
    return new UserSettingsFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_settings;
  }

  @OnClick(R2.id.settings_change_password_layout)
  public void changePassword() {
    mPresenter.changePassword();
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mHeaderView.getLeftIconIv().setVisibility(View.GONE);
  }

  @OnClick(R2.id.settings_logout_tv)
  public void logout() {
    mPresenter.logout();
  }
}
