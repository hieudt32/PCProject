package app.positiveculture.com.agent.screen.properties.detail;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.data.response.dto.PropertyDisplayDTO;

/**
 * The Detail Contract
 */
interface DetailContract {

  interface Interactor extends IInteractor<Presenter> {
    void getDisplayNameProperty(String floor, String unit, String building, String streetOne, String streetTwo,
                                String country, String postalCode, CommonCallback<PropertyDisplayDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void bindData(PropertyDTO propertyDTO);

    void hideButtonCreateOTP();

    void bindDataUserView(PropertyDTO mPropertyDTO);

    void showReviewOTP(OtpDTO mOTP);

    void displayNameProperty(PropertyDisplayDTO data);
  }

  interface Presenter extends IPresenter<View, Interactor> {

    void gotoCreateOtp();

    void goToDocumentsScreen();

    void setComeBack();

    void goToPhotoFullView();

    void goToAgentProfileScreen();

    void getDisplayNameProperty();
  }
}



