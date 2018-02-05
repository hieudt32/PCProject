package app.positiveculture.com.user.screen.usersettings;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.ActivityUtils;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.user.screen.userchangepassword.UserChangePasswordPresenter;

/**
 * The UserSettings Presenter
 */
public class UserSettingsPresenter extends Presenter<UserSettingsContract.View, UserSettingsContract.Interactor>
        implements UserSettingsContract.Presenter {

  public UserSettingsPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public UserSettingsContract.View onCreateView() {
    return UserSettingsFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public UserSettingsContract.Interactor onCreateInteractor() {
    return new UserSettingsInteractor(this);
  }

  @Override
  public void changePassword() {
    new UserChangePasswordPresenter(mContainerView).pushView();
  }

  @Override
  public void logout() {
    mView.showProgress();
    mInteractor.logout(new CommonCallback<ErrorDTO>(getViewContext()) {

      @Override
      public void onSuccess(ErrorDTO data) {
        super.onSuccess(data);
        PrefWrapper.remove(PrefWrapper.KEY_MEMBER);
        PrefWrapper.remove(PrefWrapper.KEY_USER);
        ActivityUtils.restart(getViewContext());
      }
    });
  }

}
