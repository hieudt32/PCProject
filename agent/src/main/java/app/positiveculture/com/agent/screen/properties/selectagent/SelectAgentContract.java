package app.positiveculture.com.agent.screen.properties.selectagent;

import android.support.v7.widget.RecyclerView;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import java.util.List;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.AgentDTO;

/**
 * The SelectAgent Contract
 */
interface SelectAgentContract {

  interface Interactor extends IInteractor<Presenter> {
    void getListAgent(int offset, int limit, String searchKey, CommonCallback<List<AgentDTO>> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void bindData();

    void onLoadMoreAgentDone(boolean canLoadMore);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goBack();

    RecyclerView.Adapter getAgentAdapter();

    void onMoreAgentAsked();

    void searchAgent(String searchKey);

    void doneSelectAgent();
  }
}



