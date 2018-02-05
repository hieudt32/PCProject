package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * ErrorDTO
 * Created by hungdn on 8/23/2017.
 */

public class ErrorDTO {

  @SerializedName("title")
  private String title;
  @SerializedName("message")
  private String errorMessage;

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
