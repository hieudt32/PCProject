package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

import app.positiveculture.com.data.enumdata.MemberType;
import app.positiveculture.com.data.enumdata.SignType;
import app.positiveculture.com.data.enumdata.StatusSignOTP;

/**
 * SignOtpDTO
 * Created by hieudt on 11/29/2017.
 */

public class SignOtpDTO {
  @SerializedName("id")
  private long mIdSign;
  @SerializedName("signature_image")
  private FileDTO mSignatureImage;
  @SerializedName("status")
  private StatusSignOTP mStatus;
  @SerializedName("member_type")
  private MemberType mType;
  @SerializedName("sign_type")
  private SignType mSignType;
  @SerializedName("otp")
  private long mIdOTP;
  @SerializedName("member")
  private long mIdMember;
  @SerializedName("seller_agent")
  private long mIdSellerAgent;
  @SerializedName("buyer_agent")
  private long mIdBuyerAgent;

  public long getIdSign() {
    return mIdSign;
  }

  public void setIdSign(long mIdSign) {
    this.mIdSign = mIdSign;
  }

  public FileDTO getSignatureImage() {
    return mSignatureImage;
  }

  public void setSignatureImage(FileDTO mSignatureImage) {
    this.mSignatureImage = mSignatureImage;
  }

  public StatusSignOTP getStatus() {
    return mStatus;
  }

  public void setStatus(StatusSignOTP mStatus) {
    this.mStatus = mStatus;
  }

  public MemberType getType() {
    return mType;
  }

  public void setType(MemberType mType) {
    this.mType = mType;
  }

  public SignType getSignType() {
    return mSignType;
  }

  public void setSignType(SignType mSignType) {
    this.mSignType = mSignType;
  }

  public long getIdOTP() {
    return mIdOTP;
  }

  public void setIdOTP(long mIdOTP) {
    this.mIdOTP = mIdOTP;
  }

  public long getIdMember() {
    return mIdMember;
  }

  public void setIdMember(long mIdMember) {
    this.mIdMember = mIdMember;
  }

  public long getIdSellerAgent() {
    return mIdSellerAgent;
  }

  public void setIdSellerAgent(long mIdSellerAgent) {
    this.mIdSellerAgent = mIdSellerAgent;
  }

  public long getIdBuyerAgent() {
    return mIdBuyerAgent;
  }

  public void setIdBuyerAgent(long mIdBuyerAgent) {
    this.mIdBuyerAgent = mIdBuyerAgent;
  }
}
