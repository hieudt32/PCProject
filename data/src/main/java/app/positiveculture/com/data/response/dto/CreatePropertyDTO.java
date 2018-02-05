package app.positiveculture.com.data.response.dto;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.data.enumdata.TypeProperty;

/**
 * CreatePropertyDTO
 * Created by hieudt on 9/1/2017.
 */

public class CreatePropertyDTO {

  @SerializedName("type")
  private TypeProperty mType;
  @SerializedName("building")
  private String mBuilding;
  @SerializedName("latitude")
  private double mLatitude;
  @SerializedName("longitude")
  private double mLongitude;
  @SerializedName("street_line_one")
  private String mStreetLineOne;
  @SerializedName("street_line_two")
  private String mStreetLineTwo;
  @SerializedName("country")
  private String mCountry;
  @SerializedName("postal_code")
  private String mPostalCode;
  @SerializedName("rooms")
  private int mRooms;
  @SerializedName("plus_rooms")
  private int mPlusRooms;
  @SerializedName("bathrooms")
  private int mBathrooms;
  @SerializedName("floor_size_built")
  private float mFloorSizeBuilt;
  @SerializedName("floor_size_landed")
  private float mFloorSizeLanded;
  @SerializedName("floor_size_unit")
  private String mFloorSizeUnit;
  @SerializedName("price")
  private double mPrice;
  @SerializedName("valuation")
  private double mValuation;
  @SerializedName("floor")
  private String mFloor;
  @SerializedName("unit")
  private String mUnit;
  @SerializedName("description")
  private String mDescription;
  @SerializedName("property_seller")
  private JsonArray mPropertySeller;
  @SerializedName("feature_image")
  private long mFeatureImage;
  @SerializedName("gallery")
  private JsonArray mListGallery;

  private transient ArrayList<FileDTO> mGallery;
  private transient List<MemberDTO> mListOwner;

  public TypeProperty getmType() {
    return mType;
  }

  public void setmType(TypeProperty mType) {
    this.mType = mType;
  }

  public String getmBuilding() {
    return mBuilding;
  }

  public void setmBuilding(String mBuilding) {
    this.mBuilding = mBuilding;
  }

  public double getmLatitude() {
    return mLatitude;
  }

  public void setmLatitude(double mLatitude) {
    this.mLatitude = mLatitude;
  }

  public double getmLongitude() {
    return mLongitude;
  }

  public void setmLongitude(double mLongitude) {
    this.mLongitude = mLongitude;
  }

  public String getmStreetLineOne() {
    return mStreetLineOne;
  }

  public void setmStreetLineOne(String mStreetLineOne) {
    this.mStreetLineOne = mStreetLineOne;
  }

  public String getmStreetLineTwo() {
    return mStreetLineTwo;
  }

  public void setmStreetLineTwo(String mStreetLineTwo) {
    this.mStreetLineTwo = mStreetLineTwo;
  }

  public String getmCountry() {
    return mCountry;
  }

  public void setmCountry(String mCountry) {
    this.mCountry = mCountry;
  }

  public String getmPostalCode() {
    return mPostalCode;
  }

  public void setmPostalCode(String mPostalCode) {
    this.mPostalCode = mPostalCode;
  }

  public int getmRooms() {
    return mRooms;
  }

  public void setmRooms(int mRooms) {
    this.mRooms = mRooms;
  }

  public int getmPlusRooms() {
    return mPlusRooms;
  }

  public void setmPlusRooms(int mPlusRooms) {
    this.mPlusRooms = mPlusRooms;
  }

  public int getmBathrooms() {
    return mBathrooms;
  }

  public void setmBathrooms(int mBathrooms) {
    this.mBathrooms = mBathrooms;
  }

  public float getmFloorSizeBuilt() {
    return mFloorSizeBuilt;
  }

  public void setmFloorSizeBuilt(float mFloorSizeBuilt) {
    this.mFloorSizeBuilt = mFloorSizeBuilt;
  }

  public float getmFloorSizeLanded() {
    return mFloorSizeLanded;
  }

  public void setmFloorSizeLanded(float mFloorSizeLanded) {
    this.mFloorSizeLanded = mFloorSizeLanded;
  }

  public String getmFloorSizeUnit() {
    return mFloorSizeUnit;
  }

  public void setmFloorSizeUnit(String mFloorSizeUnit) {
    this.mFloorSizeUnit = mFloorSizeUnit;
  }

  public double getmPrice() {
    return mPrice;
  }

  public void setmPrice(double mPrice) {
    this.mPrice = mPrice;
  }

  public double getmValuation() {
    return mValuation;
  }

  public void setmValuation(double mValuation) {
    this.mValuation = mValuation;
  }

  public String getmDescription() {
    return mDescription;
  }

  public void setmDescription(String mDescription) {
    this.mDescription = mDescription;
  }

  public JsonArray getmPropertySeller() {
    return mPropertySeller;
  }

  public void setmPropertySeller(JsonArray mPropertySeller) {
    this.mPropertySeller = mPropertySeller;
  }

  public ArrayList<FileDTO> getmGallery() {
    return mGallery;
  }

  public void setmGallery(ArrayList<FileDTO> mGallery) {
    this.mGallery = mGallery;
  }

  public String getmFloor() {
    return mFloor;
  }

  public void setmFloor(String mFloor) {
    this.mFloor = mFloor;
  }

  public String getmUnit() {
    return mUnit;
  }

  public void setmUnit(String mUnit) {
    this.mUnit = mUnit;
  }

  public List<MemberDTO> getmListOwner() {
    return mListOwner;
  }

  public void setmListOwner(List<MemberDTO> mListOwner) {
    this.mListOwner = mListOwner;
  }

  public long getmFeatureImage() {
    return mFeatureImage;
  }

  public void setmFeatureImage(long mFeatureImage) {
    this.mFeatureImage = mFeatureImage;
  }

  public JsonArray getmListGallery() {
    return mListGallery;
  }

  public void setmListGallery(JsonArray mListGallery) {
    this.mListGallery = mListGallery;
  }
}
