package app.positiveculture.com.agent.screen.description;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.response.dto.CreatePropertyDTO;

/**
 * The Description Contract
 */
interface DescriptionContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
    void bindDataFromSummary(CreatePropertyDTO createPropertyDTO);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToPhotoSreen(String s);

    void goBack();

    void goBackToSummary(String description);
  }
}



