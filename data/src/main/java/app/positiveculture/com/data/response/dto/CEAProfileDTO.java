package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

/**
 * CEAProfileDTO
 * Created by hungdn on 8/24/2017.
 */

public class CEAProfileDTO {

  @SerializedName("id")
  private Integer mId;
  @SerializedName("full_name")
  private String mFullName;
  @SerializedName("cea_no")
  private String mCEANumber;
  @SerializedName("company")
  private String mCompany;
  @SerializedName("phone_number")
  private String mPhoneNumber;
  @SerializedName("phone_country_code")
  private String mCountryCode;
  @SerializedName("agency_license")
  private String mAgencyLicense;
  @SerializedName("created_at")
  private String mCreatedAt;
  @SerializedName("updated_at")
  private String mUpdatedAt;

  public Integer getId() {
    return mId;
  }

  public void setId(Integer id) {
    mId = id;
  }

  public String getFullName() {
    return mFullName;
  }

  public void setFullName(String fullName) {
    mFullName = fullName;
  }

  public String getCEANumber() {
    return mCEANumber;
  }

  public void setCEANumber(String CEANumber) {
    mCEANumber = CEANumber;
  }

  public String getCompany() {
    return mCompany;
  }

  public void setCompany(String company) {
    mCompany = company;
  }

  public String getPhoneNumber() {
    return mPhoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    mPhoneNumber = phoneNumber;
  }

  public String getCountryCode() {
    return mCountryCode;
  }

  public void setCountryCode(String mCountryCode) {
    this.mCountryCode = mCountryCode;
  }

  public String getAgencyLicense() {
    return mAgencyLicense;
  }

  public void setAgencyLicense(String agencyLicense) {
    mAgencyLicense = agencyLicense;
  }

  public String getCreatedAt() {
    return mCreatedAt;
  }

  public void setCreatedAt(String createdAt) {
    mCreatedAt = createdAt;
  }

  public String getUpdatedAt() {
    return mUpdatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    mUpdatedAt = updatedAt;
  }
}
