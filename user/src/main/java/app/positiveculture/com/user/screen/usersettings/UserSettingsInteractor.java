package app.positiveculture.com.user.screen.usersettings;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.ErrorDTO;

/**
 * The UserSettings interactor
 */
class UserSettingsInteractor extends Interactor<UserSettingsContract.Presenter>
        implements UserSettingsContract.Interactor {

    UserSettingsInteractor(UserSettingsContract.Presenter presenter) {
        super(presenter);
    }

    @Override
    public void logout(CommonCallback<ErrorDTO> commonCallback) {
        ServiceBuilder.getInstance().getService().logout().enqueue(commonCallback);
    }
}
