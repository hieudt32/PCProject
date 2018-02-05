package app.positiveculture.com.agent.screen.clientlist.clientprofile.clientselling;

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
 * The ClientSelling Fragment
 */
public class ClientSellingFragment extends ViewFragment<ClientSellingContract.Presenter> implements ClientSellingContract.View {

  @BindView(R2.id.client_selling_rv)
  SuperRecyclerView mClientSellingRv;
  @BindView(R2.id.client_selling_empty_rl)
  RelativeLayout mEmptyLayout;
  @BindView(R2.id.empty_layout_tv)
  TextView mEmptyMessage;

  public static ClientSellingFragment getInstance() {
    return new ClientSellingFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_client_selling;
  }

  @Override
  public void showEmptyLayout() {
    mEmptyLayout.setVisibility(View.VISIBLE);
    mClientSellingRv.setVisibility(View.GONE);
    mEmptyMessage.setText(getString(R.string.seller_empty));
  }

  @Override
  public void bindListSelling(PropertyAdapter mSellingClientAdapter) {
    mClientSellingRv.setVisibility(View.VISIBLE);
    mClientSellingRv.setAdapter(mSellingClientAdapter);
    mClientSellingRv.setLayoutManager(new LinearLayoutManager(getViewContext()));
    mClientSellingRv.setupMoreListener(new OnMoreListener() {
      @Override
      public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        mPresenter.loadMore();
      }
    }, 15);

    mClientSellingRv.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        mPresenter.start();
      }
    });
  }

  @Override
  public void loadMoreSuccess() {
    mClientSellingRv.hideMoreProgress();
  }
}
