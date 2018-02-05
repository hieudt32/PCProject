package app.positiveculture.com.agent.screen.properties.solicitor;

import android.support.v7.widget.RecyclerView;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.SolicitorDTO;

/**
 * The Solicitor Presenter
 */
public class SolicitorPresenter extends Presenter<SolicitorContract.View, SolicitorContract.Interactor>
        implements SolicitorContract.Presenter, SolicitorAdapter.OnSolicitorSelect {

  private static final int LIMIT = 15;

  private SolicitorAdapter mSolicitorAdapter;
  private ArrayList<SolicitorDTO> mSolicitorList = new ArrayList<>();
  private int mOffset = 0;
  private String mSearchKey;
  private SolicitorDTO mSelectedSolicitor = new SolicitorDTO();
  private OnDoneSelectSolicitorListener mOnDoneSelectSolicitorListener;

  private CommonCallback<List<SolicitorDTO>> mSolicitorCallBack;
  private CommonCallback<List<SolicitorDTO>> mMoreSolicitorCallBack;

  public SolicitorPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SolicitorContract.View onCreateView() {
    return SolicitorFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
    mSolicitorAdapter = new SolicitorAdapter(mSolicitorList);
    if (mSelectedSolicitor != null) {
      mSolicitorAdapter.setSelectedSolicitor(mSelectedSolicitor);
      mView.fillSolicitorInfo(mSelectedSolicitor);
    }
    setupCallBacks();
    getSolicitorList(LIMIT, mOffset, mSearchKey, mSolicitorCallBack);
  }

  private void setupCallBacks() {
    mSolicitorCallBack = new CommonCallback<List<SolicitorDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<SolicitorDTO> data) {
        super.onSuccess(data);
        mSolicitorList.clear();
        if (data != null) {
          mSolicitorList.addAll(data);
          mOffset += LIMIT;
        }
        mSolicitorAdapter.setOnSolicitorClick(SolicitorPresenter.this);
        mView.bindData();
      }
    };

    mMoreSolicitorCallBack = new CommonCallback<List<SolicitorDTO>>(getViewContext()) {
      @Override
      public void onSuccess(List<SolicitorDTO> data) {
        super.onSuccess(data);
        mSolicitorAdapter.loadMore(data);
        mOffset += LIMIT;
        mView.onLoadMoreSolicitorDone(data.size() > 0);
      }
    };
  }

  public SolicitorPresenter setOnDoneSelectSolicitorListener(OnDoneSelectSolicitorListener listener) {
    mOnDoneSelectSolicitorListener = listener;
    return this;
  }

  public SolicitorPresenter setSelectedSolicitor(SolicitorDTO solicitor) {
    mSelectedSolicitor = solicitor;
    return this;
  }

  @Override
  public SolicitorContract.Interactor onCreateInteractor() {
    return new SolicitorInteractor(this);
  }

  @Override
  public void goBack() {
    back();
  }

  @Override
  public void onMoreSolicitorAsked() {
    getSolicitorList(LIMIT, mOffset, mSearchKey, mMoreSolicitorCallBack);
  }

  @Override
  public void searchSolicitor(String searchKey) {
    mOffset = 0;
    mSearchKey = searchKey;
    getSolicitorList(LIMIT, mOffset, mSearchKey, mSolicitorCallBack);
  }

  private void getSolicitorList(int limit, int offset, String searchKey, CommonCallback<List<SolicitorDTO>> callback) {
    mInteractor.getListSolicitor(limit, offset, searchKey, callback);
  }

  @Override
  public void onSolicitorSelect(SolicitorDTO solicitorDTO) {
    mSelectedSolicitor = solicitorDTO;
    mView.showDoneTextView(mSelectedSolicitor);
    mView.fillSolicitorInfo(mSelectedSolicitor);
  }

  @Override
  public RecyclerView.Adapter getSolicitorAdapter() {
    return mSolicitorAdapter;
  }

  @Override
  public void doneSelectSolicitor() {
    if (mSelectedSolicitor == null) {
      mSelectedSolicitor = mSolicitorList.get(0);
    }
    if (mOnDoneSelectSolicitorListener != null) {
      mOnDoneSelectSolicitorListener.onDone(mSelectedSolicitor);
    }
    back();
  }

  public interface OnDoneSelectSolicitorListener {
    void onDone(SolicitorDTO solicitorDTO);
  }

}
