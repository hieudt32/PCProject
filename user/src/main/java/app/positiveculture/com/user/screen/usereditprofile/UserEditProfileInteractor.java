package app.positiveculture.com.user.screen.usereditprofile;

import android.net.Uri;

import com.gemvietnam.base.viper.Interactor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import app.positiveculture.com.agent.utils.MediaUtils;
import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.User;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * The UserEditProfile interactor
 */
class UserEditProfileInteractor extends Interactor<UserEditProfileContract.Presenter>
        implements UserEditProfileContract.Interactor {

  UserEditProfileInteractor(UserEditProfileContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getProfile(CommonCallback<MemberDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().getProfileMember().enqueue(commonCallback);
  }

  @Override
  public void saveEditProfile(MemberDTO memberDTO, CommonCallback<User> commonCallback) {
    ServiceBuilder.getInstance().getService().editProfileMember(
            memberDTO.getAvatar().getId(),
            memberDTO.getUsers().getFullName(),
            memberDTO.getUsers().getPhoneCountryCode(),
            memberDTO.getUsers().getPhoneNumber(),
            memberDTO.getmDateOfBirth(),
            memberDTO.getUsers().getEmail(),
            memberDTO.getmResidencyStatus(),
            memberDTO.getmAddrFloor(),
            memberDTO.getmAddrUnit(),
            memberDTO.getmAddrBuilding(),
            memberDTO.getmAddrStreetLineOne(),
            memberDTO.getmAddrStreetLineTwo(),
            memberDTO.getmAddrPostalCode(),
            memberDTO.getmAddrCountry(),
            memberDTO.getmCompName(),
            memberDTO.getmCompPhoneCountryCode(),
            memberDTO.getmCompPhoneNumber(),
            memberDTO.getmCompEmail(),
            memberDTO.getmCompAddrFloor(),
            memberDTO.getmComAddrUnit(),
            memberDTO.getmCompAddrBuilding(),
            memberDTO.getmCompAddrStreetLineOne(),
            memberDTO.getmCompAddrStreetLineTwo(),
            memberDTO.getmCompAddrPostalCode(),
            memberDTO.getmCompAddrCountry())
            .enqueue(commonCallback);
  }

  @Override
  public void uploadUserAvatar(Uri mSelectedUri, String mRealFrontImageFilePath, CommonCallback<FileDTO> commonCallback) {
    MultipartBody.Part mAvatar = null;
    String miniType;
    if (mSelectedUri != null) {
      String fileExt = MediaUtils.getFileExtension(mRealFrontImageFilePath);
      if ("jpg".compareTo(fileExt) == 0 || "jpeg".compareTo(fileExt) == 0) {
        miniType = "image/jpeg";
      } else miniType = "image/png";
      RequestBody requestBody = RequestBody.create(MediaType.parse(miniType), new File(mRealFrontImageFilePath));
      mAvatar = MultipartBody.Part.createFormData("file", "user_avatar." + fileExt, requestBody);
    }
    ServiceBuilder.getInstance().getService().imageUpload(mAvatar).enqueue(commonCallback);
  }
}
