package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by HaiLS on 10/11/2017.
 */

public class AddressComponent {

  @SerializedName("long_name")
  private String mLongName;
  @SerializedName("short_name")
  private String mShortName;
  @SerializedName("types")
  private ArrayList<String> mTypes;

  public String getLongName() {
    return mLongName;
  }

  public void setLongName(String mLongName) {
    this.mLongName = mLongName;
  }

  public String getShortName() {
    return mShortName;
  }

  public void setShortName(String mShortName) {
    this.mShortName = mShortName;
  }

  public String getType() {
    return mTypes != null ? mTypes.get(0) : "";
  }

  public void setTypes(ArrayList<String> types) {
    this.mTypes = types;
  }

}
