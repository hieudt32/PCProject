package app.positiveculture.com.agent.screen.properties.detail;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.PropertyDisplayDTO;

/**
 * The Detail interactor
 */
class DetailInteractor extends Interactor<DetailContract.Presenter>
        implements DetailContract.Interactor {

  DetailInteractor(DetailContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getDisplayNameProperty(String floor, String unit, String building, String streetOne,
                                     String streetTwo, String country, String postalCode,
                                     CommonCallback<PropertyDisplayDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().getPropertyDisplay(floor, unit, building, streetOne,
            streetTwo, country, postalCode).enqueue(commonCallback);
  }
}
