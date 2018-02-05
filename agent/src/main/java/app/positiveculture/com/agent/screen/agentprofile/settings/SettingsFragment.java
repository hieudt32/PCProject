package app.positiveculture.com.agent.screen.agentprofile.settings;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomHeaderView;

/**
 * The Settings Fragment
 */
public class SettingsFragment extends ViewFragment<SettingsContract.Presenter> implements SettingsContract.View {

  @BindView(R2.id.settings_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.settings_change_password_layout)
  LinearLayout mChangePasswordLayout;
  @BindView(R2.id.switchSetting)
  Switch mTitleSwitchIv;
  @BindView(R2.id.settings_logout_tv)
  TextView mLogoutTv;

  public static SettingsFragment getInstance() {
    return new SettingsFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_settings;
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

    mChangePasswordLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.goToChangePassword();
      }
    });

    mTitleSwitchIv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mTitleSwitchIv.setSelected(!mTitleSwitchIv.isSelected());
      }
    });

  }

  @OnClick(R2.id.settings_logout_tv)
  void logout() {
    mPresenter.logout();
  }
}
