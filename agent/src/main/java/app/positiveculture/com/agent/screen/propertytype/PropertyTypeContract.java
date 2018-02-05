package app.positiveculture.com.agent.screen.propertytype;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.enumdata.TypeProperty;

/**
 * The PropertyType Contract
 */
interface PropertyTypeContract {

    interface Interactor extends IInteractor<Presenter> {
    }

    interface View extends PresentView<Presenter> {
    }

    interface Presenter extends IPresenter<View, Interactor> {
      void goToLocation(TypeProperty mSelected);

      void goBack();
    }
}



