package app.positiveculture.com.agent.screen.properties.property;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.gemvietnam.base.viper.ViewFragment;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import butterknife.BindView;

/**
 * The Property Fragment
 */
public class PropertyFragment extends ViewFragment<PropertyContract.Presenter> implements PropertyContract.View{
  @BindView(R2.id.ag_list_property_rv)
  SuperRecyclerView mListPropertyRv;
  @BindView(R2.id.property_empty_layout)
  RelativeLayout mEmptyLayout;

  public static PropertyFragment getInstance() {
    return new PropertyFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_property;
  }

  @Override
  public void initLayout() {
    super.initLayout();
  }


  @Override
  public void bindData(PropertyAdapter adapter) {
    mListPropertyRv.setVisibility(View.VISIBLE);
    mEmptyLayout.setVisibility(View.GONE);
    mListPropertyRv.setAdapter(adapter);
    mListPropertyRv.setLayoutManager(new GridLayoutManager(getViewContext(), 1));
    mListPropertyRv.setupMoreListener(new OnMoreListener() {
      @Override
      public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        mPresenter.loadMore();
      }
    },15);
    mListPropertyRv.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        mPresenter.start();
      }
    });
  }

  @Override
  public void loadMoreSuccess() {
    mListPropertyRv.hideMoreProgress();
  }

  @Override
  public void showPropertyEmptyList() {
    mListPropertyRv.setVisibility(View.GONE);
    mEmptyLayout.setVisibility(View.VISIBLE);
  }
}
