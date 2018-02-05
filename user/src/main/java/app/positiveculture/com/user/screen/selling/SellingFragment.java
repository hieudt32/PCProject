package app.positiveculture.com.user.screen.selling;

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
 * The Selling Fragment
 */
public class SellingFragment extends ViewFragment<SellingContract.Presenter> implements SellingContract.View {

  @BindView(R2.id.selling_rv)
  SuperRecyclerView mSellingRv;
  @BindView(R2.id.selling_empty_layout)
  RelativeLayout mEmptyLayout;
  @BindView(R2.id.empty_layout_tv)
  TextView mMessageEmpty;

  public static SellingFragment getInstance() {
    return new SellingFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_selling;
  }


  @Override
  public void bindProperty(PropertyAdapter mAdapter) {
    mSellingRv.setVisibility(View.VISIBLE);
    mSellingRv.setAdapter(mAdapter);
    mSellingRv.setLayoutManager(new GridLayoutManager(getViewContext(), 1));
    mSellingRv.setupMoreListener(new OnMoreListener() {
      @Override
      public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        mPresenter.loadMore();
      }
    }, 15);
    mSellingRv.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        mPresenter.start();
      }
    });
  }

  @Override
  public void loadMoreSuccessful() {
    mSellingRv.hideMoreProgress();
  }

  @Override
  public void displayEmptyLayout() {
    mSellingRv.setVisibility(View.GONE);
    mEmptyLayout.setVisibility(View.VISIBLE);
    mMessageEmpty.setText(getString(R.string.seller_empty));
  }
}
