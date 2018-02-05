package app.positiveculture.com.agent.screen.propertydetails;

import com.gemvietnam.base.viper.Interactor;

/**
 * The PropertyDetails interactor
 */
class PropertyDetailsInteractor extends Interactor<PropertyDetailsContract.Presenter>
        implements PropertyDetailsContract.Interactor {

  PropertyDetailsInteractor(PropertyDetailsContract.Presenter presenter) {
    super(presenter);
  }
}
