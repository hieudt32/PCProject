package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by HaiLS on 10/11/2017.
 */

public class LocationSuggestResult {
  @SerializedName("place_id")
  private String mPlaceId;
  @SerializedName("address_components")
  private ArrayList<AddressComponent> mAddressComponentList;
  @SerializedName("formatted_address")
  private String mFormattedAddress;
  @SerializedName("formatted_phone_number")
  private String mFormattedPhoneNumber;
  @SerializedName("geometry")
  private Geometry mGeometry;
  @SerializedName("name")
  private String mPlaceName;
  @SerializedName("vicinity")
  private String mVicinity;

  public String getPlaceId() {
    return mPlaceId;
  }

  public void setPlaceId(String mPlaceId) {
    this.mPlaceId = mPlaceId;
  }

  public ArrayList<AddressComponent> getAddressComponentList() {
    return mAddressComponentList;
  }

  public void setAddressComponentList(ArrayList<AddressComponent> mAddressComponentList) {
    this.mAddressComponentList = mAddressComponentList;
  }

  public String getFormattedAddress() {
    return mFormattedAddress;
  }

  public void setFormattedAddress(String mFormattedAddress) {
    this.mFormattedAddress = mFormattedAddress;
  }

  public String getFormattedPhoneNumber() {
    return mFormattedPhoneNumber;
  }

  public void setFormattedPhoneNumber(String mFormattedPhoneNumber) {
    this.mFormattedPhoneNumber = mFormattedPhoneNumber;
  }

  public Geometry getGeometry() {
    return mGeometry;
  }

  public void setGeometry(Geometry mGeometry) {
    this.mGeometry = mGeometry;
  }

  public String getPlaceName() {
    return mPlaceName;
  }

  public void setPlaceName(String mPlaceName) {
    this.mPlaceName = mPlaceName;
  }

  public String getVicinity() {
    return mVicinity;
  }

  public void setVicinity(String mVicinity) {
    this.mVicinity = mVicinity;
  }
}
