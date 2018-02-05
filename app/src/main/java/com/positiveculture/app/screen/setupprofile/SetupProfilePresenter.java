package com.positiveculture.app.screen.setupprofile;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import com.positiveculture.app.screen.nric.NricPresenter;
import com.positiveculture.app.screen.nricupload.NricUploadPresenter;
import com.positiveculture.app.screen.verifymobilenumber.VerifyMobileNumberPresenter;

import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.PhotoDTO;
import app.positiveculture.com.data.response.dto.SettingDTO;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.data.response.dto.Users;

/**
 * The SetupProfile Presenter
 */
public class SetupProfilePresenter extends Presenter<SetupProfileContract.View, SetupProfileContract.Interactor>
        implements SetupProfileContract.Presenter {

  private SettingDTO mSettingDTO = PrefWrapper.getSetting();
  private FileDTO mAvatar;
  private Users mUsers;

  public SetupProfilePresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SetupProfileContract.View onCreateView() {
    return SetupProfileFragment.getInstance();
  }

  @Override
  public void start() {
    if (mUsers != null) {
      mView.bindUser(mUsers);
    } else {
      User user = PrefWrapper.getUser();
      mUsers = user != null ? user.getUsers() : null;
      if (mUsers != null) mView.bindUser(mUsers);
    }
  }

  @Override
  public SetupProfileContract.Interactor onCreateInteractor() {
    return new SetupProfileInteractor(this);
  }

  @Override
  public void updateProfile(String name, String email, String countryCode, String phone) {
    long idAvatar = -1;
    if (mAvatar != null) idAvatar = mAvatar.getId();
    mView.showProgress();
    mInteractor.updateSetupProfile(idAvatar, name, email, countryCode, phone, new CommonCallback<User>(getViewContext()) {
      @Override
      public void onSuccess(User data) {
        super.onSuccess(data);
        if(data instanceof MemberDTO) PrefWrapper.saveMember((MemberDTO) data);
        mView.hideProgress();
        if (mSettingDTO.getNeedVerifyPhoneNumber() == 1) {
          new VerifyMobileNumberPresenter(mContainerView)
                  .setFromSetupProfile(true)
                  .pushView();
        } else {
          if (mSettingDTO.getNeedUploadImage() == 1) {
            new NricUploadPresenter(mContainerView)
                    .setSetupProfile(true)
                    .pushView();
          } else {
            new NricPresenter(mContainerView)
                    .setSetupprofile(true)
                    .pushView();
          }
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
        mView.hideProgress();
      }
    });
  }

  @Override
  public void uploadAvatar(PhotoDTO photo) {
    if (photo == null) return;
    mView.showProgress();
    mInteractor.uploadAvatar(photo, new CommonCallback<FileDTO>(getViewContext()) {
      @Override
      public void onSuccess(FileDTO data) {
        super.onSuccess(data);
        mAvatar = data;
        mView.bindAvatar(data);
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public void goToNricUpload() {
    new NricUploadPresenter(mContainerView)
            .setSetupProfile(true).pushView();
  }

  public SetupProfilePresenter setUser(Users mUsers) {
    this.mUsers = mUsers;
    return this;
  }
}
