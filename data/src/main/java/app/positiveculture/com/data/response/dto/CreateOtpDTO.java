package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

import app.positiveculture.com.data.enumdata.StatusAndOrNominee;
import app.positiveculture.com.data.enumdata.TypeFurnishing;
import app.positiveculture.com.data.enumdata.TypeTenancy;

/**
 * CreateOtpDTO
 * Created by hieudt on 11/1/2017.
 */

public class CreateOtpDTO {
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
  @SerializedName("buyer_agent")
  private long mBuyerAgentId;
  @SerializedName("property")
  private long mPropertyId;
  @SerializedName("solicitor")
  private int mSolicitorId;
  @SerializedName("solicitor_name")
  private String mSolicitorName;
  @SerializedName("solicitor_address")
  private String mSolicitorAddress;
  @SerializedName("commission_exact_amount")
  private int mCommissionExactAmount;
  @SerializedName("commission_percent")
  private int mCommissionPercent;
  @SerializedName("furnishing")
  private String mFurnishing;
  @SerializedName("tenancy")
  private String mTenancy;
  @SerializedName("and_or_nominee")
  private StatusAndOrNominee mAndOrNominee;

  public StatusAndOrNominee getAndOrNominee() {
    return mAndOrNominee;
  }

  public void setAndOrNominee(StatusAndOrNominee mAndOrNominee) {
    this.mAndOrNominee = mAndOrNominee;
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

  public long getBuyerAgentId() {
    return mBuyerAgentId;
  }

  public void setBuyerAgentId(long mBuyerAgentId) {
    this.mBuyerAgentId = mBuyerAgentId;
  }

  public long getPropertyId() {
    return mPropertyId;
  }

  public void setPropertyId(long mPropertyId) {
    this.mPropertyId = mPropertyId;
  }

  public int getSolicitorId() {
    return mSolicitorId;
  }

  public void setSolicitorId(int mSolicitorId) {
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

  public int getCommissionExactAmount() {
    return mCommissionExactAmount;
  }

  public void setCommissionExactAmount(int mCommissionExactAmount) {
    this.mCommissionExactAmount = mCommissionExactAmount;
  }

  public int getCommissionPercent() {
    return mCommissionPercent;
  }

  public void setCommissionPercent(int mCommissionPercent) {
    this.mCommissionPercent = mCommissionPercent;
  }

  public String getFurnishing() {
    return mFurnishing;
  }

  public void setFurnishing(String mFurnishing) {
    this.mFurnishing = mFurnishing;
  }

  public String getTenancy() {
    return mTenancy;
  }

  public void setTenancy(String mTenancy) {
    this.mTenancy = mTenancy;
  }
}
