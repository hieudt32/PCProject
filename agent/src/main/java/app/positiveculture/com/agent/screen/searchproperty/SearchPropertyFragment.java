package app.positiveculture.com.agent.screen.searchproperty;

import com.gemvietnam.base.viper.ViewFragment;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomHeaderView;

/**
 * The SearchProperty Fragment
 */
public class SearchPropertyFragment extends ViewFragment<SearchPropertyContract.Presenter> implements SearchPropertyContract.View {

  @BindView(R2.id.search_property_hd)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.search_property_rv)
  SuperRecyclerView mSearchRv;

  @OnClick(R2.id.left_icon_iv)
  void doBackClick() {
    mPresenter.back();
  }

  public static SearchPropertyFragment getInstance() {
    return new SearchPropertyFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_search_property;
  }
}
