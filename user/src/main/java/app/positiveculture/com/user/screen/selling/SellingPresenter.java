package app.positiveculture.com.user.screen.selling;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.List;

import app.positiveculture.com.agent.Constant;
import app.positiveculture.com.agent.screen.properties.property.PropertyAdapter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;
import app.positiveculture.com.user.screen.userprocesstracker.UserProcessTrackerPresenter;
import retrofit2.Call;

/**
 * The Selling Presenter
 */
public class SellingPresenter extends Presenter<SellingContract.View, SellingContract.Interactor>
        implements SellingContract.Presenter, PropertyAdapter.OnPropertyClickListener {

  private PropertyAdapter mAdapter;
  private List<PropertyDTO> mListSelling;
  private final String mFilter = "selling";
  private int mLimit = Constant.PAGE_LIMIT;
  private int mOffSet = 0;
  private boolean mAllSelling = false;

  public SellingPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SellingContract.View onCreateView() {
    return SellingFragment.getInstance();
  }

  @Override
  public void start() {
    getSelling();
  }

  private void getSelling() {
    mOffSet = 0;
    mAllSelling = false;
    mInteractor.getSelling(mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {

      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          mListSelling = data;
          mAdapter = new PropertyAdapter(mListSelling);
          mAdapter.setOnPropertyClickListener(SellingPresenter.this);
          mView.bindProperty(mAdapter);
        } else {
          mView.displayEmptyLayout();
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }

      @Override
      public void onFailure(Call<ResponseDTO<List<PropertyDTO>>> call, Throwable t) {
        super.onFailure(call, t);
      }
    });
  }

  @Override
  public void loadMore() {
    if (mAllSelling) {
      mView.loadMoreSuccessful();
      return;
    }
    mOffSet += mLimit;
    mInteractor.getSelling(mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          int startIndex = mListSelling.size();
          mListSelling.addAll(data);
          mAdapter.notifyItemRangeInserted(startIndex, mListSelling.size());
          mView.loadMoreSuccessful();
        } else {
          mAllSelling = true;
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
  public SellingContract.Interactor onCreateInteractor() {
    return new SellingInteractor(this);
  }

  @Override
  public void onItemPropertyClick(PropertyDTO propertyDTO) {
    new UserProcessTrackerPresenter(mContainerView)
            .setProperty(propertyDTO)
            .setTypeProcess(TypeProcess.Selling)
            .pushView();
  }

  @Override
  public void onDeletePropertyClick(PropertyDTO propertyDTO, int position) {

  }
}
