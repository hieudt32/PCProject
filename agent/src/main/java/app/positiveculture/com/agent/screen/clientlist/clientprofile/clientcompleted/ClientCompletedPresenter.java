package app.positiveculture.com.agent.screen.clientlist.clientprofile.clientcompleted;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.Constant;
import app.positiveculture.com.agent.screen.properties.property.PropertyAdapter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The ClientCompleted Presenter
 */
public class ClientCompletedPresenter extends Presenter<ClientCompletedContract.View, ClientCompletedContract.Interactor>
        implements ClientCompletedContract.Presenter {

  private MemberDTO mClient;
  private String mFilter = "complete";
  private boolean mAllCompletedClient;
  private int mLimit = Constant.PAGE_LIMIT;
  private int mOffSet = 0;
  private PropertyAdapter mCompletedClientAdapter;
  private List<PropertyDTO> mListCompletedClient = new ArrayList<>();

  public ClientCompletedPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public ClientCompletedContract.View onCreateView() {
    return ClientCompletedFragment.getInstance();
  }

  @Override
  public void start() {
    if (mClient == null) return;
    getListCompleted();
  }

  private void getListCompleted() {
    mOffSet = 0;
    mAllCompletedClient = false;
    mInteractor.getListCompletedClient(String.valueOf(mClient.getId()), mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          mListCompletedClient.clear();
          mListCompletedClient.addAll(data);
          mCompletedClientAdapter = new PropertyAdapter(mListCompletedClient);
          mView.bindListCompleted(mCompletedClientAdapter);
        } else {
          mView.showEmptyLayout();
        }
      }
    });
  }

  @Override
  public void loadMore() {
    if (mAllCompletedClient) {
      mView.loadMoreSuccess();
      return;
    }
    mOffSet += mLimit;
    mInteractor.getListCompletedClient(String.valueOf(mClient.getId()), mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          int startPosition = mListCompletedClient.size();
          mListCompletedClient.addAll(data);
          mCompletedClientAdapter.notifyItemRangeInserted(startPosition, mListCompletedClient.size());
        } else {
          mAllCompletedClient = true;
          mView.loadMoreSuccess();
        }
      }
    });
  }

  @Override
  public ClientCompletedContract.Interactor onCreateInteractor() {
    return new ClientCompletedInteractor(this);
  }

  public ClientCompletedPresenter setClient(MemberDTO mClient) {
    this.mClient = mClient;
    return this;
  }
}
