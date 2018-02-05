package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

/**
 * RegisterAgentDTO
 * Created by hungdn on 8/25/2017.
 */

public class RegisterAgentDTO {

  @SerializedName("email")
  private String mEmail;
  @SerializedName("password")
  private String mPassword;
  @SerializedName("sms_token")
  private String mSmsToken;

  public String getEmail() {
    return mEmail;
  }

  public void setEmail(String email) {
    mEmail = email;
  }

  public String getPassword() {
    return mPassword;
  }

  public void setPassword(String password) {
    mPassword = password;
  }

  public String getSmsToken() {
    return mSmsToken;
  }

  public void setSmsToken(String smsToken) {
    mSmsToken = smsToken;
  }
}
