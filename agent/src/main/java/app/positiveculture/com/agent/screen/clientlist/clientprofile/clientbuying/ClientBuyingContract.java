package app.positiveculture.com.agent.screen.clientlist.clientprofile.clientbuying;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import java.util.List;

import app.positiveculture.com.agent.screen.properties.property.PropertyAdapter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The ClientBuying Contract
 */
interface ClientBuyingContract {

  interface Interactor extends IInteractor<Presenter> {
    void getListBuyingClient(String idClient, int mLimit, int mOffSet, String mFilter, CommonCallback<List<PropertyDTO>> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void showEmptyLayout();

    void bindListBuyingClient(PropertyAdapter mBuyingClientAdapter);

    void loadMoreSuccess();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void loadMore();
  }
}



