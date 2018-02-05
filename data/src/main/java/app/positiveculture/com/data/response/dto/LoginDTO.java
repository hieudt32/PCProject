package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hieudt on 8/25/2017.
 */

public class LoginDTO {

  @SerializedName("email")
  private String mEmail;
  @SerializedName("password")
  private String mPassword;

  public String getmEmail() {
    return mEmail;
  }

  public void setmEmail(String mEmail) {
    this.mEmail = mEmail;
  }

  public String getmPassword() {
    return mPassword;
  }

  public void setmPassword(String mPassword) {
    this.mPassword = mPassword;
  }
}
