package app.positiveculture.com.agent.screen.properties.detail;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.event.OtpSentOutEvent;
import app.positiveculture.com.agent.screen.agentprofile.AgentProfilePresenter;
import app.positiveculture.com.agent.screen.properties.createotp.CreateOtpPresenter;

import app.positiveculture.com.agent.screen.documents.DocumentsPresenter;
import app.positiveculture.com.agent.screen.properties.detail.photofullview.PhotoFullViewPresenter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.data.response.dto.PropertyDisplayDTO;

/**
 * The Detail Presenter
 */
public class DetailPresenter extends Presenter<DetailContract.View, DetailContract.Interactor>
        implements DetailContract.Presenter {

  private PropertyDTO mPropertyDTO;
  private OnBackToPropertyScreenListener mOnBackToPropertyScreenListener;
  private OnBackToSummaryScreenListener mOnBackToSummaryScreenListener;
  private OnBackToProcessTrackerAgentScreenListener mOnBackToProcessTrackerAgentScreenListener;
  private OnBackToProcessTrackerUserScreenListener mOnBackToProcessTrackerUserScreenListener;
  private OtpDTO mOTP;

  public DetailPresenter setOnBackToProcessTrackerUserScreenListener(OnBackToProcessTrackerUserScreenListener mOnBackToProcessTrackerUserScreenListener) {
    this.mOnBackToProcessTrackerUserScreenListener = mOnBackToProcessTrackerUserScreenListener;
    return this;
  }

  public DetailPresenter setOnBackToProcessTrackerAgentScreenListener(OnBackToProcessTrackerAgentScreenListener mOnBackToProcessTrackerAgentScreenListener) {
    this.mOnBackToProcessTrackerAgentScreenListener = mOnBackToProcessTrackerAgentScreenListener;
    return this;
  }

  public DetailPresenter setOnBackToPropertyScreenListener(OnBackToPropertyScreenListener mOnBackToPropertyScreenListener) {
    this.mOnBackToPropertyScreenListener = mOnBackToPropertyScreenListener;
    return this;
  }

  public DetailPresenter setOnBackToSummaryScreenListener(OnBackToSummaryScreenListener mOnBackToSummaryScreenListener) {
    this.mOnBackToSummaryScreenListener = mOnBackToSummaryScreenListener;
    return this;
  }

  public DetailPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public DetailContract.View onCreateView() {
    return DetailFragment.getInstance();
  }

  @Override
  public void start() {
    if (mPropertyDTO != null) {
      mView.bindData(mPropertyDTO);
    }

    if (mOnBackToSummaryScreenListener != null) {
      mView.hideButtonCreateOTP();
      getDisplayNameProperty();
    }

    if (mOnBackToProcessTrackerUserScreenListener != null) {
      mView.bindDataUserView(mPropertyDTO);
    }

    if (mOnBackToProcessTrackerAgentScreenListener != null && mOTP != null) {
      mView.showReviewOTP(mOTP);
    }
  }

  @Override
  public void gotoCreateOtp() {
    new CreateOtpPresenter(mContainerView)
            .setPropertyDTO(mPropertyDTO)
            .setOTP(mOTP)
            .pushView();
  }

  @Override
  public void goToDocumentsScreen() {
    new DocumentsPresenter(mContainerView)
            .setPropertyDTO(mPropertyDTO)
            .pushView();
  }

  @Override
  public DetailContract.Interactor onCreateInteractor() {
    return new DetailInteractor(this);
  }

  public DetailPresenter setProperty(PropertyDTO property) {
    mPropertyDTO = property;
    return this;
  }

  @Override
  public void setComeBack() {
    if (mOnBackToPropertyScreenListener != null) {
      mOnBackToPropertyScreenListener.onResetPropertyClick();
    }

    if (mOnBackToSummaryScreenListener != null) {
      mOnBackToSummaryScreenListener.onResetPreviewClick();
    }

    if (mOnBackToProcessTrackerAgentScreenListener != null) {
      mOnBackToProcessTrackerAgentScreenListener.onResetNextClickAgent();
    }

    if (mOnBackToProcessTrackerUserScreenListener != null) {
      mOnBackToProcessTrackerUserScreenListener.onResetNextClickUser();
    }
  }

  @Override
  public void goToPhotoFullView() {
    if (mPropertyDTO == null) return;
    List<FileDTO> listPhoto = new ArrayList<>();
    if (mPropertyDTO.getmFeatrureImage() != null) listPhoto.add(mPropertyDTO.getmFeatrureImage());
    if (mPropertyDTO.getmGallery() != null && !mPropertyDTO.getmGallery().isEmpty()) {
      listPhoto.addAll(mPropertyDTO.getmGallery());
    }
    new PhotoFullViewPresenter(mContainerView)
            .setListPhoto(listPhoto)
            .pushView();
  }

  @Override
  public void getDisplayNameProperty() {
    mView.showProgress();
    mInteractor.getDisplayNameProperty(mPropertyDTO.getmFloor(), mPropertyDTO.getmUnit(),
            mPropertyDTO.getmBuilding(), mPropertyDTO.getmStreetLineOne(), mPropertyDTO.getmStreetLineTwo(),
            mPropertyDTO.getmCountry(), mPropertyDTO.getmPostalCode(), new CommonCallback<PropertyDisplayDTO>(getViewContext()){
              @Override
              public void onSuccess(PropertyDisplayDTO data) {
                super.onSuccess(data);
                mView.displayNameProperty(data);
              }
            });
  }

  @Override
  public void goToAgentProfileScreen() {
    new AgentProfilePresenter(mContainerView)
            .setAgentProfile(mPropertyDTO.getmAgent())
            .pushView();
  }

  public DetailPresenter setOTP(OtpDTO mOTP) {
    this.mOTP = mOTP;
    return this;
  }

  public interface OnBackToPropertyScreenListener {
    void onResetPropertyClick();
  }

  public interface OnBackToSummaryScreenListener {
    void onResetPreviewClick();
  }

  public interface OnBackToProcessTrackerAgentScreenListener {
    void onResetNextClickAgent();
  }

  public interface OnBackToProcessTrackerUserScreenListener {
    void onResetNextClickUser();
  }

  @Subscribe
  public void onOtpSentOutSuccess(OtpSentOutEvent otpSentOutEvent) {
    mView.hideButtonCreateOTP();
  }
}
