package app.positiveculture.com.agent.screen.description;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.agent.screen.photo.PhotoPresenter;
import app.positiveculture.com.data.response.dto.CreatePropertyDTO;

/**
 * The Description Presenter
 */
public class DescriptionPresenter extends Presenter<DescriptionContract.View, DescriptionContract.Interactor>
        implements DescriptionContract.Presenter {

  private CreatePropertyDTO createPropertyDTO;
  private OnGetPropertyDescriptionListener mOnGetPropertyDescriptionListener;

  public DescriptionPresenter setmOnGetPropertyDescriptionListener(OnGetPropertyDescriptionListener mOnGetPropertyDescriptionListener) {
    this.mOnGetPropertyDescriptionListener = mOnGetPropertyDescriptionListener;
    return this;
  }


  public DescriptionPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public DescriptionContract.View onCreateView() {
    return DescriptionFragment.getInstance();
  }

  @Override
  public void start() {
    if (createPropertyDTO != null && createPropertyDTO.getmDescription() != null && mOnGetPropertyDescriptionListener != null) {
      if (createPropertyDTO.getmDescription() != null)
        mView.bindDataFromSummary(createPropertyDTO);
    }
  }

  @Override
  public void goToPhotoSreen(String s) {
    createPropertyDTO.setmDescription(s);
    new PhotoPresenter(mContainerView)
            .setCreatePropertyDTO(createPropertyDTO)
            .pushView();
  }

  @Override
  public void goBack() {
    mContainerView.back();
  }

  @Override
  public void goBackToSummary(String description) {
    createPropertyDTO.setmDescription(description);
    if (mOnGetPropertyDescriptionListener != null) {
      mOnGetPropertyDescriptionListener.onGetDescriptionSuccessful(createPropertyDTO);
    }
    mContainerView.back();
  }

  @Override
  public DescriptionContract.Interactor onCreateInteractor() {
    return new DescriptionInteractor(this);
  }

  public DescriptionPresenter setCreatePropertyDTO(CreatePropertyDTO createPropertyDTO) {
    this.createPropertyDTO = createPropertyDTO;
    return this;
  }

  public interface OnGetPropertyDescriptionListener {
    void onGetDescriptionSuccessful(CreatePropertyDTO createPropertyDTO);
  }
}
