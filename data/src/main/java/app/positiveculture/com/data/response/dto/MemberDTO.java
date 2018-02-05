package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

import app.positiveculture.com.data.enumdata.MemberType;
import app.positiveculture.com.data.enumdata.ResidencyStatus;
import app.positiveculture.com.data.enumdata.TypeID;

/**
 * MemberDTO
 * Created by hungdn on 8/31/2017.
 */

public class MemberDTO extends User {
  @SerializedName("id_number")
  private String mIdNumber;
  @SerializedName("id_type")
  private TypeID mIdType;
  @SerializedName("sell_properties_count")
  private int mPropertiesCount;
  @SerializedName("login_agent_member_contact_status")
  private String mContractStatus;
  @SerializedName("already_in_contact")
  private boolean mAlreadyInContact;
  @SerializedName("phone_country_code")
  private String mPhoneCountryCode;
  @SerializedName("date_of_birth")
  private String mDateOfBirth;
  @SerializedName("residency_status")
  private ResidencyStatus mResidencyStatus;
  @SerializedName("address_floor")
  private String mAddrFloor;
  @SerializedName("address_unit")
  private String mAddrUnit;
  @SerializedName("address_building")
  private String mAddrBuilding;
  @SerializedName("address_street_line_one")
  private String mAddrStreetLineOne;
  @SerializedName("address_street_line_two")
  private String mAddrStreetLineTwo;
  @SerializedName("address_postal_code")
  private String mAddrPostalCode;
  @SerializedName("address_country")
  private String mAddrCountry;
  @SerializedName("company_name")
  private String mCompName;
  @SerializedName("company_phone_country_code")
  private String mCompPhoneCountryCode;
  @SerializedName("company_phone_number")
  private String mCompPhoneNumber;
  @SerializedName("company_email")
  private String mCompEmail;
  @SerializedName("company_address_floor")
  private String mCompAddrFloor;
  @SerializedName("company_address_unit")
  private String mComAddrUnit;
  @SerializedName("company_address_building")
  private String mCompAddrBuilding;
  @SerializedName("company_address_street_line_one")
  private String mCompAddrStreetLineOne;
  @SerializedName("company_address_street_line_two")
  private String mCompAddrStreetLineTwo;
  @SerializedName("company_address_postal_code")
  private String mCompAddrPostalCode;
  @SerializedName("company_address_country")
  private String mCompAddrCountry;
  @SerializedName("note")
  private String mNote;
  private transient MemberType mMemberType;
  private transient boolean isSelected;

  public MemberType getTypeInOTP() {
    return mMemberType;
  }

  public void setTypeInOTP(MemberType mMemberType) {
    this.mMemberType = mMemberType;
  }


  public String getmPhoneCountryCode() {
    return mPhoneCountryCode;
  }

  public void setmPhoneCountryCode(String mPhoneCountryCode) {
    this.mPhoneCountryCode = mPhoneCountryCode;
  }

  public String getmCompPhoneCountryCode() {
    return mCompPhoneCountryCode;
  }

  public void setmCompPhoneCountryCode(String mCompPhoneCountryCode) {
    this.mCompPhoneCountryCode = mCompPhoneCountryCode;
  }

  public void setmDateOfBirth(String mDateOfBirth) {
    this.mDateOfBirth = mDateOfBirth;
  }

  public void setmResidencyStatus(ResidencyStatus mResidencyStatus) {
    this.mResidencyStatus = mResidencyStatus;
  }

  public void setmAddrFloor(String mAddrFloor) {
    this.mAddrFloor = mAddrFloor;
  }

  public void setmAddrUnit(String mAddrUnit) {
    this.mAddrUnit = mAddrUnit;
  }

  public void setmAddrBuilding(String mAddrBuilding) {
    this.mAddrBuilding = mAddrBuilding;
  }

  public void setmAddrStreetLineOne(String mAddrStreetLineOne) {
    this.mAddrStreetLineOne = mAddrStreetLineOne;
  }

  public void setmAddrStreetLineTwo(String mAddrStreetLineTwo) {
    this.mAddrStreetLineTwo = mAddrStreetLineTwo;
  }

  public void setmAddrPostalCode(String mAddrPostalCode) {
    this.mAddrPostalCode = mAddrPostalCode;
  }

  public void setmAddrCountry(String mAddrCountry) {
    this.mAddrCountry = mAddrCountry;
  }

  public void setmCompName(String mCompName) {
    this.mCompName = mCompName;
  }


  public void setmCompPhoneNumber(String mCompPhoneNumber) {
    this.mCompPhoneNumber = mCompPhoneNumber;
  }

  public void setmCompEmail(String mCompEmail) {
    this.mCompEmail = mCompEmail;
  }

  public void setmCompAddrFloor(String mCompAddrFloor) {
    this.mCompAddrFloor = mCompAddrFloor;
  }

  public void setmComAddrUnit(String mComAddrUnit) {
    this.mComAddrUnit = mComAddrUnit;
  }

  public void setmCompAddrBuilding(String mCompAddrBuilding) {
    this.mCompAddrBuilding = mCompAddrBuilding;
  }

  public void setmCompAddrStreetLineOne(String mCompAddrStreetLineOne) {
    this.mCompAddrStreetLineOne = mCompAddrStreetLineOne;
  }

  public void setmCompAddrStreetLineTwo(String mCompAddrStreetLineTwo) {
    this.mCompAddrStreetLineTwo = mCompAddrStreetLineTwo;
  }

  public void setmCompAddrPostalCode(String mCompAddrPostalCode) {
    this.mCompAddrPostalCode = mCompAddrPostalCode;
  }

  public void setmCompAddrCountry(String mCompAddrCountry) {
    this.mCompAddrCountry = mCompAddrCountry;
  }

  public String getmDateOfBirth() {
    return mDateOfBirth;
  }

  public ResidencyStatus getmResidencyStatus() {
    return mResidencyStatus;
  }

  public String getmAddrFloor() {
    return mAddrFloor;
  }

  public String getmAddrUnit() {
    return mAddrUnit;
  }

  public String getmAddrBuilding() {
    return mAddrBuilding;
  }

  public String getmAddrStreetLineOne() {
    return mAddrStreetLineOne;
  }

  public String getmAddrStreetLineTwo() {
    return mAddrStreetLineTwo;
  }

  public String getmAddrPostalCode() {
    return mAddrPostalCode;
  }

  public String getmAddrCountry() {
    return mAddrCountry;
  }

  public String getmCompName() {
    return mCompName;
  }

  public String getmCompPhoneNumber() {
    return mCompPhoneNumber;
  }

  public String getmCompEmail() {
    return mCompEmail;
  }

  public String getmCompAddrFloor() {
    return mCompAddrFloor;
  }

  public String getmComAddrUnit() {
    return mComAddrUnit;
  }

  public String getmCompAddrBuilding() {
    return mCompAddrBuilding;
  }

  public String getmCompAddrStreetLineOne() {
    return mCompAddrStreetLineOne;
  }

  public String getmCompAddrStreetLineTwo() {
    return mCompAddrStreetLineTwo;
  }

  public String getmCompAddrPostalCode() {
    return mCompAddrPostalCode;
  }

  public String getmCompAddrCountry() {
    return mCompAddrCountry;
  }

  public MemberDTO() {
  }

  public MemberDTO(String name) {
    getUsers().setFullName(name);
  }

  public String getmIdNumber() {
    return mIdNumber;
  }

  public void setmIdNumber(String mIdNumber) {
    this.mIdNumber = mIdNumber;
  }

  public TypeID getIdType() {
    return mIdType;
  }

  public void setIdType(TypeID mIdType) {
    this.mIdType = mIdType;
  }

  public String getContractStatus() {
    return mContractStatus;
  }

  public void setmContractStatus(String mContractStatus) {
    this.mContractStatus = mContractStatus;
  }

  public boolean ismAlreadyInContact() {
    return mAlreadyInContact;
  }

  public void setmAlreadyInContact(boolean mAlreadyInContact) {
    this.mAlreadyInContact = mAlreadyInContact;
  }

  public int getProperties() {
    return mPropertiesCount;
  }

  public void setProperties(int mProperties) {
    this.mPropertiesCount = mProperties;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public void setSelected(boolean selected) {
    isSelected = selected;
  }

  public String getNote() {
    return mNote;
  }

  public void setNote(String mNote) {
    this.mNote = mNote;
  }
}
