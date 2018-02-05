package app.positiveculture.com.user.screen.usernric;

import android.net.Uri;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The UserNric Contract
 */
interface UserNricContract {

  interface Interactor extends IInteractor<Presenter> {
    void saveNricProfile(String nric, String idType, long idFront, long idBack, CommonCallback<User> commonCallback);

    void uploadNricImage(Uri mSelectedUri, String mRealImageFilePath, CommonCallback<FileDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void hideUploadImg();

    void bindNric(MemberDTO mMemberDTO);

    void bindNricUpload(FileDTO data, boolean b);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    int getNeedUpLoadImg();

    void saveNricProfile(String idNumber, String idType);

    void uploadNricImage(Uri mSelectedUri, String mRealImageFilePath, boolean b);
  }
}



