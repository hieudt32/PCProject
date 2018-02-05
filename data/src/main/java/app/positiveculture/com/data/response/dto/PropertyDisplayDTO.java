package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hieudt on 12/1/2017.
 */

public class PropertyDisplayDTO {
  @SerializedName("display_line_one")
  private String mDisplayLineOne;
  @SerializedName("display_line_two")
  private String mDisplayLineTwo;
  @SerializedName("display_line_three")
  private String mDisplayLineThree;
  @SerializedName("display_line_four")
  private String mDisplayLineFour;

  public String getDisplayLineOne() {
    return mDisplayLineOne;
  }

  public void setDisplayLineOne(String mDisplayLineOne) {
    this.mDisplayLineOne = mDisplayLineOne;
  }

  public String getDisplayLineTwo() {
    return mDisplayLineTwo;
  }

  public void setDisplayLineTwo(String mDisplayLineTwo) {
    this.mDisplayLineTwo = mDisplayLineTwo;
  }

  public String getDisplayLineThree() {
    return mDisplayLineThree;
  }

  public void setDisplayLineThree(String mDisplayLineThree) {
    this.mDisplayLineThree = mDisplayLineThree;
  }

  public String getDisplayLineFour() {
    return mDisplayLineFour;
  }

  public void setDisplayLineFour(String mDisplayLineFour) {
    this.mDisplayLineFour = mDisplayLineFour;
  }
}
