package app.positiveculture.com.agent.screen.clientlist.clientprofile.clientselling;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import java.util.List;

import app.positiveculture.com.agent.screen.properties.property.PropertyAdapter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The ClientSelling Contract
 */
interface ClientSellingContract {

  interface Interactor extends IInteractor<Presenter> {
    void getListPropertyClient(String idClient, int mLimit, int mOffSet, String mFilter, CommonCallback<List<PropertyDTO>> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void showEmptyLayout();

    void bindListSelling(PropertyAdapter mSellingClientAdapter);

    void loadMoreSuccess();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void loadMore();
  }
}



