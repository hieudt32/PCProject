package app.positiveculture.com.agent.screen.selectowners;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import java.util.List;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * The SelectOwners Contract
 */
interface SelectOwnersContract {

  interface Interactor extends IInteractor<Presenter> {
    void getListOwners(int limit, int offSet, String nameSearch, CommonCallback<List<MemberDTO>> commonCallback);

    void assignClient(long idOTP, List<MemberDTO> mListSelected, CommonCallback<OtpDTO> commonCallback);

    void reassignedClient(long idOTP, List<MemberDTO> mListSelected, CommonCallback<OtpDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void bindSearchData(SelectOwnersAdapter mListUser);

    void bindDataSelectedOwner(List<MemberDTO> mListSelected);

    void bindViewFromSummary();

    void onLoadMoreSuccessful();

    void bindViewForAssignClient();

    void bindViewForReassignClient();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goBack();

    void goToPropertyType();

    void onSearchOwner(String name);

    void goToCreateNewClient();

    void loadMoreOwner(String nameSearch);

    void refreshList(String mNameSearch);
  }
}



