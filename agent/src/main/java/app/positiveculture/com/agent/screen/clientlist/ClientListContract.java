package app.positiveculture.com.agent.screen.clientlist;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import java.util.List;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;

/**
 * The ClientList Contract
 */
interface ClientListContract {

  interface Interactor extends IInteractor<Presenter> {
    void getListClient(int limit, int offset, String nameSearch, CommonCallback<List<MemberDTO>> commonCallback);

    void getProfileAgent(CommonCallback<AgentDTO> verified);
  }

  interface View extends PresentView<Presenter> {
    void bindData(ClientListAdapter mAdapter);

    void loadMoreSuccess();

    void showNoClient();

    void reloadClientList();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToCreateNewClient();

    void goToClientProfile(MemberDTO memberDTO);

    void loadMoreClient(String nameSearch);

    void searchName(String name);

    void refreshList(String nameSearch);
  }
}



