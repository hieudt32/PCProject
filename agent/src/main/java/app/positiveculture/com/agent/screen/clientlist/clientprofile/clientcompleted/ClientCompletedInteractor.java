package app.positiveculture.com.agent.screen.clientlist.clientprofile.clientcompleted;

import com.gemvietnam.base.viper.Interactor;

import java.util.List;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The ClientCompleted interactor
 */
class ClientCompletedInteractor extends Interactor<ClientCompletedContract.Presenter>
        implements ClientCompletedContract.Interactor {

  ClientCompletedInteractor(ClientCompletedContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getListCompletedClient(String idClient, int mLimit, int mOffSet, String mFilter, CommonCallback<List<PropertyDTO>> commonCallback) {
    ServiceBuilder.getInstance().getService().getListPropertyClient(idClient, mLimit, mOffSet, mFilter).enqueue(commonCallback);
  }
}
