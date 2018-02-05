package app.positiveculture.com.agent.screen.photo;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.ArrayList;

import app.positiveculture.com.agent.screen.summary.SummaryPresenter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.CreatePropertyDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.PhotoDTO;

/**
 * The Photo Presenter
 */
public class PhotoPresenter extends Presenter<PhotoContract.View, PhotoContract.Interactor>
        implements PhotoContract.Presenter, SummaryPresenter.OnBackPhotoFromSummaryListener {

  private CreatePropertyDTO createPropertyDTO;

  public PhotoPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public PhotoContract.View onCreateView() {
    return PhotoFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public void goBack() {
    mContainerView.back();
  }

  @Override
  public void uploadGalleryImage(PhotoDTO photo) {
    if (photo == null) return;
    mView.showProgress();
    mInteractor.uploadGalleryImage(photo, new CommonCallback<FileDTO>(getViewContext()) {
      @Override
      public void onSuccess(FileDTO data) {
        super.onSuccess(data);
        mView.bindImageGallery(data);
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public void goToSummaryProperties(final ArrayList<FileDTO> photos) {
    createPropertyDTO.setmGallery(photos);
    new SummaryPresenter(mContainerView)
            .setCreatePropertyDTO(createPropertyDTO)
            .setOnBackPhotoFromSummaryListener(this)
            .pushView();
  }

  @Override
  public PhotoContract.Interactor onCreateInteractor() {
    return new PhotoInteractor(this);
  }

  public PhotoPresenter setCreatePropertyDTO(CreatePropertyDTO createPropertyDTO) {
    this.createPropertyDTO = createPropertyDTO;
    return this;
  }

  @Override
  public void onBackPhotoScreen() {
    mView.bindViewForEditPhoto();
  }
}
