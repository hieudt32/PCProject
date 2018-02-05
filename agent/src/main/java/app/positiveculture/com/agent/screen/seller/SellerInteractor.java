package app.positiveculture.com.agent.screen.seller;

import com.gemvietnam.base.viper.Interactor;

import java.util.List;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;

/**
 * The Seller interactor
 */
class SellerInteractor extends Interactor<SellerContract.Presenter>
        implements SellerContract.Interactor {

  SellerInteractor(SellerContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getListProperty(int limit, int offSet, String filter, CommonCallback<List<PropertyDTO>> commonCallback) {
    ServiceBuilder.getInstance().getService().getListProperty(limit, offSet, filter).enqueue(commonCallback);
  }

  @Override
  public void deleteProperty(String idProperty, CommonCallback<ResponseDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().deleteProperty(idProperty).enqueue(commonCallback);
  }
}
