package app.positiveculture.com.user.screen.usereditprofile;

import android.net.Uri;
import android.widget.Toast;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.DialogUtils;

import app.positiveculture.com.agent.dto.BankDetail;
import app.positiveculture.com.agent.screen.bankdetails.BankDetailsPresenterAgent;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.Address;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.screen.searchaddress.SearchAddressPresenter;
import app.positiveculture.com.user.screen.userbankdetail.UserBankDetailPresenter;
import app.positiveculture.com.user.screen.usernric.UserNricPresenter;
import retrofit2.Call;

/**
 * The UserEditProfile Presenter
 */
public class UserEditProfilePresenter extends Presenter<UserEditProfileContract.View, UserEditProfileContract.Interactor>
        implements UserEditProfileContract.Presenter, UserNricPresenter.OnSaveNricListener, BankDetailsPresenterAgent.OnSaveBankDetails, UserBankDetailPresenter.OnSaveBankDetailListener {

  private OnSaveUserProfileListener onSaveUserProfileListener;
  private MemberDTO mMemberDTO;
  private FileDTO mFileDTO;

  public void setOnSaveUserProfileListener(OnSaveUserProfileListener onSaveUserProfileListener) {
    this.onSaveUserProfileListener = onSaveUserProfileListener;
  }

  public UserEditProfilePresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public UserEditProfileContract.View onCreateView() {
    return UserEditProfileFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
    getProfile();
  }

  private void getProfile() {
    DialogUtils.showProgressDialog(getViewContext());
    mInteractor.getProfile(new CommonCallback<MemberDTO>(getViewContext()) {

      @Override
      public void onSuccess(MemberDTO data) {
        super.onSuccess(data);
        mMemberDTO = data;
        mView.createProfile(data);
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
        mMemberDTO = PrefWrapper.getMember();
        mView.createProfile(PrefWrapper.getMember());
      }
    });
  }

  @Override
  public UserEditProfileContract.Interactor onCreateInteractor() {
    return new UserEditProfileInteractor(this);
  }

  @Override
  public void searchAddr(String type) {
    SearchAddressPresenter searchAddressPresenter = new SearchAddressPresenter(mContainerView);
    searchAddressPresenter.setOnClickItemLocationListener(new SearchAddressPresenter.OnClickItemLocationListener() {
      @Override
      public void onLocationItemClick(String type, Address address) {
        if (type.equals(UserEditProfileFragment.PERSONAL)) {
          mView.bindPersonalAddr(address);
        } else {
          mView.bindCompanyAddr(address);
        }
      }
    });
    searchAddressPresenter.setType(type);
    searchAddressPresenter.pushView();
  }

  @Override
  public void showNric() {
    UserNricPresenter userNricPresenter = new UserNricPresenter(mContainerView);
    userNricPresenter.setNeedUpLoadImg(PrefWrapper.getSetting().getNeedUploadImage());
    userNricPresenter.setMemberDTO(mMemberDTO);
    userNricPresenter.setOnSaveNricListener(this);
    userNricPresenter.pushView();
  }

  @Override
  public void saveProfile(MemberDTO memberDTO) {
    DialogUtils.showProgressDialog(getViewContext());
    if (mFileDTO != null) memberDTO.setAvatar(mFileDTO);
    mInteractor.saveEditProfile(memberDTO, new CommonCallback<User>(getViewContext()) {
      @Override
      public void onSuccess(User data) {
        super.onSuccess(data);
        Toast.makeText(getViewContext(), getFragment().getString(R.string.change_profile_successfully), Toast.LENGTH_SHORT).show();
        PrefWrapper.saveMember((MemberDTO) data);
        onSaveUserProfileListener.onSaveSuccess();
      }
    });
  }

  @Override
  public void uploadUserAvatar(Uri mSelectedUri, String mRealFrontImageFilePath) {
    if (mSelectedUri == null) return;
    mView.showProgress();
    ViewUtils.rotateImage(mSelectedUri);
    mInteractor.uploadUserAvatar(mSelectedUri, mRealFrontImageFilePath, new CommonCallback<FileDTO>(getViewContext()) {
      @Override
      public void onSuccess(FileDTO data) {
        super.onSuccess(data);
        mFileDTO = data;
        mView.bindAvatar(data);
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public void goToBankDetailScreen() {
    BankDetail mBankDetail = new BankDetail();
    mBankDetail.setBankName(mMemberDTO.getBankName());
    mBankDetail.setAcountType(mMemberDTO.getBankAccountType());
    mBankDetail.setBankAcountNo(mMemberDTO.getBankAccountNumber());
    new UserBankDetailPresenter(mContainerView)
            .setOnSaveBankDetailListener(this)
            .setBankDetail(mBankDetail)
            .pushView();
  }

  @Override
  public void onSaveNric(MemberDTO memberDTO) {
    this.mMemberDTO = memberDTO;
    mView.setNric(memberDTO);
  }

  @Override
  public void onSaveBankDetailSuccessful() {
    start();
  }

  @Override
  public void onSaveBankDetailsClick(String bankName, String accountNumber, String accountType) {

  }


  public interface OnSaveUserProfileListener {
    void onSaveSuccess();
  }
}
