package app.positiveculture.com.agent.dto;

/**
 * BankDetail
 * Created by hieudt on 11/8/2017.
 */

public class BankDetail {
  private String mBankName;
  private String mBankAcountNo;
  private String mAcountType;

  public String getBankName() {
    return mBankName;
  }

  public void setBankName(String mBankName) {
    this.mBankName = mBankName;
  }

  public String getBankAcountNo() {
    return mBankAcountNo;
  }

  public void setBankAcountNo(String mBankAcountNo) {
    this.mBankAcountNo = mBankAcountNo;
  }

  public String getAcountType() {
    return mAcountType;
  }

  public void setAcountType(String mAcountType) {
    this.mAcountType = mAcountType;
  }
}
