package app.positiveculture.com.agent.screen.clientlist.existinguser;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.DialogUtils;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;

/**
 * The ExistingUser Presenter
 */
public class ExistingUserPresenter extends Presenter<ExistingUserContract.View, ExistingUserContract.Interactor>
        implements ExistingUserContract.Presenter {

  private MemberDTO mMemberDTO;
  private OnInvitationSentSuccessful mOnInvitationSentSuccessful;

  public ExistingUserPresenter setOnInvitationSentSuccessful(OnInvitationSentSuccessful mOnInvitationSentSuccessful) {
    this.mOnInvitationSentSuccessful = mOnInvitationSentSuccessful;
    return this;
  }

  public ExistingUserPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public ExistingUserContract.View onCreateView() {
    return ExistingUserFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
    if (mMemberDTO != null) {
      mView.setupData(mMemberDTO);
    }
  }

  @Override
  public ExistingUserContract.Interactor onCreateInteractor() {
    return new ExistingUserInteractor(this);
  }

  public ExistingUserPresenter setMemberDTO(MemberDTO mMemberDTO) {
    this.mMemberDTO = mMemberDTO;
    return this;
  }

  @Override
  public void backToClientList() {
    if(mOnInvitationSentSuccessful!=null){
      mOnInvitationSentSuccessful.onSentSuccess();
    }
    mContainerView.backTwiceTimes();
  }

  @Override
  public void addExistingClient(Long clientId) {
    DialogUtils.showProgressDialog(getViewContext());
    mInteractor.addExistingClient(clientId, new CommonCallback<AgentDTO>(getViewContext()) {
      @Override
      public void onSuccess(AgentDTO data) {
        super.onSuccess(data);
        if (data != null) {
          mView.showRequestSentDialog();
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
      }
    });
  }

  public interface OnInvitationSentSuccessful{
    void onSentSuccess();
  }
}
