package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

/**
 * CeaSmsDTO
 * Created by hieudt on 8/25/2017.
 */

public class CeaSmsDTO {
  @SerializedName("sms_token")
  private String mSmsToken;

  public String getSmsToken() {
    return mSmsToken;
  }

  public void setSmsToken(String smsToken) {
    mSmsToken = smsToken;
  }
}
