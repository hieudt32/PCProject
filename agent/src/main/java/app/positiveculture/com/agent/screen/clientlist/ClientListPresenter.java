package app.positiveculture.com.agent.screen.clientlist;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.StringUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.Constant;
import app.positiveculture.com.agent.event.ReloadClientListEvent;
import app.positiveculture.com.agent.screen.clientlist.clientprofile.ClientProfilePresenter;
import app.positiveculture.com.agent.screen.clientlist.createnewclient.CreateNewClientPresenter;
import app.positiveculture.com.agent.screen.clientlist.existinguser.ExistingUserPresenter;
import app.positiveculture.com.agent.screen.clientlist.newuser.NewUserPresenter;
import app.positiveculture.com.agent.screen.verifyaccount.VerifyAccountPresenter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;

/**
 * The ClientList Presenter
 */
public class ClientListPresenter extends Presenter<ClientListContract.View, ClientListContract.Interactor>
        implements ClientListContract.Presenter, NewUserPresenter.OnBackToClientListListener, ExistingUserPresenter.OnInvitationSentSuccessful, ClientProfilePresenter.OnNoteClientClientProfileListener {

  private ClientListAdapter mAdapter;
  private List<MemberDTO> mListMemberDTO = new ArrayList<>();
  private final int mLimit = Constant.PAGE_LIMIT;
  private int mOffSet = 0;
  private boolean mAllClient = false;
  private AgentDTO mAgent;

  public ClientListPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public ClientListContract.View onCreateView() {
    return ClientListFragment.getInstance();
  }

  @Override
  public void start() {
    mOffSet = 0;
    mAllClient = false;
    mListMemberDTO.clear();
    getListClient(mLimit, mOffSet, null);
  }

  private void getListClient(int limit, int offset, String nameSearch) {
    mInteractor.getListClient(limit, offset, nameSearch, new CommonCallback<List<MemberDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<MemberDTO> data) {
        super.onSuccess(data);
        if (data.isEmpty()) {
          mView.showNoClient();
          mAllClient = true;
        } else {
          mListMemberDTO.clear();
          mListMemberDTO = data;
          mAdapter = new ClientListAdapter(mListMemberDTO);
          mAdapter.notifyDataSetChanged();
          mView.bindData(mAdapter);
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO errorMessage) {
        super.onError(errorCode, errorMessage);
      }
    });
  }

  @Override
  public ClientListContract.Interactor onCreateInteractor() {
    return new ClientListInteractor(this);
  }

  @Override
  public void goToCreateNewClient() {
    mView.showProgress();
    mInteractor.getProfileAgent(new CommonCallback<AgentDTO>(getViewContext()) {
      @Override
      public void onSuccess(AgentDTO data) {
        super.onSuccess(data);
        mAgent = data;
        PrefWrapper.saveAgent(data);
        if (mAgent != null) {
          if (mAgent.getStatus() != null) {
            if (!mAgent.getStatus().equals("unverified") && !StringUtils.isEmpty(mAgent.getNric())) {
              new CreateNewClientPresenter(mContainerView)
                      .setClientListPresenter(ClientListPresenter.this)
                      .pushView();
            } else {
              new VerifyAccountPresenter(mContainerView).presentView();
            }
          }
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public void goToClientProfile(MemberDTO memberDTO) {
    new ClientProfilePresenter(mContainerView)
            .setClient(memberDTO)
            .setOnNoteClientClientProfileListener(this)
            .pushView();
  }

  @Override
  public void loadMoreClient(String nameSearch) {
    if (mAllClient) {
      mView.loadMoreSuccess();
      return;
    }
    mOffSet += mLimit;
    mInteractor.getListClient(mLimit, mOffSet, nameSearch, new CommonCallback<List<MemberDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<MemberDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          int startIndex = mListMemberDTO.size();
          mListMemberDTO.addAll(data);
          mAdapter.notifyItemRangeInserted(startIndex, mListMemberDTO.size());
          mView.loadMoreSuccess();
        } else mView.loadMoreSuccess();
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public void refreshList(String nameSearch) {
    mOffSet = 0;
    mAllClient = false;
    getListClient(mLimit, mOffSet, nameSearch);
  }

  @Override
  public void searchName(String name) {
    mOffSet = 0;
    mAllClient = false;
    getListClient(mLimit, mOffSet, name);
  }

  @Override
  public void onBackToClientList() {
    start();
  }

  @Override
  public void onSentSuccess() {
    start();
  }

  @Subscribe
  public void onReloadClientListEvent(ReloadClientListEvent event) {
    mView.reloadClientList();
    start();
  }

  @Override
  public void onNoteEdited() {
    mView.reloadClientList();
    start();
  }
}
