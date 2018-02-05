package app.positiveculture.com.user.screen.userprofile;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.DialogUtils;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.user.screen.loan.LoanPresenter;
import app.positiveculture.com.user.screen.usereditprofile.UserEditProfilePresenter;

/**
 * The UserProfile Presenter
 */
public class UserProfilePresenter extends Presenter<UserProfileContract.View, UserProfileContract.Interactor>
        implements UserProfileContract.Presenter, UserEditProfilePresenter.OnSaveUserProfileListener {

  public UserProfilePresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public UserProfileContract.View onCreateView() {
    return UserProfileFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
    getProfile();
  }

  private void getProfile() {
    DialogUtils.showProgressDialog(getViewContext());
    mInteractor.getProfile(new CommonCallback<MemberDTO>(getViewContext()) {

      @Override
      public void onSuccess(MemberDTO data) {
        super.onSuccess(data);
        mView.creatProfile(data);
      }
    });
  }

  @Override
  public UserProfileContract.Interactor onCreateInteractor() {
    return new UserProfileInteractor(this);
  }

  @Override
  public void showEditProfil() {
    UserEditProfilePresenter userEditProfilePresenter = new UserEditProfilePresenter(mContainerView);
    userEditProfilePresenter.setOnSaveUserProfileListener(this);
    userEditProfilePresenter.pushView();
  }

  @Override
  public void showLoanForm() {
    new LoanPresenter(mContainerView).pushView();
  }

  @Override
  public void onSaveSuccess() {
    getProfile();
  }
}
