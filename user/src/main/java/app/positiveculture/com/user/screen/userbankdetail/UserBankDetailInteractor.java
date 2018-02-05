package app.positiveculture.com.user.screen.userbankdetail;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.agent.dto.BankDetail;
import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The UserBankDetail interactor
 */
class UserBankDetailInteractor extends Interactor<UserBankDetailContract.Presenter>
        implements UserBankDetailContract.Interactor {

  UserBankDetailInteractor(UserBankDetailContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void saveBankDetails(BankDetail mBankDetail, CommonCallback<User> commonCallback) {
    ServiceBuilder.getInstance().getService().saveBankDetail(mBankDetail.getBankName(), mBankDetail.getBankAcountNo(), mBankDetail.getAcountType()).enqueue(commonCallback);
  }
}
