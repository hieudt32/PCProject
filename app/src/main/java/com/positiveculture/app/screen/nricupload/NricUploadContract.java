package com.positiveculture.app.screen.nricupload;

import android.net.Uri;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeID;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.PhotoDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The NricUpload Contract
 */
interface NricUploadContract {

  interface Interactor extends IInteractor<Presenter> {
    void saveNricProfile(long idNricFront, long idNricBack, String nric, CommonCallback<User> commonCallback);

    void uploadNricImage(PhotoDTO mPhoto, CommonCallback<FileDTO> commonCallback);

    void saveUserId(String idType, String userId, long frontImage, long backImage, CommonCallback<User> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void showUserView(TypeID typeID, String idNumber);

    void bindView(FileDTO mFrontImage, FileDTO mBackImage);

    void showErrorDialog(boolean b);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void saveNricProfile(String nricNumber);

    void goToMainAgent();

    void goToBankDetail();

    void nextStep();

    void uploadNricImage(PhotoDTO mFrontPhoto, boolean b);

    void saveUserId(String idType, String userId);
  }
}



