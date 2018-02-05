package app.positiveculture.com.agent.screen.locationaddress;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;
import com.google.android.gms.maps.model.LatLng;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.Address;
import app.positiveculture.com.data.response.dto.CreatePropertyDTO;

/**
 * The Location_Adress Contract
 */
interface LocationAddressContract {

	interface Interactor extends IInteractor<Presenter> {
	}

	interface View extends PresentView<Presenter> {
		void addLocation(LatLng latLng,String title);

		void bindAddr(Address address);

		void bindDataFromSummary(CreatePropertyDTO createPropertyDTO);
	}

	interface Presenter extends IPresenter<View, Interactor> {
		LatLng getLatLng();

		void refreshMap(String location);

		void showFullMap(Address address);

		void goBack();

		void goToPropertyDetail(Address address);

		void goBackToSummary(Address address);
	}
}



