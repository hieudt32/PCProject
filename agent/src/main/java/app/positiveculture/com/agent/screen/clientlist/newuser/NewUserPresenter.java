package app.positiveculture.com.agent.screen.clientlist.newuser;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.DialogUtils;

import app.positiveculture.com.agent.screen.clientlist.ClientListPresenter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeID;
import app.positiveculture.com.data.response.dto.CreateMemberDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;

/**
 * The NewUser Presenter
 */
public class NewUserPresenter extends Presenter<NewUserContract.View, NewUserContract.Interactor>
        implements NewUserContract.Presenter {

  private String mIdType;
  private String mIdNumber;
  private OnBackToClientListListener mOnBackToClientListListener;

  public NewUserPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public NewUserContract.View onCreateView() {
    return NewUserFragment.getInstance();
  }

  @Override
  public void start() {
    if (mIdType != null && mIdNumber != null) {
      mView.setupData(mIdType, mIdNumber);
    }
  }

  public NewUserPresenter setIdType(String idType) {
    mIdType = idType;
    return this;
  }

  public NewUserPresenter setIdNumber(String idNumber) {
    mIdNumber = idNumber;
    return this;
  }

  public NewUserPresenter setOnBackToClientListListener(OnBackToClientListListener onBackToClientListListener) {
    mOnBackToClientListListener = onBackToClientListListener;
    return this;
  }

  @Override
  public NewUserContract.Interactor onCreateInteractor() {
    return new NewUserInteractor(this);
  }

  @Override
  public void goToClientList() {
    mContainerView.backTwiceTimes();
    if (mOnBackToClientListListener != null) {
      mOnBackToClientListListener.onBackToClientList();
    }
  }

  @Override
  public void createNewClient(CreateMemberDTO createMemberDTO) {
    DialogUtils.showProgressDialog(getViewContext());
    mInteractor.createNewClient(createMemberDTO, new CommonCallback<MemberDTO>(getViewContext()) {
      @Override
      public void onSuccess(MemberDTO data) {
        super.onSuccess(data);
        if (data != null) {
          mView.showCreateSuccessDialog();
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
      }
    });
  }

  public interface OnBackToClientListListener {
    void onBackToClientList();
  }
}
