package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

import app.positiveculture.com.data.enumdata.StatusMemberVerifySMS;

/**
 * MemberVerifySmsDTO
 * Created by hieudt on 10/5/2017.
 */

public class MemberVerifySmsDTO {
  @SerializedName("id")
  private int mId;
  @SerializedName("member_id")
  private int mMemberId;
  @SerializedName("status")
  private StatusMemberVerifySMS mStatus;
  @SerializedName("created_at")
  private String mCreateAt;
  @SerializedName("updated_at")
  private String mUpdateAt;
}
