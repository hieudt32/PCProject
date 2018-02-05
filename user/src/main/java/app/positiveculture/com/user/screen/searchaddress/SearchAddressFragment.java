package app.positiveculture.com.user.screen.searchaddress;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.RecyclerUtils;
import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.screen.location.SearchAdapter;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * The Search_Address Fragment
 */
public class SearchAddressFragment extends ViewFragment<SearchAddressContract.Presenter> implements SearchAddressContract.View {

  @BindView(R2.id.search_location_et)
  EditText mSearchLocationEt;
  @BindView(R2.id.search_rv)
  RecyclerView mLocationRv;

  private Handler mHandler;
  private Runnable mRunnable;

  public static SearchAddressFragment getInstance() {
    return new SearchAddressFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_search_address;
  }

  @OnClick(R2.id.left_icon_iv)
  public void back() {
    mPresenter.back();
  }

  @Override
  public void initLayout() {
    super.initLayout();

    mHandler = new Handler();
    mRunnable = new Runnable() {
      @Override
      public void run() {
        mPresenter.searchLocation(mSearchLocationEt.getText().toString());
      }
    };

    mSearchLocationEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mRunnable != null) {
          mHandler.removeCallbacks(mRunnable);
        }
      }

      @Override
      public void afterTextChanged(Editable s) {
        if (StringUtils.isEmpty(s.toString())) {
          mLocationRv.setVisibility(View.INVISIBLE);
        } else {
          mLocationRv.setVisibility(View.VISIBLE);
        }
        mHandler.postDelayed(mRunnable, 500);
      }
    });

    mSearchLocationEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
          String query = mSearchLocationEt.getText().toString();
          if (!StringUtils.isEmpty(query)) {
            mPresenter.searchLocation(query);
            ViewUtils.dismissKeyboard(getViewContext());
          }
          return true;
        }
        return false;
      }
    });
  }

  @Override
  public void bindListLocation(SearchAdapter searchAdapter) {
    RecyclerUtils.setupVerticalRecyclerView(getViewContext(), mLocationRv);
    mLocationRv.setAdapter(searchAdapter);
  }
}
