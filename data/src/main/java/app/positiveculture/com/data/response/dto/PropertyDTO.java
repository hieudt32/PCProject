package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.data.enumdata.PropertyStatus;
import app.positiveculture.com.data.enumdata.TypeProperty;

/**
 * PropertyDTO
 * Created by hieudt on 9/6/2017.
 */

public class PropertyDTO {
  @SerializedName("id")
  private Long mId;
  @SerializedName("type")
  private TypeProperty mType;
  @SerializedName("building")
  private String mBuilding;
  @SerializedName("latitude")
  private double mLatitude;
  @SerializedName("longitude")
  private double mLongitude;
  @SerializedName("floor")
  private String mFloor;
  @SerializedName("unit")
  private String mUnit;
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
  @SerializedName("description")
  private String mDescription;
  @SerializedName("reference_number")
  private String mReferenceNumber;
  @SerializedName("status")
  private PropertyStatus mStatus;
  @SerializedName("property_seller")
  private List<MemberDTO> mPropertySeller;
  @SerializedName("feature_image")
  private FileDTO mFeatrureImage;
  @SerializedName("gallery")
  private ArrayList<FileDTO> mGallery;
  @SerializedName("created_at")
  private String mCreateAt;
  @SerializedName("update_at")
  private String mUpdateAt;
  @SerializedName("seller_agent")
  private AgentDTO mAgent;
  @SerializedName("display_line_one")
  private String mDisplayOne;
  @SerializedName("display_line_two")
  private String mDisplayTwo;
  @SerializedName("display_line_three")
  private String mDisplayThree;
  @SerializedName("display_line_four")
  private String mDisplayFour;

  public Long getmId() {
    return mId;
  }

  public void setmId(Long mId) {
    this.mId = mId;
  }

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

  public String getmReferenceNumber() {
    return mReferenceNumber;
  }

  public void setmReferenceNumber(String mReferenceNumber) {
    this.mReferenceNumber = mReferenceNumber;
  }

  public PropertyStatus getmStatus() {
    return mStatus;
  }

  public void setmStatus(PropertyStatus mStatus) {
    this.mStatus = mStatus;
  }

  public List<MemberDTO> getPropertySeller() {
    return mPropertySeller;
  }

  public void setPropertySeller(List<MemberDTO> mPropertySeller) {
    this.mPropertySeller = mPropertySeller;
  }

  public FileDTO getmFeatrureImage() {
    return mFeatrureImage;
  }

  public void setmFeatrureImage(FileDTO mFeatrureImage) {
    this.mFeatrureImage = mFeatrureImage;
  }

  public ArrayList<FileDTO> getmGallery() {
    return mGallery;
  }

  public void setmGallery(ArrayList<FileDTO> mGallery) {
    this.mGallery = mGallery;
  }

  public String getmCreateAt() {
    return mCreateAt;
  }

  public void setmCreateAt(String mCreateAt) {
    this.mCreateAt = mCreateAt;
  }

  public String getmUpdateAt() {
    return mUpdateAt;
  }

  public void setmUpdateAt(String mUpdateAt) {
    this.mUpdateAt = mUpdateAt;
  }

  public AgentDTO getmAgent() {
    return mAgent;
  }

  public void setmAgent(AgentDTO mAgent) {
    this.mAgent = mAgent;
  }

  public String getmDisplayOne() {
    return mDisplayOne;
  }

  public void setmDisplayOne(String mDisplayOne) {
    this.mDisplayOne = mDisplayOne;
  }

  public String getmDisplayTwo() {
    return mDisplayTwo;
  }

  public void setmDisplayTwo(String mDisplayTwo) {
    this.mDisplayTwo = mDisplayTwo;
  }

  public String getmDisplayThree() {
    return mDisplayThree;
  }

  public void setmDisplayThree(String mDisplayThree) {
    this.mDisplayThree = mDisplayThree;
  }

  public String getmDisplayFour() {
    return mDisplayFour;
  }

  public void setmDisplayFour(String mDisplayFour) {
    this.mDisplayFour = mDisplayFour;
  }
}
