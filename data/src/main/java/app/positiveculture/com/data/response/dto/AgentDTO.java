package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

/**
 * AgentDTO
 * Created by hungdn on 8/31/2017.
 */

public class AgentDTO extends User {
  @SerializedName("verified_email")
  private int mVerifyEmail;
  @SerializedName("uploaded_nric")
  private int mUploadNric;
  @SerializedName("need_verify_nric_photo")
  private int mNeedVerifyPhoto;
  @SerializedName("nric")
  private String mNric;
  @SerializedName("cea")
  private CEAProfileDTO mCEA;
  @SerializedName("agency_license")
  private String mAgency;
  private boolean isSelected;

  public String getAgency() {
    return mAgency;
  }

  public void setAgency(String agency) {
    mAgency = agency;
  }

  public String getNric() {
    return mNric;
  }

  public void setNric(String mNric) {
    this.mNric = mNric;
  }

  public CEAProfileDTO getCEA() {
    return mCEA;
  }

  public void setCEA(CEAProfileDTO mCEA) {
    this.mCEA = mCEA;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public void setSelected(boolean selected) {
    isSelected = selected;
  }

  public int getVerifyEmail() {
    return mVerifyEmail;
  }

  public void setVerifyEmail(int mVerifyEmail) {
    this.mVerifyEmail = mVerifyEmail;
  }

  public int getUploadNric() {
    return mUploadNric;
  }

  public void setUploadNric(int mUploadNric) {
    this.mUploadNric = mUploadNric;
  }

  public int getNeedVerifyPhoto() {
    return mNeedVerifyPhoto;
  }

  public void setNeedVerifyPhoto(int mNeedVerifyPhoto) {
    this.mNeedVerifyPhoto = mNeedVerifyPhoto;
  }
}
