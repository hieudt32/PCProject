package app.positiveculture.com.agent.screen.location;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.RecyclerUtils;
import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.StepIndicatorHorizontal;
import app.positiveculture.com.agent.utils.ViewUtils;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;
import customview.CustomHeaderView;

/**
 * The Location Fragment
 */
public class LocationFragment extends ViewFragment<LocationContract.Presenter> implements LocationContract.View, CustomHeaderView.OnHeaderEventListener {

  @BindView(R2.id.location_step)
  StepIndicatorHorizontal mStep;
  @BindView(R2.id.location_hd)
  CustomHeaderView mHeader;
  @BindView(R2.id.find_property_edt)
  EditText mFind;
  @BindView(R2.id.add_addr_bt)
  TableRow mAddAddrBt;
  @BindView(R2.id.location_rv)
  RecyclerView mLocationRv;

  private Handler mHandler;
  private Runnable mRunnable;

  @OnTouch(R2.id.location_ll)
  boolean doTouchLayout() {
    getBaseActivity().hideKeyboard();
    return false;
  }

  public static LocationFragment getInstance() {
    return new LocationFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_location;
  }


  @OnClick(R2.id.add_addr_bt)
  public void addAddress() {
    mAddAddrBt.setClickable(false);
    mPresenter.showScreenAddr();
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mStep.setCurrentStep(2);
    RecyclerUtils.setupVerticalRecyclerView(getViewContext(), mLocationRv);

    mHandler = new Handler();
    mRunnable = new Runnable() {
      @Override
      public void run() {
        mPresenter.searchLocation(mFind.getText().toString());
      }
    };

    setupListeners();
  }

  private void setupListeners() {
    mHeader.setOnHeaderEventListener(this);

    mFind.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (mRunnable != null) {
          mHandler.removeCallbacks(mRunnable);
        }
      }

      @Override
      public void afterTextChanged(Editable editable) {
        if (StringUtils.isEmpty(editable.toString())) {
          mLocationRv.setVisibility(View.INVISIBLE);
        } else {
          mLocationRv.setVisibility(View.VISIBLE);
        }
        mHandler.postDelayed(mRunnable, 500);
      }
    });

    mFind.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
          String query = mFind.getText().toString();
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
    mLocationRv.setAdapter(searchAdapter);
  }

  @Override
  public void onLeftIconClick() {
    mPresenter.goBack();
  }

  @Override
  public void onRightIconClick() {

  }

  @Override
  public void enableAddBt() {
    mAddAddrBt.setClickable(true);
  }
}
