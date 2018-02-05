package app.positiveculture.com.data.enumdata;

import com.google.gson.annotations.SerializedName;

/**
 * PropertiesCountDTO
 * Created by hieudt on 12/4/2017.
 */

public class PropertiesCountDTO {
  @SerializedName("total_property_count: 5")
  private int mTotalPropertiesCount;

  public int getmTotalPropertiesCount() {
    return mTotalPropertiesCount;
  }

  public void setmTotalPropertiesCount(int mTotalPropertiesCount) {
    this.mTotalPropertiesCount = mTotalPropertiesCount;
  }
}
