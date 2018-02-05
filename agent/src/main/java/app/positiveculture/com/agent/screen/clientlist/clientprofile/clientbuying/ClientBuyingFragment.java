package app.positiveculture.com.agent.screen.clientlist.clientprofile.clientbuying;

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
 * The ClientBuying Fragment
 */
public class ClientBuyingFragment extends ViewFragment<ClientBuyingContract.Presenter> implements ClientBuyingContract.View {

  @BindView(R2.id.client_buying_rv)
  SuperRecyclerView mClientBuyingRv;
  @BindView(R2.id.client_buying_empty_rl)
  RelativeLayout mEmptyLayout;
  @BindView(R2.id.empty_layout_tv)
  TextView mEmptyMessage;

  public static ClientBuyingFragment getInstance() {
    return new ClientBuyingFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_client_buying;
  }

  @Override
  public void showEmptyLayout() {
    mEmptyLayout.setVisibility(View.VISIBLE);
    mClientBuyingRv.setVisibility(View.GONE);
    mEmptyMessage.setText(getText(R.string.client_buyer_empty));
  }


  @Override
  public void bindListBuyingClient(PropertyAdapter mBuyingClientAdapter) {
    mClientBuyingRv.setVisibility(View.VISIBLE);
    mClientBuyingRv.setAdapter(mBuyingClientAdapter);
    mClientBuyingRv.setLayoutManager(new LinearLayoutManager(getViewContext()));
    mClientBuyingRv.setupMoreListener(new OnMoreListener() {
      @Override
      public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        mPresenter.loadMore();
      }
    },15);
    mClientBuyingRv.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        mPresenter.start();
      }
    });
  }

  @Override
  public void loadMoreSuccess() {
    mClientBuyingRv.hideMoreProgress();
  }
}
