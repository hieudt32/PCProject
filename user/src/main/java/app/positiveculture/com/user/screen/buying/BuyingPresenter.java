package app.positiveculture.com.user.screen.buying;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.List;

import app.positiveculture.com.agent.Constant;
import app.positiveculture.com.agent.screen.properties.property.PropertyAdapter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.user.screen.userprocesstracker.UserProcessTrackerPresenter;

/**
 * The Buying Presenter
 */
public class BuyingPresenter extends Presenter<BuyingContract.View, BuyingContract.Interactor>
        implements BuyingContract.Presenter, PropertyAdapter.OnPropertyClickListener {

  private PropertyAdapter mAdapter;
  private List<PropertyDTO> mListBuying;
  private final String mFilter = "buying";
  private int mLimit = Constant.PAGE_LIMIT;
  private int mOffSet = 0;
  private boolean mAllBuying = false;

  public BuyingPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public BuyingContract.View onCreateView() {
    return BuyingFragment.getInstance();
  }

  @Override
  public void start() {
    getBuying();
  }

  private void getBuying() {
    mOffSet = 0;
    mAllBuying = false;
    mInteractor.getBuying(mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {

      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          mListBuying = data;
          mAdapter = new PropertyAdapter(mListBuying);
          mAdapter.setOnPropertyClickListener(BuyingPresenter.this);
          mView.bindProperty(mAdapter);
        } else {
          mView.displayEmptyLayout();
        }
      }
    });
  }

  @Override
  public void loadMore() {
    if (mAllBuying) {
      mView.hideProgress();
      return;
    }
    mOffSet += mLimit;
    mInteractor.getBuying(mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          int startIndex = mListBuying.size();
          mListBuying.addAll(data);
          mAdapter.notifyItemRangeInserted(startIndex, mListBuying.size());
          mView.loadMoreSuccessful();
        } else {
          mAllBuying = true;
          mView.loadMoreSuccessful();
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public BuyingContract.Interactor onCreateInteractor() {
    return new BuyingInteractor(this);
  }

  @Override
  public void onItemPropertyClick(PropertyDTO propertyDTO) {
    new UserProcessTrackerPresenter(mContainerView)
            .setProperty(propertyDTO)
            .setTypeProcess(TypeProcess.Buying)
            .pushView();
  }

  @Override
  public void onDeletePropertyClick(PropertyDTO propertyDTO, int position) {

  }
}
