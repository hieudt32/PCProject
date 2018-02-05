package app.positiveculture.com.agent.screen.clientlist;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.data.response.dto.MemberDTO;
import butterknife.BindView;
import customview.CustomHeaderView;

/**
 * The ClientList Fragment
 */
public class ClientListFragment extends ViewFragment<ClientListContract.Presenter> implements ClientListContract.View, ClientListAdapter.OnClientClick {

  @BindView(R2.id.client_list_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.client_list_search_et)
  EditText mSearchEt;
  @BindView(R2.id.client_list_rv)
  SuperRecyclerView mClientListRv;
  @BindView(R2.id.client_list_empty_layout)
  RelativeLayout mEmptyLayout;
  private String nameSearch = null;

  public static ClientListFragment getInstance() {
    return new ClientListFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_client_list;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mEmptyLayout.setVisibility(View.GONE);
    mClientListRv.setVisibility(View.VISIBLE);
    mHeaderView.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
      }

      @Override
      public void onRightIconClick() {
        mPresenter.goToCreateNewClient();
      }
    });

  }

  private void setupSearchFunction() {
    mSearchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
          nameSearch = mSearchEt.getText().toString().trim();
          if (nameSearch.compareTo("") != 0) mPresenter.searchName(nameSearch);
          getBaseActivity().hideKeyboard();
          mClientListRv.setLoadingMore(false);
        }
        return false;
      }
    });
  }

  @Override
  public void bindData(ClientListAdapter mAdapter) {
    mEmptyLayout.setVisibility(View.GONE);
    mClientListRv.setVisibility(View.VISIBLE);
    mClientListRv.setAdapter(mAdapter);
    mAdapter.setOnClientClick(this);
    setupSearchFunction();
    mClientListRv.setLayoutManager(new GridLayoutManager(getViewContext(), 1));
    mClientListRv.setupMoreListener(new OnMoreListener() {
      @Override
      public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        mPresenter.loadMoreClient(nameSearch);
      }
    }, 10);
    mClientListRv.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        mPresenter.refreshList(nameSearch);
      }
    });
  }

  @Override
  public void loadMoreSuccess() {
    mClientListRv.hideMoreProgress();
    mClientListRv.isLoadingMore();
  }

  @Override
  public void showNoClient() {
    mEmptyLayout.setVisibility(View.VISIBLE);
    mClientListRv.setVisibility(View.GONE);
  }

  @Override
  public void reloadClientList() {
    mSearchEt.setText(null);
    nameSearch = null;
    mClientListRv.setLoadingMore(false);
  }

  @Override
  public void onClientClick(MemberDTO memberDTO) {
    mPresenter.goToClientProfile(memberDTO);
  }

}
