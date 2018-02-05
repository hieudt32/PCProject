package app.positiveculture.com.agent.screen.agentprofile.editprofile;

import android.net.Uri;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.agent.ObjectType;
import app.positiveculture.com.agent.dto.BankDetail;
import app.positiveculture.com.agent.screen.agentprofile.AgentProfilePresenter;
import app.positiveculture.com.agent.screen.bankdetails.BankDetailsPresenterAgent;
import app.positiveculture.com.agent.screen.nric.NricPresenterAgent;
import app.positiveculture.com.agent.screen.nricupload.NricUploadPresenterAgent;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * The EditProfile Presenter
 */
public class EditProfilePresenter extends Presenter<EditProfileContract.View, EditProfileContract.Interactor>
        implements EditProfileContract.Presenter, BankDetailsPresenterAgent.OnSaveBankDetails, NricPresenterAgent.OnSaveNric
        , NricUploadPresenterAgent.OnSaveNricUpload {

  private AgentDTO mAgentDTO;
  private FileDTO mAvatar;
  private OnEditProfileAgentListener mOnEditProfileAgentListener;

  public EditProfilePresenter setmOnEditProfileAgentListener(OnEditProfileAgentListener mOnEditProfileAgentListener) {
    this.mOnEditProfileAgentListener = mOnEditProfileAgentListener;
    return this;
  }

  public EditProfilePresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public EditProfileContract.View onCreateView() {
    return EditProfileFragment.getInstance();
  }

  @Override
  public void start() {
    if (mAgentDTO != null) {
      mView.setupData(mAgentDTO);
    }
  }

  public EditProfilePresenter setAgent(AgentDTO agent) {
    mAgentDTO = agent;
    return this;
  }

  @Override
  public EditProfileContract.Interactor onCreateInteractor() {
    return new EditProfileInteractor(this);
  }

  @Override
  public void goToBankDetails() {
    BankDetail bankDetail = new BankDetail();
    bankDetail.setBankName(mAgentDTO.getBankName());
    bankDetail.setBankAcountNo(mAgentDTO.getBankAccountNumber());
    bankDetail.setAcountType(mAgentDTO.getBankAccountType());
    new BankDetailsPresenterAgent(mContainerView)
            .setOnSaveBankDetails(this)
            .setBankDetail(bankDetail)
            .pushView();
  }

  @Override
  public void goToNric() {
    if (PrefWrapper.getSetting().getNeedUploadImage() == 1) {
      new NricUploadPresenterAgent(mContainerView)
              .setmOnSaveNricUpload(this)
              .setNric(mAgentDTO.getNric(), mAgentDTO.getNricFrontImage(), mAgentDTO.getNricBackImage())
              .pushView();
    } else {
      new NricPresenterAgent(mContainerView)
              .setOnSaveNric(this)
              .setNricNumber(mAgentDTO.getNric())
              .pushView();
    }
  }

  @Override
  public void saveEditProfile(String Email) {
    mView.showProgress();
    long idImage = -1;
    if (mAvatar != null) idImage = mAvatar.getId();
    mInteractor.saveEditProfile(idImage, Email, new CommonCallback<User>(getViewContext()) {
      @Override
      public void onSuccess(User data) {
        super.onSuccess(data);
        mView.hideProgress();
        if (data instanceof AgentDTO) {
          PrefWrapper.saveAgent((AgentDTO) data);
        }
        goBack();
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
        mView.hideProgress();
      }
    });
  }

  @Override
  public void goBack() {
    if (mOnEditProfileAgentListener != null) {
      mOnEditProfileAgentListener.onEditSuccessful();
    }
    mContainerView.back();
  }

  @Override
  public void uploadAvatar(Uri mSelectedUri, String mRealFrontImageFilePath) {
    mView.showProgress();
    mInteractor.uploadAvatar(mSelectedUri, mRealFrontImageFilePath, new CommonCallback<FileDTO>(getViewContext()) {
      @Override
      public void onSuccess(FileDTO data) {
        super.onSuccess(data);
        mAvatar = data;
        mView.showAvatar(data);
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public void onSaveBankDetailsClick(String bankName, String accountNumber, String accountType) {
    if (mOnEditProfileAgentListener != null) {
      mOnEditProfileAgentListener.onEditSuccessful();
    }
    mAgentDTO.setBankName(bankName);
    mAgentDTO.setBankAccountNumber(accountNumber);
    mAgentDTO.setBankAccountType(accountType);
    mView.updateBankDetails(bankName, accountNumber, accountType);
  }

  @Override
  public void onSaveNricClick(String nric) {
    mAgentDTO.setNric(nric);
    mView.updateNric(nric);
  }

  @Override
  public void onSaveNricUploadSuccessful(AgentDTO agentDTO) {
    this.mAgentDTO = agentDTO;
    mView.updateNric(agentDTO.getNric());
    if (mOnEditProfileAgentListener != null) {
      mOnEditProfileAgentListener.onEditSuccessful();
    }
  }

  public interface OnEditProfileAgentListener {
    void onEditSuccessful();
  }
}
