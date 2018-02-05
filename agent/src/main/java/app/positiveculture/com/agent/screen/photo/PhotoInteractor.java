package app.positiveculture.com.agent.screen.photo;

import android.graphics.Bitmap;
import android.net.Uri;

import com.gemvietnam.base.viper.Interactor;
import com.gemvietnam.utils.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import app.positiveculture.com.agent.utils.BitmapUtils;
import app.positiveculture.com.agent.utils.MediaUtils;
import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.PhotoDTO;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * The Photo interactor
 */
class PhotoInteractor extends Interactor<PhotoContract.Presenter>
        implements PhotoContract.Interactor {

  PhotoInteractor(PhotoContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void uploadGalleryImage(PhotoDTO photo, CommonCallback<FileDTO> commonCallback) {
    MultipartBody.Part mMultiBody = null;
    String fileExt = "";
    String mimeType;
    if (!StringUtils.isEmpty(photo.getRealImagePath())) {
      fileExt = MediaUtils.getFileExtension(photo.getRealImagePath());
    }

    if ("jpg".equalsIgnoreCase(fileExt) || "jpeg".equalsIgnoreCase(fileExt)) {
      mimeType = "image/jpeg";
    } else {
      mimeType = "image/png";
    }
    RequestBody fileBody = RequestBody.create(MediaType.parse(mimeType), new File(photo.getRealImagePath()));
    mMultiBody = MultipartBody.Part.createFormData("file", "gallery." + fileExt, fileBody);

    ServiceBuilder.getInstance().getService().imageUpload(mMultiBody).enqueue(commonCallback);
  }
}
