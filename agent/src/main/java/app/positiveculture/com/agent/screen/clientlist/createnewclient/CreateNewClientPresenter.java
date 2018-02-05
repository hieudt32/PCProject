package app.positiveculture.com.agent.screen.clientlist.createnewclient;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.DialogUtils;

import app.positiveculture.com.agent.screen.clientlist.ClientListPresenter;
import app.positiveculture.com.agent.screen.clientlist.existinguser.ExistingUserPresenter;
import app.positiveculture.com.agent.screen.clientlist.newuser.NewUserPresenter;
import app.positiveculture.com.agent.screen.selectowners.SelectOwnersPresenter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import dialog.CustomDialog;

/**
 * The CreateNewClient Presenter
 */
public class CreateNewClientPresenter extends Presenter<CreateNewClientContract.View, CreateNewClientContract.Interactor>
        implements CreateNewClientContract.Presenter {

  private ClientListPresenter mClientListPresenter;
  private SelectOwnersPresenter mSelectOwnersPresenter;

  public CreateNewClientPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public CreateNewClientContract.View onCreateView() {
    return CreateNewClientFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  public CreateNewClientPresenter setClientListPresenter(ClientListPresenter clientListPresenter) {
    mClientListPresenter = clientListPresenter;
    return this;
  }

  public CreateNewClientPresenter setSelectOwnersPresenter(SelectOwnersPresenter mSelectOwnersPresenter) {
    this.mSelectOwnersPresenter = mSelectOwnersPresenter;
    return this;
  }

  @Override
  public CreateNewClientContract.Interactor onCreateInteractor() {
    return new CreateNewClientInteractor(this);
  }

  @Override
  public void checkClient(final String idType, final String idNumber) {
    getView().getBaseActivity().hideKeyboard();
    mInteractor.checkClient(idType, idNumber, new CommonCallback<MemberDTO>(getViewContext()) {
      @Override
      public void onSuccess(MemberDTO data) {
        super.onSuccess(data);
        ExistingUserPresenter existingUserPresenter = new ExistingUserPresenter(mContainerView);
        existingUserPresenter.setMemberDTO(data);
        if (mClientListPresenter != null) {
          existingUserPresenter.setOnInvitationSentSuccessful(mClientListPresenter).pushView();
        } else {
          existingUserPresenter.setOnInvitationSentSuccessful(mSelectOwnersPresenter).pushView();
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        DialogUtils.dismissProgressDialog();
        if (errorCode.equals("ERROR_USER_NOT_FOUND")) {
          NewUserPresenter newUserPresenter = new NewUserPresenter(mContainerView);
          newUserPresenter.setIdType(idType).setIdNumber(idNumber);
          if (mClientListPresenter != null) {
            newUserPresenter.setOnBackToClientListListener(mClientListPresenter).pushView();
          } else if (mSelectOwnersPresenter != null) {
            newUserPresenter.setOnBackToClientListListener(mSelectOwnersPresenter).pushView();
          }
        } else if (errorCode.equals("ERROR_USER_IN_CONTACT")) {
          utils.DialogUtils.showDialog(getViewContext(), error.getTitle(), error.getErrorMessage(),
                  new CustomDialog.OnConfirmSelected() {
                    @Override
                    public void onConfirmSelected() {

                    }
                  });
        } else super.onError(errorCode, error);
      }
    });
  }

}
