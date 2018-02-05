package app.positiveculture.com.agent.screen.properties.buyer;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import java.util.List;

import app.positiveculture.com.agent.screen.properties.property.PropertyAdapter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The Buyer Contract
 */
interface BuyerContract {

  interface Interactor extends IInteractor<Presenter> {
    void getListProperty(int limit, int offSet,String filterType, CommonCallback<List<PropertyDTO>> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void bindData(PropertyAdapter data);

    void showBuyerEmptyList();

    void loadMoreSuccess();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToProcessTracker(PropertyDTO propertyDTO);

    void loadMore();
  }
}



