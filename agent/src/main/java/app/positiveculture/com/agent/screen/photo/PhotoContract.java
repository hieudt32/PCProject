package app.positiveculture.com.agent.screen.photo;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import java.io.File;
import java.util.ArrayList;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.PhotoDTO;

/**
 * The Photo Contract
 */
interface PhotoContract {

  interface Interactor extends IInteractor<Presenter> {
    void uploadGalleryImage(PhotoDTO photo, CommonCallback<FileDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void bindImageGallery(FileDTO data);

    void bindViewForEditPhoto();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToSummaryProperties(ArrayList<FileDTO> photos);

    void goBack();

    void uploadGalleryImage(PhotoDTO photo);
  }
}



