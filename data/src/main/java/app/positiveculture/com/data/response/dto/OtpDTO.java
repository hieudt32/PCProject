
package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import app.positiveculture.com.data.enumdata.OTPStatus;
import app.positiveculture.com.data.enumdata.StatusAndOrNominee;
import app.positiveculture.com.data.enumdata.TypeFurnishing;
import app.positiveculture.com.data.enumdata.TypeTenancy;

public class OtpDTO {

  @SerializedName("id")
  private long mId;
  @SerializedName("reference_number")
  private String mReferenceNumber;
  @SerializedName("running_number")
  private int mRunningNumber;
  @SerializedName("hdb_serial_number")
  private String mHdbSerialNumber;
  @SerializedName("hdb_otp_timestamp")
  private String mHdbOtpTimestamp;
  @SerializedName("hdb_appointment_days")
  private int mHdbAppointmentDays;
  @SerializedName("otp_start_date")
  private String mOtpStartDate;
  @SerializedName("week_to_expiry")
  private int mWeekToExpiry;
  @SerializedName("otp_date_expiry")
  private String mOtpDateExpiry;
  @SerializedName("week_to_completion")
  private int mWeekToCompletion;
  @SerializedName("otp_date_completion")
  private String mOtpDateCompletion;
  @SerializedName("total_price")
  private double mTotalPrice;
  @SerializedName("option_fee")
  private double mOptionFee;
  @SerializedName("exercise_fee")
  private double mExerciseFee;
  @SerializedName("seller_agent")
  private AgentDTO mSellerAgent;
  @SerializedName("seller_agent_id")
  private long mSellerAgentId;
  @SerializedName("buyer_agent")
  private AgentDTO mBuyerAgent;
  @SerializedName("buyer_agent_id")
  private long mBuyerAgentId;
  @SerializedName("property")
  private PropertyDTO mProperty;
  @SerializedName("property_id")
  private long mPropertyId;
  @SerializedName("status")
  private OTPStatus mStatus;
  @SerializedName("active_status")
  private String mActiveStatus;
  @SerializedName("solicitor")
  private SolicitorDTO mSolicitorOrigin;
  @SerializedName("solicitor_id")
  private long mSolicitorId;
  @SerializedName("solicitor_name")
  private String mSolicitorName;
  @SerializedName("solicitor_address")
  private String mSolicitorAddress;
  @SerializedName("commission_exact_amount")
  private float mCommissionExactAmount;
  @SerializedName("commission_percent")
  private int mCommissionPercent;
  @SerializedName("furnishing")
  private TypeFurnishing mFurnishing;
  @SerializedName("tenancy")
  private TypeTenancy mTenancy;
  @SerializedName("created_at")
  private String mCreatedAt;
  @SerializedName("updated_at")
  private String mUpdatedAt;
  @SerializedName("and_or_nominee")
  private StatusAndOrNominee mAndOrNominee;
  @SerializedName("property_buyer")
  private List<MemberDTO> mPropertyBuyer;
  @SerializedName("otpsign_otp")
  private List<SignOtpDTO> mListSign;

  public StatusAndOrNominee getAndOrNominee() {
    return mAndOrNominee;
  }

  public void setAndOrNominee(StatusAndOrNominee mAndOrNominee) {
    this.mAndOrNominee = mAndOrNominee;
  }

  public long getId() {
    return mId;
  }

  public void setId(long mId) {
    this.mId = mId;
  }

  public String getReferenceNumber() {
    return mReferenceNumber;
  }

  public void setReferenceNumber(String mReferenceNumber) {
    this.mReferenceNumber = mReferenceNumber;
  }

  public int getRunningNumber() {
    return mRunningNumber;
  }

  public void setRunningNumber(int mRunningNumber) {
    this.mRunningNumber = mRunningNumber;
  }

  public String getHdbSerialNumber() {
    return mHdbSerialNumber;
  }

  public void setHdbSerialNumber(String mHdbSerialNumber) {
    this.mHdbSerialNumber = mHdbSerialNumber;
  }

  public String getHdbOtpTimestamp() {
    return mHdbOtpTimestamp;
  }

  public void setHdbOtpTimestamp(String mHdbOtpTimestamp) {
    this.mHdbOtpTimestamp = mHdbOtpTimestamp;
  }

  public int getHdbAppointmentDays() {
    return mHdbAppointmentDays;
  }

  public void setHdbAppointmentDays(int mHdbAppointmentDays) {
    this.mHdbAppointmentDays = mHdbAppointmentDays;
  }

  public String getOtpStartDate() {
    return mOtpStartDate;
  }

  public void setOtpStartDate(String mOtpStartDate) {
    this.mOtpStartDate = mOtpStartDate;
  }

  public int getWeekToExpiry() {
    return mWeekToExpiry;
  }

  public void setWeekToExpiry(int mWeekToExpiry) {
    this.mWeekToExpiry = mWeekToExpiry;
  }

  public String getOtpDateExpiry() {
    return mOtpDateExpiry;
  }

  public void setOtpDateExpiry(String mOtpDateExpiry) {
    this.mOtpDateExpiry = mOtpDateExpiry;
  }

  public int getWeekToCompletion() {
    return mWeekToCompletion;
  }

  public void setWeekToCompletion(int mWeekToCompletion) {
    this.mWeekToCompletion = mWeekToCompletion;
  }

  public String getOtpDateCompletion() {
    return mOtpDateCompletion;
  }

  public void setOtpDateCompletion(String mOtpDateCompletion) {
    this.mOtpDateCompletion = mOtpDateCompletion;
  }

  public double getTotalPrice() {
    return mTotalPrice;
  }

  public void setTotalPrice(double mTotalPrice) {
    this.mTotalPrice = mTotalPrice;
  }

  public double getOptionFee() {
    return mOptionFee;
  }

  public void setOptionFee(double mOptionFee) {
    this.mOptionFee = mOptionFee;
  }

  public double getExerciseFee() {
    return mExerciseFee;
  }

  public void setExerciseFee(double mExerciseFee) {
    this.mExerciseFee = mExerciseFee;
  }

  public AgentDTO getSellerAgent() {
    return mSellerAgent;
  }

  public void setSellerAgent(AgentDTO mSellerAgent) {
    this.mSellerAgent = mSellerAgent;
  }

  public long getSellerAgentId() {
    return mSellerAgentId;
  }

  public void setSellerAgentId(long mSellerAgentId) {
    this.mSellerAgentId = mSellerAgentId;
  }

  public AgentDTO getBuyerAgent() {
    return mBuyerAgent;
  }

  public void setBuyerAgent(AgentDTO mBuyerAgent) {
    this.mBuyerAgent = mBuyerAgent;
  }

  public long getBuyerAgentId() {
    return mBuyerAgentId;
  }

  public void setBuyerAgentId(long mBuyerAgentId) {
    this.mBuyerAgentId = mBuyerAgentId;
  }

  public PropertyDTO getProperty() {
    return mProperty;
  }

  public void setProperty(PropertyDTO mProperty) {
    this.mProperty = mProperty;
  }

  public long getPropertyId() {
    return mPropertyId;
  }

  public void setPropertyId(long mPropertyId) {
    this.mPropertyId = mPropertyId;
  }

  public OTPStatus getStatus() {
    return mStatus;
  }

  public void setStatus(OTPStatus mStatus) {
    this.mStatus = mStatus;
  }

  public String getActiveStatus() {
    return mActiveStatus;
  }

  public void setActiveStatus(String mActiveStatus) {
    this.mActiveStatus = mActiveStatus;
  }

  public SolicitorDTO getSolicitorOrigin() {
    return mSolicitorOrigin;
  }

  public void setSolicitorOrigin(SolicitorDTO mSolicitorOrigin) {
    this.mSolicitorOrigin = mSolicitorOrigin;
  }

  public long getSolicitorId() {
    return mSolicitorId;
  }

  public void setSolicitorId(long mSolicitorId) {
    this.mSolicitorId = mSolicitorId;
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

  public float getCommissionExactAmount() {
    return mCommissionExactAmount;
  }

  public void setCommissionExactAmount(float mCommissionExactAmount) {
    this.mCommissionExactAmount = mCommissionExactAmount;
  }

  public int getCommissionPercent() {
    return mCommissionPercent;
  }

  public void setCommissionPercent(int mCommissionPercent) {
    this.mCommissionPercent = mCommissionPercent;
  }

  public TypeFurnishing getFurnishing() {
    return mFurnishing;
  }

  public void setFurnishing(TypeFurnishing mFurnishing) {
    this.mFurnishing = mFurnishing;
  }

  public TypeTenancy getTenancy() {
    return mTenancy;
  }

  public void setTenancy(TypeTenancy mTenancy) {
    this.mTenancy = mTenancy;
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

  public List<MemberDTO> getPropertyBuyer() {
    return mPropertyBuyer;
  }

  public void setPropertyBuyer(List<MemberDTO> mPropertyBuyer) {
    this.mPropertyBuyer = mPropertyBuyer;
  }

  public List<SignOtpDTO> getListSign() {
    return mListSign;
  }

  public void setListSign(List<SignOtpDTO> mListSign) {
    this.mListSign = mListSign;
  }
}
