package app.positiveculture.com.agent.screen.agentprofile.editprofile;

import android.net.Uri;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The EditProfile Contract
 */
interface EditProfileContract {

  interface Interactor extends IInteractor<Presenter> {
    void saveEditProfile(long idImage, String email, CommonCallback<User> commonCallback);

    void uploadAvatar(Uri mSelectedUri, String mRealFrontImageFilePath, CommonCallback<FileDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void setupData(AgentDTO agentDTO);

    void updateBankDetails(String bankName, String accountNumber, String accountType);

    void updateNric(String nric);

    void showAvatar(FileDTO data);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goToBankDetails();

    void goToNric();

    void saveEditProfile(String Email);

    void goBack();

    void uploadAvatar(Uri mSelectedUri, String mRealFrontImageFilePath);
  }
}



