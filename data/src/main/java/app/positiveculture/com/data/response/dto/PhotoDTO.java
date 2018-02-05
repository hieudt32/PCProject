package app.positiveculture.com.data.response.dto;

import android.net.Uri;

/**
 * Photo model
 * Created by hungdn on 8/23/2017.
 */

public class PhotoDTO {
  private Uri mPhotoUrl;
  private String mRealImagePath;

  public Uri getPhotoUrl() {
    return mPhotoUrl;
  }

  public void setPhotoUrl(Uri photoUrl) {
    mPhotoUrl = photoUrl;
  }

  public String getRealImagePath() {
    return mRealImagePath;
  }

  public void setRealImagePath(String realImagePath) {
    mRealImagePath = realImagePath;
  }
}
