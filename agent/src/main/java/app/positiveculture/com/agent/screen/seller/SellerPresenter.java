package app.positiveculture.com.agent.screen.seller;

import android.widget.Toast;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.Constant;
import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.event.CreateOtpCompleteEvent;
import app.positiveculture.com.agent.event.DeleteOtpEvent;
import app.positiveculture.com.agent.screen.properties.property.PropertyAdapter;
import app.positiveculture.com.agent.screen.seller.processtracker.ProcessTrackerPresenter;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.data.response.dto.ResponseDTO;

/**
 * The Seller Presenter
 */
public class SellerPresenter extends Presenter<SellerContract.View, SellerContract.Interactor>
        implements SellerContract.Presenter, PropertyAdapter.OnPropertyClickListener {

  private List<PropertyDTO> mListSeller = new ArrayList<>();
  private PropertyAdapter mAdapter;
  private int mOffSet = 0;
  private int mLimit = Constant.PAGE_LIMIT;
  private final String mFilter = "seller";
  private boolean mAllSeller = false;

  public SellerPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SellerContract.View onCreateView() {
    return SellerFragment.getInstance();
  }

  @Override
  public void start() {
    mOffSet = 0;
    mAllSeller = false;
    mInteractor.getListProperty(mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          mListSeller = data;
          mAdapter = new PropertyAdapter(mListSeller);
          mAdapter.setSwipeable(true);
          mAdapter.setOnPropertyClickListener(SellerPresenter.this);
          mView.bindData(mAdapter);
        } else {
          mView.showSellerEmptyList();
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
    if (mAllSeller) {
      mView.loadMoreSuccess();
      return;
    }
    mOffSet += mLimit;
    mInteractor.getListProperty(mLimit, mOffSet, mFilter, new CommonCallback<List<PropertyDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<PropertyDTO> data) {
        super.onSuccess(data);
        if (!data.isEmpty()) {
          int startIndex = mListSeller.size();
          mListSeller.addAll(data);
          mAdapter.notifyItemRangeInserted(startIndex, mListSeller.size());
          mView.loadMoreSuccess();
        } else {
          mAllSeller = true;
          mView.loadMoreSuccess();
        }
      }
    });
  }

  @Override
  public SellerContract.Interactor onCreateInteractor() {
    return new SellerInteractor(this);
  }

  @Override
  public void goToProcessTracker(PropertyDTO property) {
    new ProcessTrackerPresenter(mContainerView)
            .setProperty(property)
            .setTypeProcess(TypeProcess.Selling)
            .pushView();
  }

  @Override
  public void onItemPropertyClick(PropertyDTO propertyDTO) {
    goToProcessTracker(propertyDTO);
  }

  @Override
  public void onDeletePropertyClick(final PropertyDTO propertyDTO, final int position) {
    mView.showProgress();
    mInteractor.deleteProperty(String.valueOf(propertyDTO.getmId()), new CommonCallback<ResponseDTO>(getViewContext()) {
      @Override
      public void onSuccess(ResponseDTO data) {
        super.onSuccess(data);
        mListSeller.remove(propertyDTO);
        mAdapter.notifyItemRemoved(position);
        Toast.makeText(getViewContext(), getViewContext().getString(R.string.delete_property_success), Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Subscribe
  public void onCreateOtpComplete(CreateOtpCompleteEvent event) {
    start();
  }

  @Subscribe
  public void onDeleteOtpComplete(DeleteOtpEvent event) {
    start();
  }
}
