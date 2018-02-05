package app.positiveculture.com.agent.screen.clientlist.clientprofile.clientcompleted;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import java.util.List;

import app.positiveculture.com.agent.screen.properties.property.PropertyAdapter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The ClientCompleted Contract
 */
interface ClientCompletedContract {

  interface Interactor extends IInteractor<Presenter> {
    void getListCompletedClient(String idClient, int mLimit, int mOffSet, String mFilter, CommonCallback<List<PropertyDTO>> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void showEmptyLayout();

    void bindListCompleted(PropertyAdapter mCompletedClientAdapter);

    void loadMoreSuccess();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void loadMore();
  }
}



