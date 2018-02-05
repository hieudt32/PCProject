package app.positiveculture.com.agent.screen.clientlist.clientprofile.clientselling;

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
 * The ClientSelling Presenter
 */
public class ClientSellingPresenter extends Presenter<ClientSellingContract.View, ClientSellingContract.Interactor>
        implements ClientSellingContract.Presenter {

  private MemberDTO mClient;
  private String mFilter = "selling";
  private boolean mAllSellingClient;
  private int mLimit = Constant.PAGE_LIMIT;
  private int mOffSet = 0;
  private PropertyAdapter mSellingClientAdapter;
  private List<PropertyDTO> mListSellingClient = new ArrayList<>();

  public ClientSellingPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public ClientSellingContract.View onCreateView() {
    return ClientSellingFragment.getInstance();
  }

  @Override
  public void start() {
    if (mClient == null) return;
    getListSellingClient();
  }

  private void getListSellingClient() {
    mOffSet = 0;
    mAllSellingClient = false;
    mInteractor.getListPropertyClient(String.valueOf(mClient.getId()), mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        mListSellingClient.clear();
        if (!data.isEmpty()) {
          mListSellingClient.addAll(data);
          mSellingClientAdapter = new PropertyAdapter(mListSellingClient);
          mView.bindListSelling(mSellingClientAdapter);
        } else {
          mView.showEmptyLayout();
        }
      }
    });
  }

  @Override
  public void loadMore() {
    if (mAllSellingClient) {
      mView.loadMoreSuccess();
      return;
    }

    mOffSet += mLimit;
    mInteractor.getListPropertyClient(String.valueOf(mClient.getId()), mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          int startIndex = mListSellingClient.size();
          mListSellingClient.addAll(data);
          mSellingClientAdapter.notifyItemRangeInserted(startIndex, mListSellingClient.size());
        } else {
          mAllSellingClient = true;
          mView.loadMoreSuccess();
        }
      }
    });

  }

  @Override
  public ClientSellingContract.Interactor onCreateInteractor() {
    return new ClientSellingInteractor(this);
  }

  public ClientSellingPresenter setClient(MemberDTO mClient) {
    this.mClient = mClient;
    return this;
  }
}
