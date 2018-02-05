package app.positiveculture.com.user.screen.usereditprofile;

import android.net.Uri;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.Address;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The UserEditProfile Contract
 */
interface UserEditProfileContract {

  interface Interactor extends IInteractor<Presenter> {

    void getProfile(CommonCallback<MemberDTO> commonCallback);

    void saveEditProfile(MemberDTO memberDTO, CommonCallback<User> commonCallback);

    void uploadUserAvatar(Uri mSelectedUri, String mRealFrontImageFilePath, CommonCallback<FileDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void createProfile(MemberDTO memberDTO);

    void setNric(MemberDTO nric);

    void bindPersonalAddr(Address address);

    void bindCompanyAddr(Address address);

    void bindAvatar(FileDTO data);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void searchAddr(String type);

    void showNric();

    void saveProfile(MemberDTO memberDTO);

    void uploadUserAvatar(Uri mSelectedUri, String mRealFrontImageFilePath);

    void goToBankDetailScreen();
  }
}



