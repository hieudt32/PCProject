package app.positiveculture.com.agent.screen.seller.verifysigning;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * The VerifySigning interactor
 */
class VerifySigningInteractor extends Interactor<VerifySigningContract.Presenter>
        implements VerifySigningContract.Interactor {

  VerifySigningInteractor(VerifySigningContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void verifyOfflineSigning(String idOTP, CommonCallback<OtpDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().agentVerifySigning(idOTP).enqueue(commonCallback);
  }
}
