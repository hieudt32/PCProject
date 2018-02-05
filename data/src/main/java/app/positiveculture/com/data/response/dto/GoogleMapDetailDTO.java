package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HaiLS on 10/11/2017.
 */

public class GoogleMapDetailDTO {

  @SerializedName("result")
  private LocationSuggestResult mLocationSuggestResult;
  @SerializedName("status")
  private String mStatus;

  public LocationSuggestResult getResult() {
    return mLocationSuggestResult;
  }

  public void setResult(LocationSuggestResult mLocationSuggestResult) {
    this.mLocationSuggestResult = mLocationSuggestResult;
  }

  public String getStatus() {
    return mStatus;
  }

  public void setStatus(String mStatus) {
    this.mStatus = mStatus;
  }
}
