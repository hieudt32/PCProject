package app.positiveculture.com.agent.screen.clientlist.clientprofile.clientcompleted;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.screen.properties.property.PropertyAdapter;
import butterknife.BindView;

/**
 * The ClientCompleted Fragment
 */
public class ClientCompletedFragment extends ViewFragment<ClientCompletedContract.Presenter> implements ClientCompletedContract.View {

  @BindView(R2.id.client_completed_rv)
  SuperRecyclerView mClientCompletedRv;
  @BindView(R2.id.client_completed_empty_rl)
  RelativeLayout mEmptyLayout;
  @BindView(R2.id.empty_layout_tv)
  TextView mEmptyMessage;

  public static ClientCompletedFragment getInstance() {
    return new ClientCompletedFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_client_completed;
  }

  @Override
  public void showEmptyLayout() {
    mEmptyLayout.setVisibility(View.VISIBLE);
    mClientCompletedRv.setVisibility(View.GONE);
    mEmptyMessage.setText(getText(R.string.client_complete_empty));
  }

  @Override
  public void bindListCompleted(PropertyAdapter mCompletedClientAdapter) {
    mClientCompletedRv.setVisibility(View.VISIBLE);
    mClientCompletedRv.setAdapter(mCompletedClientAdapter);
    mClientCompletedRv.setLayoutManager(new LinearLayoutManager(getViewContext()));
    mClientCompletedRv.setupMoreListener(new OnMoreListener() {
      @Override
      public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        mPresenter.loadMore();
      }
    }, 15);
    mClientCompletedRv.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        mPresenter.start();
      }
    });
  }

  @Override
  public void loadMoreSuccess() {
    mClientCompletedRv.hideMoreProgress();
  }
}
