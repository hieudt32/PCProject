package app.positiveculture.com.agent.screen.bankdetails;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.User;

/**
 * The BankDetails interactor
 */
class BankDetailsInteractorAgent extends Interactor<BankDetailsContractAgent.Presenter>
        implements BankDetailsContractAgent.Interactor {

  BankDetailsInteractorAgent(BankDetailsContractAgent.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void updateBankDetail(String bankName, String accountNumber, String accountType, CommonCallback<User> commonCallback) {
    ServiceBuilder.getInstance().getService().saveBankDetail(bankName, accountNumber, accountType).enqueue(commonCallback);
  }
}
