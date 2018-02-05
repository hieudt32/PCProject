package app.positiveculture.com.user.screen.userprofile;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.MemberDTO;

/**
 * The UserProfile interactor
 */
class UserProfileInteractor extends Interactor<UserProfileContract.Presenter>
        implements UserProfileContract.Interactor {

    UserProfileInteractor(UserProfileContract.Presenter presenter) {
        super(presenter);
    }

    @Override
    public void getProfile(CommonCallback<MemberDTO> commonCallback) {
        ServiceBuilder.getInstance().getService().getProfileMember().enqueue(commonCallback);
    }
}
