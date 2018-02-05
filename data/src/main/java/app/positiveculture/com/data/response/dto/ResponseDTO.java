package app.positiveculture.com.data.response.dto;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Common Response DTO for all API requests
 * Created by NEO on 11/7/2016.
 */

public class ResponseDTO<T> {
  @SerializedName("message")
  private String message;
  @SerializedName("errors")
  private ErrorDTO errors;
  @SerializedName("data")
  private T data;
  @SerializedName("error")
  private Boolean error;
  @SerializedName("meta")

  /**
   *
   * @return
   * The message
   */
  public String getMessage() {
    return message;
  }

  /**
   *
   * @param message
   * The message
   */
  public void setMessage(String message) {
    this.message = message;
  }

  public ErrorDTO getErrors() {
    return errors;
  }

  public void setErrors(ErrorDTO errors) {
    this.errors = errors;
  }

  /**
   *
   * @return
   * The data
   */
  public T getData() {
    return data;
  }

  /**
   *
   * @param data
   * The data
   */
  public void setData(T data) {
    this.data = data;
  }

  /**
   *
   * @return
   * The error
   */
  public Boolean getError() {
    return error;
  }

  /**
   *
   * @param error
   * The error
   */
  public void setError(Boolean error) {
    this.error = error;
  }

}
