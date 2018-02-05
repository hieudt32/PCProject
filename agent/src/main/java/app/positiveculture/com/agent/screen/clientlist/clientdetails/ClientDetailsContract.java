package app.positiveculture.com.agent.screen.clientlist.clientdetails;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.MemberDTO;

/**
 * The ClientDetails Contract
 */
interface ClientDetailsContract {

  interface Interactor extends IInteractor<Presenter> {
    void saveNoteClient(String s, String note, CommonCallback<MemberDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void setupData(MemberDTO client, boolean editable);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void saveNoteMember(String note);
  }
}



