package app.positiveculture.com.agent.screen.selectowners;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.data.response.dto.MemberDTO;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;
import customview.CustomHeaderView;

/**
 * The SelectOwners Fragment
 */
public class SelectOwnersFragment extends ViewFragment<SelectOwnersContract.Presenter> implements SelectOwnersContract.View {

  @BindView(R2.id.select_owners_hd)
  CustomHeaderView mHeader;
  @BindView(R2.id.next_select_owners_cb)
  CustomButton mNext;
  @BindView(R2.id.search_owners_et)
  EditText mSearch;
  @BindView(R2.id.select_owners_rv)
  SuperRecyclerView mListOwnerRv;
  @BindView(R2.id.owners_selected_tv)
  TextView mListSelectedTV;

  @OnClick(R2.id.next_select_owners_cb)
  void doNext() {
    if (mNext.isEnabled()) mPresenter.goToPropertyType();
  }

  private String mNameSearch;

  public static SelectOwnersFragment getInstance() {
    return new SelectOwnersFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_select_owners;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mHeader.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.goBack();
      }

      @Override
      public void onRightIconClick() {
        mPresenter.goToCreateNewClient();
      }
    });

    mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
          getBaseActivity().hideKeyboard();
          mNameSearch = mSearch.getText().toString().trim();
          if (!StringUtils.isEmpty(mNameSearch)) {
            mPresenter.onSearchOwner(mNameSearch);
            mListOwnerRv.setLoadingMore(false);
          }
          return true;
        }
        return false;
      }
    });

    mListOwnerRv.setupMoreListener(new OnMoreListener() {
      @Override
      public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        mPresenter.loadMoreOwner(mNameSearch);
      }
    }, 10);

    mListOwnerRv.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        mPresenter.refreshList(mNameSearch);
      }
    });
  }

  @Override
  public void bindSearchData(SelectOwnersAdapter adapter) {
    mListOwnerRv.setAdapter(adapter);
    mListOwnerRv.setLayoutManager(new GridLayoutManager(getViewContext(), 1));
  }

  @Override
  public void bindDataSelectedOwner(List<MemberDTO> mListSelected) {
    String listSelect = "";
    if (!mListSelected.isEmpty()) {
      for (int i = 0; i < mListSelected.size(); i++) {
        if (i == mListSelected.size() - 1) {
          listSelect = listSelect + mListSelected.get(i).getUsers().getFullName();
        } else {
          listSelect = listSelect + mListSelected.get(i).getUsers().getFullName() + ", ";
        }
      }

      mNext.statusEnableButton();
      mListSelectedTV.setText(listSelect);
    } else {
      mNext.statusDisabledButton();
      mListSelectedTV.setText(listSelect);
    }
  }

  @Override
  public void bindViewFromSummary() {
    mHeader.getLeftIconIv().setImageResource(R.drawable.ic_back);
    mNext.setVisibility(View.GONE);
  }

  @Override
  public void bindViewForAssignClient() {
    mHeader.setTitle(getString(R.string.assign_client));
    mNext.setTextButton(getString(R.string.assign));
  }

  @Override
  public void bindViewForReassignClient() {
    mHeader.setTitle(getString(R.string.reassign_client));
    mNext.setTextButton(getString(R.string.reassign));
  }

  @Override
  public void onLoadMoreSuccessful() {
    mListOwnerRv.hideMoreProgress();
  }
}
