package app.positiveculture.com.agent.screen.adjustpinmap;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;
import com.google.android.gms.maps.model.LatLng;

/**
 * The AdjustPinMap Contract
 */
interface AdjustPinMapContract {

	interface Interactor extends IInteractor<Presenter> {
	}

	interface View extends PresentView<Presenter> {
		void hideStepIndicator();
	}

	interface Presenter extends IPresenter<View, Interactor> {
		LatLng getLatlng();

		void setLocation(LatLng latlng);

    void goBack();
  }
}



