
package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

public abstract class User {

  @SerializedName("id")
  private long mId;
  @SerializedName("user")
  private Users mUsers;
  @SerializedName("status")
  private String mStatus;
  @SerializedName("token")
  private String mToken;
  @SerializedName("type")
  private String mType;
  @SerializedName("updated_at")
  private String mUpdatedAt;
  @SerializedName("first_time_login")
  private int isFirstTimeLogin;
  @SerializedName("push_notification")
  private int mPushNotification;
  @SerializedName("sms_notification")
  private int mSmsNotification;
  @SerializedName("bank_name")
  private String mBankName;
  @SerializedName("bank_account_number")
  private String mBankAccountNumber;
  @SerializedName("bank_account_type")
  private String mBankAccountType;
  @SerializedName("nric_front_image")
  private FileDTO mNricFrontImage;
  @SerializedName("nric_back_image")
  private FileDTO mNricBackImage;
  @SerializedName("avatar")
  private FileDTO mAvatar;

  public long getId() {
    return mId;
  }

  public void setId(long mId) {
    this.mId = mId;
  }

  public Users getUsers() {
    return mUsers;
  }

  public void setUsers(Users mUsers) {
    this.mUsers = mUsers;
  }

  public int getPushNotification() {
    return mPushNotification;
  }

  public void setPushNotification(int pushNotification) {
    mPushNotification = pushNotification;
  }

  public int getSmsNotification() {
    return mSmsNotification;
  }

  public void setSmsNotification(int smsNotification) {
    mSmsNotification = smsNotification;
  }

  public String getBankName() {
    return mBankName;
  }

  public void setBankName(String bankName) {
    mBankName = bankName;
  }

  public String getBankAccountNumber() {
    return mBankAccountNumber;
  }

  public void setBankAccountNumber(String bankAccountNumber) {
    mBankAccountNumber = bankAccountNumber;
  }

  public String getBankAccountType() {
    return mBankAccountType;
  }

  public void setBankAccountType(String bankAccountType) {
    mBankAccountType = bankAccountType;
  }

  public int getIsFirstTimeLogin() {
    return isFirstTimeLogin;
  }

  public void setIsFirstTimeLogin(int isFirstTimeLogin) {
    this.isFirstTimeLogin = isFirstTimeLogin;
  }

  public String getStatus() {
    return mStatus;
  }

  public void setStatus(String status) {
    mStatus = status;
  }

  public String getToken() {
    return mToken;
  }

  public void setToken(String token) {
    mToken = token;
  }

  public String getType() {
    return mType;
  }

  public void setType(String type) {
    mType = type;
  }

  public String getUpdatedAt() {
    return mUpdatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    mUpdatedAt = updatedAt;
  }

  public FileDTO getNricFrontImage() {
    return mNricFrontImage;
  }

  public void setNricFrontImage(FileDTO mNricFrontImage) {
    this.mNricFrontImage = mNricFrontImage;
  }

  public FileDTO getNricBackImage() {
    return mNricBackImage;
  }

  public void setNricBackImage(FileDTO mNricBackImage) {
    this.mNricBackImage = mNricBackImage;
  }

  public FileDTO getAvatar() {
    return mAvatar;
  }

  public void setAvatar(FileDTO mAvatar) {
    this.mAvatar = mAvatar;
  }
}
