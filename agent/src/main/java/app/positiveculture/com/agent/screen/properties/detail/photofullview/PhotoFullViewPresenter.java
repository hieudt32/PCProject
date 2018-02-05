package app.positiveculture.com.agent.screen.properties.detail.photofullview;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.List;

import app.positiveculture.com.data.response.dto.FileDTO;

/**
 * The PhotoFullView Presenter
 */
public class PhotoFullViewPresenter extends Presenter<PhotoFullViewContract.View, PhotoFullViewContract.Interactor>
        implements PhotoFullViewContract.Presenter {

  private List<FileDTO> mListPhoto;
  private PhotoFullViewAdapter mAdapterPhoto;

  public PhotoFullViewPresenter setListPhoto(List<FileDTO> listPhoto) {
    this.mListPhoto = listPhoto;
    return this;
  }

  public PhotoFullViewPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public PhotoFullViewContract.View onCreateView() {
    return PhotoFullViewFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
    if(mListPhoto!=null && !mListPhoto.isEmpty()){

      mAdapterPhoto= new PhotoFullViewAdapter(mListPhoto);
      mView.bindPhoto(mAdapterPhoto, mListPhoto.size());
    }
  }

  @Override
  public PhotoFullViewContract.Interactor onCreateInteractor() {
    return new PhotoFullViewInteractor(this);
  }
}
