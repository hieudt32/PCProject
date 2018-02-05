package app.positiveculture.com.user.screen.userchangepassword;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.data.response.dto.Users;

/**
 * The UserChangePassword Presenter
 */
public class UserChangePasswordPresenter extends Presenter<UserChangePasswordContract.View, UserChangePasswordContract.Interactor>
        implements UserChangePasswordContract.Presenter {

  public UserChangePasswordPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public UserChangePasswordContract.View onCreateView() {
    return UserChangePasswordFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public UserChangePasswordContract.Interactor onCreateInteractor() {
    return new UserChangePasswordInteractor(this);
  }

  @Override
  public void changePassword(String s) {
    mView.showProgress();
    mInteractor.changePassword(s, new CommonCallback<Users>(getViewContext()) {

      @Override
      public void onSuccess(Users data) {
        super.onSuccess(data);
        mView.hideProgress();
        mView.showToastChangePasswordStatus(true);
        back();
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
        mView.hideProgress();
        mView.showToastChangePasswordStatus(false);
      }
    });
  }
}
