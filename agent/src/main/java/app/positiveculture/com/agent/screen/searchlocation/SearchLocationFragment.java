package app.positiveculture.com.agent.screen.searchlocation;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.RecyclerUtils;
import com.gemvietnam.utils.StringUtils;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.screen.location.SearchAdapter;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The SearchLocation Fragment
 */
public class SearchLocationFragment extends ViewFragment<SearchLocationContract.Presenter> implements SearchLocationContract.View, CustomHeaderView.OnHeaderEventListener{

  @BindView(R2.id.search_rv)
  SuperRecyclerView mSearchRv;
  @BindView(R2.id.search_location_hd)
  CustomHeaderView mHeader;
  @BindView(R2.id.search_location_et)
  EditText mSearch;
  @OnClick(R2.id.delete_all_iv)
  void doDeleteAllClick(){
    mSearch.setText("");
    //Todo unbind Search
  }

  public static SearchLocationFragment getInstance() {
    return new SearchLocationFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_search_location;
  }

  @Override
  public void bindSearchs(SearchAdapter searchAdapter) {
    RecyclerUtils.setupVerticalRecyclerView(getViewContext(), mSearchRv.getRecyclerView());
    mSearchRv.setAdapter(searchAdapter);
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId== EditorInfo.IME_ACTION_SEARCH){
          if(!StringUtils.isEmpty(mSearch.getText())){
            getBaseActivity().hideKeyboard();
            //TODO Call Search
          }else{
            DialogUtils.showDialog(getContext(), getString(R.string.error), getString(R.string.error_messenger), new CustomDialog.OnConfirmSelected() {
              @Override
              public void onConfirmSelected() {
              }
            });
          }
          return true;
        }
        return false;
      }
    });
    mHeader.setOnHeaderEventListener(this);
  }

  @Override
  public void onLeftIconClick() {
    mPresenter.back();
  }

  @Override
  public void onRightIconClick() {

  }
}
