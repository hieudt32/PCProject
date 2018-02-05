package app.positiveculture.com.agent.screen.clientlist.clientprofile.clientbuying;

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
 * The ClientBuying Presenter
 */
public class ClientBuyingPresenter extends Presenter<ClientBuyingContract.View, ClientBuyingContract.Interactor>
        implements ClientBuyingContract.Presenter {

  private MemberDTO mClient;
  private String mFilter = "buying";
  private boolean mAllBuyingClient;
  private int mLimit = Constant.PAGE_LIMIT;
  private int mOffSet = 0;
  private PropertyAdapter mBuyingClientAdapter;
  private List<PropertyDTO> mListBuyingClient = new ArrayList<>();

  public ClientBuyingPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public ClientBuyingContract.View onCreateView() {
    return ClientBuyingFragment.getInstance();
  }

  @Override
  public void start() {
    if (mClient == null) return;
    getListBuyingClient();
  }

  private void getListBuyingClient() {
    mOffSet = 0;
    mAllBuyingClient = false;
    mInteractor.getListBuyingClient(String.valueOf(mClient.getId()), mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          mListBuyingClient.clear();
          mListBuyingClient.addAll(data);
          mBuyingClientAdapter = new PropertyAdapter(mListBuyingClient);
          mView.bindListBuyingClient(mBuyingClientAdapter);
        } else {
          mView.showEmptyLayout();
        }
      }
    });
  }

  @Override
  public void loadMore() {
    if (mAllBuyingClient) {
      mView.loadMoreSuccess();
      return;
    }
    mOffSet = mOffSet + mLimit;
    mInteractor.getListBuyingClient(String.valueOf(mClient.getId()), mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          int startPosition = mListBuyingClient.size();
          mListBuyingClient.addAll(data);
          mBuyingClientAdapter.notifyItemRangeInserted(startPosition, mListBuyingClient.size());
        } else {
          mAllBuyingClient = true;
          mView.loadMoreSuccess();
        }
      }
    });
  }

  @Override
  public ClientBuyingContract.Interactor onCreateInteractor() {
    return new ClientBuyingInteractor(this);
  }

  public ClientBuyingPresenter setClient(MemberDTO mClient) {
    this.mClient = mClient;
    return this;
  }
}
