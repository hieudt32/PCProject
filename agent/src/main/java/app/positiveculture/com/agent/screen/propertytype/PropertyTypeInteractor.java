package app.positiveculture.com.agent.screen.propertytype;

import com.gemvietnam.base.viper.Interactor;

/**
 * The PropertyType interactor
 */
class PropertyTypeInteractor extends Interactor<PropertyTypeContract.Presenter>
        implements PropertyTypeContract.Interactor {

    PropertyTypeInteractor(PropertyTypeContract.Presenter presenter) {
        super(presenter);
    }
}
