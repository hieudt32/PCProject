package app.positiveculture.com.agent.screen.clientlist.clientprofile.clientselling;

import com.gemvietnam.base.viper.Interactor;

import java.util.List;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The ClientSelling interactor
 */
class ClientSellingInteractor extends Interactor<ClientSellingContract.Presenter>
        implements ClientSellingContract.Interactor {

  ClientSellingInteractor(ClientSellingContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getListPropertyClient(String idClient, int mLimit, int mOffSet, String mFilter, CommonCallback<List<PropertyDTO>> commonCallback) {
    ServiceBuilder.getInstance().getService().getListPropertyClient(idClient, mLimit, mOffSet, mFilter).enqueue(commonCallback);
  }
}
