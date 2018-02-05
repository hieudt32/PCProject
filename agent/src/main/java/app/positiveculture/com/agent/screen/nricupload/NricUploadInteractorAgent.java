package app.positiveculture.com.agent.screen.nricupload;

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
import app.positiveculture.com.data.response.dto.PhotoDTO;
import app.positiveculture.com.data.response.dto.User;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * The NricUpload interactor
 */
class NricUploadInteractorAgent extends Interactor<NricUploadContractAgent.Presenter>
        implements NricUploadContractAgent.Interactor {

  NricUploadInteractorAgent(NricUploadContractAgent.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void saveNricProfile(String nric, long idFrontImage, long idBackImage, CommonCallback<User> commonCallback) {
    ServiceBuilder.getInstance().getService().saveNricProfile(nric, idFrontImage, idBackImage).enqueue(commonCallback);
  }

  @Override
  public void uploadNricImage(PhotoDTO mPhoto, CommonCallback<FileDTO> commonCallback) {

    MultipartBody.Part mMultiBody = null;
    String fileExt = "";
    String mimeType;
    if (!StringUtils.isEmpty(mPhoto.getRealImagePath())) {
      fileExt = MediaUtils.getFileExtension(mPhoto.getRealImagePath());
    }

    if ("jpg".equalsIgnoreCase(fileExt) || "jpeg".equalsIgnoreCase(fileExt)) {
      mimeType = "image/jpeg";
    } else {
      mimeType = "image/png";
    }

    RequestBody fileBody = RequestBody.create(MediaType.parse(mimeType), new File(mPhoto.getRealImagePath()));
    mMultiBody = MultipartBody.Part.createFormData("file", "nric." + fileExt, fileBody);
//    Map<String, RequestBody> map = new HashMap<>();
//    map.put("file", fileBody);
    ServiceBuilder.getInstance().getService().imageUpload(mMultiBody).enqueue(commonCallback);
  }
}
