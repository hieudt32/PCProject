package app.positiveculture.com.user.screen.buying;

import com.gemvietnam.base.viper.Interactor;

import java.util.List;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The Buying interactor
 */
class BuyingInteractor extends Interactor<BuyingContract.Presenter>
        implements BuyingContract.Interactor {

  BuyingInteractor(BuyingContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getBuying(int limit, int offSet, String filter, CommonCallback<List<PropertyDTO>> commonCallback) {
    ServiceBuilder.getInstance().getService().getListProperty(limit, offSet, filter).enqueue(commonCallback);
  }
}
