package app.positiveculture.com.agent.screen.clientlist.clientprofile.clientbuying;

import com.gemvietnam.base.viper.Interactor;

import java.util.List;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The ClientBuying interactor
 */
class ClientBuyingInteractor extends Interactor<ClientBuyingContract.Presenter>
        implements ClientBuyingContract.Interactor {

  ClientBuyingInteractor(ClientBuyingContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getListBuyingClient(String idClient, int mLimit, int mOffSet, String mFilter, CommonCallback<List<PropertyDTO>> commonCallback) {
    ServiceBuilder.getInstance().getService().getListPropertyClient(idClient, mLimit, mOffSet, mFilter).enqueue(commonCallback);
  }
}
