package com.positiveculture.app.screen.setupprofile;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.PhotoDTO;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.data.response.dto.Users;

/**
 * The SetupProfile Contract
 */
interface SetupProfileContract {

  interface Interactor extends IInteractor<Presenter> {

    void updateSetupProfile(long idAvatar, String name, String email, String countryCode, String phone, CommonCallback<User> commonCallback);

    void uploadAvatar(PhotoDTO photo, CommonCallback<FileDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void bindAvatar(FileDTO data);

    void bindUser(Users mUsers);
  }

  interface Presenter extends IPresenter<View, Interactor> {

    void updateProfile(String name, String email, String countryCode, String phone);

    void goToNricUpload();

    void uploadAvatar(PhotoDTO photo);
  }
}



