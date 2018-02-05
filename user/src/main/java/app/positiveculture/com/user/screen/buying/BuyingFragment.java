package app.positiveculture.com.user.screen.buying;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
 * The Buying Fragment
 */
public class BuyingFragment extends ViewFragment<BuyingContract.Presenter> implements BuyingContract.View {

  @BindView(R2.id.buying_rv)
  SuperRecyclerView mBuyingRv;
  @BindView(R2.id.buying_empty_layout)
  RelativeLayout mEmptyLayout;
  @BindView(R2.id.empty_layout_tv)
  TextView mMessageEmpty;

  public static BuyingFragment getInstance() {
    return new BuyingFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_buying;
  }

  @Override
  public void bindProperty(PropertyAdapter mAdapter) {
    mBuyingRv.setVisibility(View.VISIBLE);
    mBuyingRv.setAdapter(mAdapter);
    mBuyingRv.setLayoutManager(new GridLayoutManager(getViewContext(), 1));
    mBuyingRv.setupMoreListener(new OnMoreListener() {
      @Override
      public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        mPresenter.loadMore();
      }
    }, 15);
    mBuyingRv.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        mPresenter.start();
      }
    });
  }

  @Override
  public void loadMoreSuccessful() {
    mBuyingRv.hideMoreProgress();
  }

  @Override
  public void displayEmptyLayout() {
    mBuyingRv.setVisibility(View.GONE);
    mEmptyLayout.setVisibility(View.VISIBLE);
    mMessageEmpty.setText(getString(R.string.buyer_empty));
  }
}
