package app.positiveculture.com.user.screen.userfullcontact;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * The UserFullContact interactor
 */
class UserFullContactInteractor extends Interactor<UserFullContactContract.Presenter>
        implements UserFullContactContract.Interactor {

  UserFullContactInteractor(UserFullContactContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void rejectOTP(String idOTP, CommonCallback<OtpDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().sellerRejectSigning(idOTP).enqueue(commonCallback);
  }
}
