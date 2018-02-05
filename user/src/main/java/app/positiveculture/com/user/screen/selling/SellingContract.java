package app.positiveculture.com.user.screen.selling;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import java.util.List;

import app.positiveculture.com.agent.screen.properties.property.PropertyAdapter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The Selling Contract
 */
interface SellingContract {

  interface Interactor extends IInteractor<Presenter> {
    void getSelling(int limit, int offSet, String filter, CommonCallback<List<PropertyDTO>> commonCallback);
  }

  interface View extends PresentView<Presenter> {

    void bindProperty(PropertyAdapter mAdapter);

    void loadMoreSuccessful();

    void displayEmptyLayout();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void loadMore();
  }
}



