package app.positiveculture.com.agent.screen.properties.property;

import android.widget.Toast;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.eventbus.EventBusWrapper;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.Constant;
import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.event.CreateOtpCompleteEvent;
import app.positiveculture.com.agent.event.DeleteOtpEvent;
import app.positiveculture.com.agent.screen.properties.detail.DetailPresenter;
import app.positiveculture.com.agent.event.ListingCreatedEvent;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;

/**
 * The Property Presenter
 */
public class PropertyPresenter extends Presenter<PropertyContract.View, PropertyContract.Interactor>
        implements PropertyContract.Presenter, DetailPresenter.OnBackToPropertyScreenListener, PropertyAdapter.OnPropertyClickListener {

  private List<PropertyDTO> mListProperty = new ArrayList<>();
  private PropertyAdapter mAdapter;
  private int mOffSet = 0;
  private int mLimit = Constant.PAGE_LIMIT;
  private final String mFilter = "property";
  private boolean mAllProperty = false;

  public PropertyPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public PropertyContract.View onCreateView() {
    return PropertyFragment.getInstance();
  }

  @Override
  public void start() {
    getPropertyList();
  }

  private void getPropertyList() {
    mOffSet = 0;
    mAllProperty = false;
    mInteractor.getListProperty(mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          mListProperty.clear();
          mListProperty = data;
          mAdapter = new PropertyAdapter(mListProperty);
          mAdapter.setSwipeable(true);
          mAdapter.setOnPropertyClickListener(PropertyPresenter.this);
          mView.bindData(mAdapter);
        } else {
          mView.showPropertyEmptyList();
        }
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public PropertyContract.Interactor onCreateInteractor() {
    return new PropertyInteractor(this);
  }

  @Override
  public void loadMore() {
    if (mAllProperty) {
      mView.loadMoreSuccess();
      return;
    }
    mOffSet += mLimit;
    mInteractor.getListProperty(mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          int startIndex = mListProperty.size();
          mListProperty.addAll(data);
          mAdapter.notifyItemRangeInserted(startIndex, mListProperty.size());
          mView.loadMoreSuccess();
        } else {
          mAllProperty = true;
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
  public void showDetail(PropertyDTO propertyDTO) {
    new DetailPresenter(mContainerView)
            .setProperty(propertyDTO)
            .setOnBackToPropertyScreenListener(this)
            .pushView();
  }

  @Override
  public void onResetPropertyClick() {
    start();
    EventBusWrapper.post(new CreateOtpCompleteEvent());
    mAdapter.setOnPropertyClickListener(this);
  }

  @Override
  public void onItemPropertyClick(PropertyDTO propertyDTO) {
    new DetailPresenter(mContainerView)
            .setProperty(propertyDTO)
            .setOnBackToPropertyScreenListener(this)
            .pushView();
    mAdapter.setOnPropertyClickListener(null);
  }

  @Override
  public void onDeletePropertyClick(final PropertyDTO propertyDTO, final int position) {
    mView.showProgress();
    mInteractor.deleteProperty(String.valueOf(propertyDTO.getmId()), new CommonCallback<ResponseDTO>(getViewContext()) {
      @Override
      public void onSuccess(ResponseDTO data) {
        super.onSuccess(data);
        mListProperty.remove(propertyDTO);
        mAdapter.notifyItemRemoved(position);
        Toast.makeText(getViewContext(), getViewContext().getString(R.string.delete_property_success), Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Subscribe
  public void onShowNoticeEvent(ListingCreatedEvent createdEvent) {
    start();
  }

  @Subscribe
  public void onDeleteOtpComplete(DeleteOtpEvent event) {
    start();
  }
}
