package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HaiLS on 26/10/2017.
 */

public class SolicitorDTO {
  @SerializedName("id")
  private int mId;
  @SerializedName("solicitor_name")
  private String mSolicitorName;
  @SerializedName("solicitor_address")
  private String mSolicitorAddress;
  @SerializedName("created_at")
  private String mCreatedAt;
  @SerializedName("updated_at")
  private String mUpdatedAt;
  private boolean isSelected;

  public int getId() {
    return mId;
  }

  public void setId(int mId) {
    this.mId = mId;
  }

  public String getSolicitorName() {
    return mSolicitorName;
  }

  public void setSolicitorName(String mSolicitorName) {
    this.mSolicitorName = mSolicitorName;
  }

  public String getSolicitorAddress() {
    return mSolicitorAddress;
  }

  public void setSolicitorAddress(String mSolicitorAddress) {
    this.mSolicitorAddress = mSolicitorAddress;
  }

  public String getCreatedAt() {
    return mCreatedAt;
  }

  public void setCreatedAt(String mCreatedAt) {
    this.mCreatedAt = mCreatedAt;
  }

  public String getUpdatedAt() {
    return mUpdatedAt;
  }

  public void setUpdatedAt(String mUpdatedAt) {
    this.mUpdatedAt = mUpdatedAt;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public void setSelected(boolean selected) {
    isSelected = selected;
  }
}
