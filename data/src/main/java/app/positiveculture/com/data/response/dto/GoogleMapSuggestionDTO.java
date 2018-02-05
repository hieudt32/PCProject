package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HaiLS on 10/11/2017.
 */

public class GoogleMapSuggestionDTO {
  @SerializedName("description")
  private String mName;
  @SerializedName("place_id")
  private String mPlaceId;
  @SerializedName("id")
  private String mId;

  public String getName() {
    return mName;
  }

  public void setName(String name) {
    this.mName = name;
  }

  public String getPlaceId() {
    return mPlaceId;
  }

  public void setPlaceId(String placeId) {
    this.mPlaceId = placeId;
  }

  public String getId() {
    return mId;
  }

  public void setId(String id) {
    this.mId = id;
  }
}
