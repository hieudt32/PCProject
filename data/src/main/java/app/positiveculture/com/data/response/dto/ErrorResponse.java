package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Error response
 * Created by neo on 8/2/17.
 */

public class ErrorResponse {
  @SerializedName("error")
  private String error;
  @SerializedName("error_description")
  private String errorDescription;

  public String getError() {
    return error;
  }

  public ErrorResponse setError(String error) {
    this.error = error;
    return this;
  }

  public String getErrorDescription() {
    return errorDescription;
  }

  public ErrorResponse setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
    return this;
  }
}
