package app.positiveculture.com.agent.screen.summary;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.CreatePropertyDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The Summary Contract
 */
interface SummaryContract {

  interface Interactor extends IInteractor<Presenter> {
    void createProperty(CreatePropertyDTO createPropertyDTO, CommonCallback<PropertyDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void bindDataToView(CreatePropertyDTO createPropertyDTO);

    void bindPriceFromPriceEdit(CreatePropertyDTO createPropertyDTO);

    void bindDetailFromDetailEdit(CreatePropertyDTO createPropertyDTO);

    void bindDataFromDescriptionEdit(CreatePropertyDTO createPropertyDTO);

    void bindDataFromLocationAddress(CreatePropertyDTO createPropertyDTO);

    void bindListOwners(AdapterBuyerSummary mAdapterBuyerSummary);

    void enableClick();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToDetailScreen();


    void gotoPropertiesScreen();

    void createProperty();

    void goToPriceScreen();

    void goToLocationAddressScreen();

    void goToPropertyDetailScreen();

    void goToDescriptionScreen();

    void goToSelectOwner();

    void backToPhoto();
  }
}



