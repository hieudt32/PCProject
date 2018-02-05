package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

import app.positiveculture.com.data.enumdata.TypeID;

/**
 * Created by HaiLS on 26/09/2017.
 */

public class CreateMemberDTO {

  @SerializedName("id_type")
  private TypeID mIdType;
  @SerializedName("id_number")
  private String mIdNumber;
  @SerializedName("email")
  private String mEmail;
  @SerializedName("phone_country_code")
  private String mPhoneCountryCode;
  @SerializedName("phone_number")
  private String mPhoneNumber;
  @SerializedName("full_name")
  private String mFullName;

  public TypeID getIdType() {
    return mIdType;
  }

  public void setIdType(TypeID mIdType) {
    this.mIdType = mIdType;
  }

  public String getIdNumber() {
    return mIdNumber;
  }

  public void setIdNumber(String mIdNumber) {
    this.mIdNumber = mIdNumber;
  }

  public String getEmail() {
    return mEmail;
  }

  public void setEmail(String mEmail) {
    this.mEmail = mEmail;
  }

  public String getPhoneNumber() {
    return mPhoneNumber;
  }

  public void setPhoneNumber(String mPhoneNumber) {
    this.mPhoneNumber = mPhoneNumber;
  }

  public String getFullName() {
    return mFullName;
  }

  public void setFullName(String mFullName) {
    this.mFullName = mFullName;
  }

  public String getPhoneCountryCode() {
    return mPhoneCountryCode;
  }

  public void setPhoneCountryCode(String mPhoneCountryCode) {
    this.mPhoneCountryCode = mPhoneCountryCode;
  }
}
