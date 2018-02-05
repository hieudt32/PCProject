package app.positiveculture.com.agent.screen.properties.complete;

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
 * The Complete Presenter
 */
public class CompletePresenter extends Presenter<CompleteContract.View, CompleteContract.Interactor>
        implements CompleteContract.Presenter, PropertyAdapter.OnPropertyClickListener {

  private List<PropertyDTO> mListComplete = new ArrayList<>();
  private PropertyAdapter mAdapter;
  private int mOffSet = 0;
  private final String mFilter = "complete";
  private int mLimit = Constant.PAGE_LIMIT;
  private boolean mAllComplete = false;

  public CompletePresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public CompleteContract.View onCreateView() {
    return CompleteFragment.getInstance();
  }

  @Override
  public void start() {
    getListComplete(mLimit, mOffSet, mFilter);
  }

  private void getListComplete(int limit, int offSet, String filter) {
    mOffSet = 0;
    mAllComplete = false;
    mInteractor.getListProperty(limit, offSet, filter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          mListComplete.clear();
          mListComplete = data;
          mAdapter = new PropertyAdapter(mListComplete);
          mAdapter.setOnPropertyClickListener(CompletePresenter.this);
          mView.bindData(mAdapter);
        } else {
          mView.showCompleteEmptyList();
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
    if (mAllComplete) {
      mView.loadMoreSuccess();
      return;
    }
    mOffSet += mLimit;
    mInteractor.getListProperty(mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          int startIndex = mListComplete.size();
          mListComplete.addAll(data);
          mAdapter.notifyItemRangeInserted(startIndex, mListComplete.size());
          mView.loadMoreSuccess();
        } else {
          mAllComplete = true;
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
  public CompleteContract.Interactor onCreateInteractor() {
    return new CompleteInteractor(this);
  }

  @Override
  public void goToProcessTracker(PropertyDTO propertyDTO) {
    new ProcessTrackerPresenter(mContainerView)
            .setProperty(propertyDTO)
            .setTypeProcess(TypeProcess.Completed)
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
