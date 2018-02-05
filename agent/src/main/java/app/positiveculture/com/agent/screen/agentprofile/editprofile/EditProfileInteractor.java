package app.positiveculture.com.agent.screen.agentprofile.editprofile;

import android.net.Uri;

import com.gemvietnam.base.viper.Interactor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import app.positiveculture.com.agent.utils.BitmapUtils;
import app.positiveculture.com.agent.utils.MediaUtils;
import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.User;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * The EditProfile interactor
 */
class EditProfileInteractor extends Interactor<EditProfileContract.Presenter>
        implements EditProfileContract.Interactor {

  EditProfileInteractor(EditProfileContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void saveEditProfile(long idImage, String email, CommonCallback<User> commonCallback) {
    ServiceBuilder.getInstance().getService().saveSetupProfile(idImage, email, null, null, null).enqueue(commonCallback);
  }

  @Override
  public void uploadAvatar(Uri mSelectedUri, String mRealFrontImageFilePath, CommonCallback<FileDTO> commonCallback) {
    MultipartBody.Part mAvatar = null;
    String miniType;
    if (mSelectedUri != null) {
      String fileExt = MediaUtils.getFileExtension(mRealFrontImageFilePath);
      if ("jpg".compareTo(fileExt) == 0 || "jpeg".compareTo(fileExt) == 0) {
        miniType = "image/jpeg";
      } else miniType = "image/png";
      RequestBody requestBody = RequestBody.create(MediaType.parse(miniType), new File(mRealFrontImageFilePath));
      mAvatar = MultipartBody.Part.createFormData("file", "avatar." + fileExt, requestBody);
    }
    ServiceBuilder.getInstance().getService().imageUpload(mAvatar).enqueue(commonCallback);
  }
}
