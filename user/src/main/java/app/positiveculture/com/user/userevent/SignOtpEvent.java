package app.positiveculture.com.user.userevent;

import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * SignOtpEvent
 * Created by hieudt on 12/4/2017.
 */

public class SignOtpEvent {
  private OtpDTO mOTP;

  public SignOtpEvent(OtpDTO mOTP) {
    this.mOTP = mOTP;
  }

  public OtpDTO getOTP() {
    return mOTP;
  }
}
