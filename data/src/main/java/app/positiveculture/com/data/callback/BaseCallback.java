package app.positiveculture.com.data.callback;

import android.util.Log;

import com.gemvietnam.utils.DialogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Base API callback1
 * Created by NEO on 11/7/2016.
 */

public abstract class BaseCallback<T> implements Callback<ResponseDTO<T>> {

  public static final String NETWORK_ERROR = "9999";
  //  private Paging mPaging;
  private ResponseDTO<T> mBody;
  private ResponseBody mErrorBody;

  @Override
  public void onResponse(Call<ResponseDTO<T>> call, Response<ResponseDTO<T>> response) {
    // Get body of request
    mBody = null;
    mErrorBody = null;
    String responseCode = NETWORK_ERROR;
    String message = "";
    mErrorBody = response.errorBody();
    if (response.isSuccessful()) {
      mBody = response.body();
      Log.e("@@@@@", mBody.toString());
    } else {
      mErrorBody = response.errorBody();
      Log.e("@@@@@", mErrorBody.toString());
    }

    if (mBody == null && mErrorBody == null) {
      try {
        onError(NETWORK_ERROR, getServerMsg());
      } catch (IllegalStateException | NullPointerException ex) {
        ex.printStackTrace();
      }
      return;
    }

    if (mErrorBody != null && mBody == null) {
      try {
        ResponseDTO error = new Gson().fromJson(mErrorBody.string(), ResponseDTO.class);
        if (error.getMessage() == null || error.getErrors() == null) {
          onError(NETWORK_ERROR, getServerMsg());
          return;
        }
        onError(error.getMessage(), error.getErrors());
      } catch (IOException | IllegalStateException | JsonSyntaxException e) {
        e.printStackTrace();
        onError(NETWORK_ERROR, getServerMsg());
      }
      return;
    }

    // If not success
    if (mBody != null && mBody.getError()) {
      try {
        onError(mBody.getMessage(), mBody.getErrors());
      } catch (IllegalStateException | NullPointerException ex) {
        ex.printStackTrace();
        onError(NETWORK_ERROR, getServerMsg());
      }
      return;
    }

    // Request success
    try {
//      mPaging = mBody.getPaging();
      onSuccess(mBody.getData());
      DialogUtils.dismissProgressDialog();
    } catch (IllegalStateException | NullPointerException ex) {
      ex.printStackTrace();
      onError(NETWORK_ERROR, getServerMsg());
    }
  }

  public ResponseDTO<T> getBody() {
    return mBody;
  }

  @Override
  public void onFailure(Call<ResponseDTO<T>> call, Throwable t) {
    try {
      onError(NETWORK_ERROR, getServerMsg());
      DialogUtils.dismissProgressDialog();
    } catch (IllegalStateException | NullPointerException ex) {
      ex.printStackTrace();
    }
  }

  public abstract String getServerMsg();

  public abstract void onError(String errorCode, String errorMessage);

  public abstract void onError(String errorCode, ErrorDTO error);

  public abstract void onSuccess(T data);
}