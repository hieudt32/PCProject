package app.positiveculture.com.agent.screen.properties.complete;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.screen.properties.property.PropertyAdapter;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import butterknife.BindView;

/**
 * The Complete Fragment
 */
public class CompleteFragment extends ViewFragment<CompleteContract.Presenter> implements CompleteContract.View {

  @BindView(R2.id.ag_list_complete_rv)
  SuperRecyclerView mCompleteRv;
  @BindView(R2.id.complete_empty_layout)
  RelativeLayout mEmptyLayout;
  @BindView(R2.id.empty_layout_tv)
  TextView mEmptyTv;

  public static CompleteFragment getInstance() {
    return new CompleteFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_agent_complete;
  }

  @Override
  public void bindData(PropertyAdapter adapter) {
    mCompleteRv.setAdapter(adapter);
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
  public void showCompleteEmptyList() {
    mCompleteRv.setVisibility(View.GONE);
    mEmptyLayout.setVisibility(View.VISIBLE);
    mEmptyTv.setText(getString(R.string.complete_empty));
  }

  @Override
  public void loadMoreSuccess() {
    mCompleteRv.hideMoreProgress();
  }
}
