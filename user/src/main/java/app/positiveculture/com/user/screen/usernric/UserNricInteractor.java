package app.positiveculture.com.user.screen.usernric;

import android.net.Uri;

import com.gemvietnam.base.viper.Interactor;
import com.gemvietnam.utils.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import app.positiveculture.com.agent.utils.MediaUtils;
import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.User;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * The UserNric interactor
 */
class UserNricInteractor extends Interactor<UserNricContract.Presenter>
        implements UserNricContract.Interactor {

  UserNricInteractor(UserNricContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void saveNricProfile(String nric, String idType, long idFront, long idBack, CommonCallback<User> commonCallback) {
    ServiceBuilder.getInstance().getService().saveIdUser(nric, idType, idFront, idBack).enqueue(commonCallback);
  }

  @Override
  public void uploadNricImage(Uri mSelectedUri, String mRealImageFilePath, CommonCallback<FileDTO> commonCallback) {
    MultipartBody.Part mPart;
    RequestBody body;
    String fileExt = "";
    String miniType;
    if (!StringUtils.isEmpty(mRealImageFilePath)) {
      fileExt = MediaUtils.getFileExtension(mRealImageFilePath);
    }
    if ("jpg".equalsIgnoreCase(fileExt) || "jpeg".equalsIgnoreCase(fileExt)) {
      miniType = "image/jpeg";
    } else {
      miniType = "image/png";
    }

    body = RequestBody.create(MediaType.parse(miniType), new File(mRealImageFilePath));
    mPart = MultipartBody.Part.createFormData("file", "nric." + fileExt, body);

    ServiceBuilder.getInstance().getService().imageUpload(mPart).enqueue(commonCallback);
  }
}
