package app.positiveculture.com.agent.screen.properties.buyer;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.Constant;
import app.positiveculture.com.agent.screen.properties.property.PropertyAdapter;
import app.positiveculture.com.agent.screen.seller.processtracker.ProcessTrackerPresenter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The Buyer Presenter
 */
public class BuyerPresenter extends Presenter<BuyerContract.View, BuyerContract.Interactor>
        implements BuyerContract.Presenter, PropertyAdapter.OnPropertyClickListener {

  private List<PropertyDTO> mListBuyer = new ArrayList<>();
  private PropertyAdapter mAdapter;
  private int mLimit = Constant.PAGE_LIMIT;
  private int mOffSet = 0;
  private boolean mAllBuyer = false;
  private final String mFilter = "buyer";

  public BuyerPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public BuyerContract.View onCreateView() {
    return BuyerFragment.getInstance();
  }

  @Override
  public void start() {
    getListBuyer();
  }

  private void getListBuyer() {
    mOffSet = 0;
    mAllBuyer = false;
    mInteractor.getListProperty(mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          mListBuyer = data;
          mAdapter = new PropertyAdapter(mListBuyer);
          mAdapter.setOnPropertyClickListener(BuyerPresenter.this);
          mView.bindData(mAdapter);
        } else {
          mView.showBuyerEmptyList();
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public void loadMore() {
    if (mAllBuyer) {
      mView.loadMoreSuccess();
      return;
    }
    mOffSet += mLimit;
    mInteractor.getListProperty(mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          int startIndex = mListBuyer.size();
          mListBuyer.addAll(data);
          mAdapter.notifyItemRangeInserted(startIndex, mListBuyer.size());
          mView.loadMoreSuccess();
        } else {
          mAllBuyer = true;
          mView.loadMoreSuccess();
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public BuyerContract.Interactor onCreateInteractor() {
    return new BuyerInteractor(this);
  }

  @Override
  public void goToProcessTracker(PropertyDTO propertyDTO) {
    new ProcessTrackerPresenter(mContainerView)
            .setProperty(propertyDTO)
            .setTypeProcess(TypeProcess.Buying)
            .pushView();
  }

  @Override
  public void onItemPropertyClick(PropertyDTO propertyDTO) {
    goToProcessTracker(propertyDTO);
  }

  @Override
  public void onDeletePropertyClick(PropertyDTO propertyDTO, int position) {

  }
}
