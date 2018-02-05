package app.positiveculture.com.agent.screen.nricupload;

import android.net.Uri;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.PhotoDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The NricUpload Contract
 */
interface NricUploadContractAgent {

  interface Interactor extends IInteractor<Presenter> {
    void saveNricProfile(String nric, long idFrontImage, long idBackImage, CommonCallback<User> commonCallback);

    void uploadNricImage(PhotoDTO mPhoto, CommonCallback<FileDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void bindView(String nricNumber, FileDTO nricFrontImage, FileDTO nricBackImage);

    void showErrorDialog(boolean b);

    void bindViewNricImage(FileDTO mNricImage, boolean b);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void saveNricProfile(String nricNumber);

    void uploadImage(PhotoDTO mFrontPhoto, boolean isFrontImage);
  }
}



