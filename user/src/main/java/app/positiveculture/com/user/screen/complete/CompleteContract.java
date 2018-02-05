package app.positiveculture.com.user.screen.complete;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import java.util.List;

import app.positiveculture.com.agent.screen.properties.property.PropertyAdapter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The Complete Contract
 */
interface CompleteContract {

  interface Interactor extends IInteractor<Presenter> {
		void getComplete(int limit, int offSet, String filter, CommonCallback<List<PropertyDTO>> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void bindCompleteProperty(PropertyAdapter mCompleteAdapter);

    void loadMoreSuccessful();

    void showEmptyLayout();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void loadMore();
  }
}



