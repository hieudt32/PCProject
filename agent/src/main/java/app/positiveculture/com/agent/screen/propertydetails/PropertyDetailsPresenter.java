package app.positiveculture.com.agent.screen.propertydetails;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.agent.screen.price.PricePresenter;
import app.positiveculture.com.data.enumdata.TypeProperty;
import app.positiveculture.com.data.response.dto.CreatePropertyDTO;

/**
 * The PropertyDetails Presenter
 */
public class PropertyDetailsPresenter extends Presenter<PropertyDetailsContract.View, PropertyDetailsContract.Interactor>
        implements PropertyDetailsContract.Presenter {

  private CreatePropertyDTO createPropertyDTO = null;
  private OnGetPropertyDetailListener mOnGetPropertyDetailListener;

  public PropertyDetailsPresenter setGetPropertyDetailListener(OnGetPropertyDetailListener OnGetPropertyDetailListener) {
    this.mOnGetPropertyDetailListener = OnGetPropertyDetailListener;
    return this;
  }

  public PropertyDetailsPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public PropertyDetailsContract.View onCreateView() {
    return PropertyDetailsFragment.getInstance();
  }

  @Override
  public void start() {
    if (createPropertyDTO != null && createPropertyDTO.getmType() != TypeProperty.landed) {
      mView.hideFloorSizeLanded();
    }
    if (createPropertyDTO != null && createPropertyDTO.getmFloorSizeBuilt() != 0 && mOnGetPropertyDetailListener != null) {
      mView.setDetailFromSummary(createPropertyDTO);
    }

  }

  @Override
  public void goBack() {
    mContainerView.back();
  }

  @Override
  public void goToPriceScreen(int valueRoom, int valuebathRoom, int valuePlusRoom, float valueFloorSizeBuildUp, float valueFloorSizeLanded, String floorSizeUnit) {
    createPropertyDTO.setmRooms(valueRoom);
    createPropertyDTO.setmBathrooms(valuebathRoom);
    createPropertyDTO.setmPlusRooms(valuePlusRoom);
    createPropertyDTO.setmFloorSizeBuilt(valueFloorSizeBuildUp);
    createPropertyDTO.setmFloorSizeLanded(valueFloorSizeLanded);
    createPropertyDTO.setmFloorSizeUnit(floorSizeUnit);
    new PricePresenter(mContainerView)
            .setCreatePropertyDTO(createPropertyDTO)
            .pushView();
  }

  @Override
  public void goBackToSummary(int room, int bathRoom, int plusRoom, float floorSizeBuildUp, float floorSizeLanded, String floorSizeUnit) {
    createPropertyDTO.setmRooms(room);
    createPropertyDTO.setmBathrooms(bathRoom);
    createPropertyDTO.setmPlusRooms(plusRoom);
    createPropertyDTO.setmFloorSizeBuilt(floorSizeBuildUp);
    createPropertyDTO.setmFloorSizeLanded(floorSizeLanded);
    createPropertyDTO.setmFloorSizeUnit(floorSizeUnit);
    if (mOnGetPropertyDetailListener != null)
      mOnGetPropertyDetailListener.onGetDetailSuccessful(createPropertyDTO);

    mContainerView.back();
  }

  @Override
  public PropertyDetailsContract.Interactor onCreateInteractor() {
    return new PropertyDetailsInteractor(this);
  }

  public PropertyDetailsPresenter setCreatePropertyDTO(CreatePropertyDTO createPropertyDTO) {
    this.createPropertyDTO = createPropertyDTO;
    return this;
  }

  public interface OnGetPropertyDetailListener {
    void onGetDetailSuccessful(CreatePropertyDTO createPropertyDTO);
  }
}
