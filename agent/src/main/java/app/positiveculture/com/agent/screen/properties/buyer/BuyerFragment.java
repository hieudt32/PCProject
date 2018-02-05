package app.positiveculture.com.agent.screen.properties.buyer;

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
 * The Buyer Fragment
 */
public class BuyerFragment extends ViewFragment<BuyerContract.Presenter> implements BuyerContract.View{

  @BindView(R2.id.ag_list_buyer_rv)
  SuperRecyclerView mBuyerRv;
  @BindView(R2.id.buyer_empty_layout)
  RelativeLayout mEmptyLayout;
  @BindView(R2.id.empty_layout_tv)
  TextView mEmptyTv;



  public static BuyerFragment getInstance() {
    return new BuyerFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_buyer;
  }

  @Override
  public void bindData(PropertyAdapter adapter) {
    mBuyerRv.setAdapter(adapter);
    mBuyerRv.setLayoutManager(new GridLayoutManager(getViewContext(), 1));
    mBuyerRv.setupMoreListener(new OnMoreListener() {
      @Override
      public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        mPresenter.loadMore();
      }
    },15);
    mBuyerRv.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        mPresenter.start();
      }
    });
  }

  @Override
  public void showBuyerEmptyList() {
    mBuyerRv.setVisibility(View.GONE);
    mEmptyLayout.setVisibility(View.VISIBLE);
    mEmptyTv.setText(getString(R.string.buyer_empty));
  }

  @Override
  public void loadMoreSuccess() {
    mBuyerRv.hideMoreProgress();
  }
}
