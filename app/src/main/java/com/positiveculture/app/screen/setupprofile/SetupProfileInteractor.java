package com.positiveculture.app.screen.setupprofile;

import com.gemvietnam.base.viper.Interactor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import app.positiveculture.com.agent.utils.MediaUtils;
import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.PhotoDTO;
import app.positiveculture.com.data.response.dto.User;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * The SetupProfile interactor
 */
class SetupProfileInteractor extends Interactor<SetupProfileContract.Presenter>
        implements SetupProfileContract.Interactor {

  SetupProfileInteractor(SetupProfileContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void updateSetupProfile(long idAvatar, String name, String email, String countryCode, String phone, CommonCallback<User> commonCallback) {
    ServiceBuilder.getInstance().getService().saveSetupProfile(idAvatar, email, name, countryCode, phone).enqueue(commonCallback);
  }

  @Override
  public void uploadAvatar(PhotoDTO photo, CommonCallback<FileDTO> commonCallback) {
    MultipartBody.Part mAvatar = null;
    String miniType;
    if (photo.getRealImagePath() != null) {
      String fileExt = MediaUtils.getFileExtension(photo.getRealImagePath());
      if ("jpg".compareTo(fileExt) == 0 || "jpeg".compareTo(fileExt) == 0) {
        miniType = "image/jpeg";
      } else miniType = "image/png";
      RequestBody requestBody = RequestBody.create(MediaType.parse(miniType), new File(photo.getRealImagePath()));
      mAvatar = MultipartBody.Part.createFormData("file", "avatar." + fileExt, requestBody);
    }
    ServiceBuilder.getInstance().getService().imageUpload(mAvatar).enqueue(commonCallback);
  }
}
