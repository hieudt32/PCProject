package app.positiveculture.com.agent.screen.properties.property;

import com.gemvietnam.base.viper.Interactor;

import java.util.List;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;

/**
 * The Property interactor
 */
class PropertyInteractor extends Interactor<PropertyContract.Presenter>
        implements PropertyContract.Interactor {

  PropertyInteractor(PropertyContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getListProperty(int limit, int offSet, String filterType, CommonCallback<List<PropertyDTO>> commonCallback) {
    ServiceBuilder.getInstance().getService().getListProperty(limit, offSet, filterType).enqueue(commonCallback);
  }

  @Override
  public void deleteProperty(String idProperty, CommonCallback<ResponseDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().deleteProperty(idProperty).enqueue(commonCallback);
  }
}
