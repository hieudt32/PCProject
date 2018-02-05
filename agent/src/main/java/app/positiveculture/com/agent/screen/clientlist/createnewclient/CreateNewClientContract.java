package app.positiveculture.com.agent.screen.clientlist.createnewclient;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.MemberDTO;

/**
 * The CreateNewClient Contract
 */
interface CreateNewClientContract {

  interface Interactor extends IInteractor<Presenter> {
    void checkClient(String idType, String idNumber, CommonCallback<MemberDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void checkClient(String idType, String idNumber);
  }
}



