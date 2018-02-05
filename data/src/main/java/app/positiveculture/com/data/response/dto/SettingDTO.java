package app.positiveculture.com.data.response.dto;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

/**
 * SettingDTO
 * Created by hieudt on 9/21/2017.
 */

public class SettingDTO {
  @SerializedName("verify_agent_need_upload_image")
  private int mNeedUploadImage;
  @SerializedName("need_verify_member_phone_number")
  private int mNeedVerifyPhoneNumber;
  @SerializedName("country_phone_code")
  private JsonArray mCountryCode;

  public JsonArray getmCountryCode() {
    return mCountryCode;
  }

  public void setmCountryCode(JsonArray mCountryCode) {
    this.mCountryCode = mCountryCode;
  }

  public int getNeedUploadImage() {
    return mNeedUploadImage;
  }

  public void setNeedUploadImage(int mNeedUploadImage) {
    this.mNeedUploadImage = mNeedUploadImage;
  }

  public int getNeedVerifyPhoneNumber() {
    return mNeedVerifyPhoneNumber;
  }

  public void setNeedVerifyPhoneNumber(int mNeedverifyPhoneNumber) {
    this.mNeedVerifyPhoneNumber = mNeedverifyPhoneNumber;
  }
}
