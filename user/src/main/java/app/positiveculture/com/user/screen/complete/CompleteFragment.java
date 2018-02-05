package app.positiveculture.com.user.screen.complete;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import app.positiveculture.com.agent.screen.properties.property.PropertyAdapter;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.BindView;

/**
 * The Complete Fragment
 */
public class CompleteFragment extends ViewFragment<CompleteContract.Presenter> implements CompleteContract.View {

  @BindView(R2.id.user_complete_rv)
  SuperRecyclerView mCompleteRv;
  @BindView(R2.id.user_complete_empty_layout)
  RelativeLayout mEmptyLayout;
  @BindView(R2.id.empty_layout_tv)
  TextView mMessageEmpty;

  public static CompleteFragment getInstance() {
    return new CompleteFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_complete;
  }

  @Override
  public void bindCompleteProperty(PropertyAdapter mCompleteAdapter) {
    mCompleteRv.setVisibility(View.VISIBLE);
    mCompleteRv.setAdapter(mCompleteAdapter);
    mCompleteRv.setLayoutManager(new GridLayoutManager(getViewContext(), 1));
    mCompleteRv.setupMoreListener(new OnMoreListener() {
      @Override
      public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        mPresenter.loadMore();
      }
    }, 15);
    mCompleteRv.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        mPresenter.start();
      }
    });
  }

  @Override
  public void loadMoreSuccessful() {
    mCompleteRv.hideMoreProgress();
  }

  @Override
  public void showEmptyLayout() {
    mEmptyLayout.setVisibility(View.VISIBLE);
    mCompleteRv.setVisibility(View.GONE);
    mMessageEmpty.setText(getString(R.string.complete_empty));
  }
}
