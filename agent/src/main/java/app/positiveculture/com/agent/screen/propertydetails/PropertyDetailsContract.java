package app.positiveculture.com.agent.screen.propertydetails;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.response.dto.CreatePropertyDTO;

/**
 * The PropertyDetails Contract
 */
interface PropertyDetailsContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
    void hideFloorSizeLanded();

    void setDetailFromSummary(CreatePropertyDTO createPropertyDTO);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goBack();

    void goToPriceScreen(int valueRoom, int valuebathRoom, int valuePlusRoom, float valueFloorSizeBuildUp, float valueFloorSizeLanded, String floorSizeUnit);

    void goBackToSummary(int room, int bathRoom, int plusRoom, float floorSizeBuildUp, float floorSizeLanded, String floorSizeUnit);
  }
}



