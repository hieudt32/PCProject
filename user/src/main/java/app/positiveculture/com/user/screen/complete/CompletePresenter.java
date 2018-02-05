package app.positiveculture.com.user.screen.complete;

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
 * The Complete Presenter
 */
public class CompletePresenter extends Presenter<CompleteContract.View, CompleteContract.Interactor>
        implements CompleteContract.Presenter, PropertyAdapter.OnPropertyClickListener {

  private PropertyAdapter mCompleteAdapter;
  private List<PropertyDTO> mListComplete;
  private final String mFilter = "complete";
  private int mLimit = Constant.PAGE_LIMIT;
  private int mOffSet = 0;
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
    getComplete();
  }


  private void getComplete() {
    mOffSet = 0;
    mAllComplete = false;
    mInteractor.getComplete(mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {

      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          mListComplete = data;
          mCompleteAdapter = new PropertyAdapter(mListComplete);
          mCompleteAdapter.setOnPropertyClickListener(CompletePresenter.this);
          mView.bindCompleteProperty(mCompleteAdapter);
        } else {
          mView.showEmptyLayout();
        }
      }
    });
  }

  @Override
  public void loadMore() {
    if (mAllComplete) {
      mView.loadMoreSuccessful();
      return;
    }
    mOffSet += mLimit;
    mInteractor.getComplete(mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (data.isEmpty()) {
          mAllComplete = true;
          mView.hideProgress();
        } else {
          int startIndex = mListComplete.size();
          mListComplete.addAll(data);
          mCompleteAdapter.notifyItemRangeInserted(startIndex, mListComplete.size());
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
  public CompleteContract.Interactor onCreateInteractor() {
    return new CompleteInteractor(this);
  }

  @Override
  public void onItemPropertyClick(PropertyDTO propertyDTO) {
    new UserProcessTrackerPresenter(mContainerView)
            .setProperty(propertyDTO).setTypeProcess(TypeProcess.Completed).pushView();
  }

  @Override
  public void onDeletePropertyClick(PropertyDTO propertyDTO, int position) {

  }
}
