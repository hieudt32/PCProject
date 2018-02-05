package app.positiveculture.com.user.screen.userbankdetail;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.agent.dto.BankDetail;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The UserBankDetail Contract
 */
interface UserBankDetailContract {

  interface Interactor extends IInteractor<Presenter> {
    void saveBankDetails(BankDetail mBankDetail, CommonCallback<User> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void bindView(BankDetail mBankDetail);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void saveBankDetail(BankDetail mBankDetail);
  }
}



