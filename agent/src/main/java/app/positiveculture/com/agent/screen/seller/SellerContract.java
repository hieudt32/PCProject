package app.positiveculture.com.agent.screen.seller;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import java.util.List;

import app.positiveculture.com.agent.screen.properties.property.PropertyAdapter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;

/**
 * The Seller Contract
 */
interface SellerContract {

  interface Interactor extends IInteractor<Presenter> {
    void getListProperty(int limit, int offSet, String filter, CommonCallback<List<PropertyDTO>> commonCallback);

    void deleteProperty(String idProperty, CommonCallback<ResponseDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void bindData(PropertyAdapter data);

    void showSellerEmptyList();

    void loadMoreSuccess();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToProcessTracker(PropertyDTO property);

    void loadMore();
  }
}



