package app.positiveculture.com.user.screen.userprofile;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.MemberDTO;

/**
 * The UserProfile Contract
 */
interface UserProfileContract {

  interface Interactor extends IInteractor<Presenter> {
    void getProfile(CommonCallback<MemberDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void creatProfile(MemberDTO memberDTO);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void showEditProfil();

    void showLoanForm();
  }
}



