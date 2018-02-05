package app.positiveculture.com.agent.screen.properties.buyer;

import com.gemvietnam.base.viper.Interactor;

import java.util.List;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The Buyer interactor
 */
class BuyerInteractor extends Interactor<BuyerContract.Presenter>
        implements BuyerContract.Interactor {

  BuyerInteractor(BuyerContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getListProperty(int limit, int offSet, String filterType, CommonCallback<List<PropertyDTO>> commonCallback) {
    ServiceBuilder.getInstance().getService().getListProperty(limit, offSet, filterType).enqueue(commonCallback);
  }
}
