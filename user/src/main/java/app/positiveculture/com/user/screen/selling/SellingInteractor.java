package app.positiveculture.com.user.screen.selling;

import com.gemvietnam.base.viper.Interactor;

import java.util.List;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The Selling interactor
 */
class SellingInteractor extends Interactor<SellingContract.Presenter>
        implements SellingContract.Interactor {

  SellingInteractor(SellingContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getSelling(int limit, int offSet, String filter, CommonCallback<List<PropertyDTO>> commonCallback) {
    ServiceBuilder.getInstance().getService().getListProperty(limit, offSet, filter).enqueue(commonCallback);
  }
}
