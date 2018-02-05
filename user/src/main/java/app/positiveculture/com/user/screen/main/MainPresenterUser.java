package app.positiveculture.com.user.screen.main;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.user.R;
import app.positiveculture.com.user.screen.properties.PropertiesPresenter;
import app.positiveculture.com.user.screen.userprofile.UserProfilePresenter;
import app.positiveculture.com.user.screen.usersettings.UserSettingsPresenter;

/**
 * The Main Presenter
 */
public class MainPresenterUser extends Presenter<MainContractUser.View, MainContractUser.Interactor>
        implements MainContractUser.Presenter {

  public MainPresenterUser(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public MainContractUser.View onCreateView() {
    return MainFragmentUser.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public MainContractUser.Interactor onCreateInteractor() {
    return new MainInteractorUser(this);
  }


  @Override
  public Presenter getMainTab(int fragmentId) {
    Presenter presenter = null;
    if (fragmentId == R.id.tab_properties_user) {
      presenter = new PropertiesPresenter(mContainerView);
    } else if (fragmentId == R.id.tab_profile) {
      presenter = new UserProfilePresenter(mContainerView);
    } else if (fragmentId == R.id.tab_settings) {
      presenter = new UserSettingsPresenter(mContainerView);
    }
    return presenter;
  }
}
