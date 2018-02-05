package app.positiveculture.com.user.screen.signotp;

import com.gemvietnam.base.viper.Interactor;

import java.io.File;

import app.positiveculture.com.agent.utils.MediaUtils;
import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * The SignOTP interactor
 */
class SignOTPInteractor extends Interactor<SignOTPContract.Presenter>
        implements SignOTPContract.Interactor {

  SignOTPInteractor(SignOTPContract.Presenter presenter) {
    super(presenter);
  }


  @Override
  public void uploadSignImage(String filePath, CommonCallback<FileDTO> commonCallback) {
    MultipartBody.Part mSignImage = null;
    String miniType;
    if (filePath != null) {
      String fileExt = MediaUtils.getFileExtension(filePath);
      if ("jpg".compareTo(fileExt) == 0 || "jpeg".compareTo(fileExt) == 0) {
        miniType = "image/jpeg";
      } else miniType = "image/png";
      RequestBody requestBody = RequestBody.create(MediaType.parse(miniType), new File(filePath));
      mSignImage = MultipartBody.Part.createFormData("file", "user_signature_image." + fileExt, requestBody);
    }
    ServiceBuilder.getInstance().getService().imageUpload(mSignImage).enqueue(commonCallback);
  }

  @Override
  public void signOTP(long idSignImage, long idOTP, CommonCallback<OtpDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().signOTP(String.valueOf(idOTP), idSignImage).enqueue(commonCallback);
  }
}
