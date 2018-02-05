package app.positiveculture.com.agent.screen.properties.selectagent;

import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.screen.properties.selectagent.adapteragent.SelectAgentAdapter;
import app.positiveculture.com.data.response.dto.AgentDTO;
import butterknife.BindView;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The SelectAgent Fragment
 */
public class SelectAgentFragment extends ViewFragment<SelectAgentContract.Presenter> implements SelectAgentContract.View {

  @BindView(R2.id.header_select_agent)
  CustomHeaderView mHeader;
  @BindView(R2.id.search_agent_et)
  EditText mSearchEt;
  @BindView(R2.id.select_agent_rv)
  SuperRecyclerView mAgentListSrv;

  private Handler mHandler;
  private Runnable mRunnable;

  public static SelectAgentFragment getInstance() {
    return new SelectAgentFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_select_agent;
  }

  private OnMoreListener mOnMoreAgentListener = new OnMoreListener() {
    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
      mPresenter.onMoreAgentAsked();
    }
  };

  @Override
  public void initLayout() {
    super.initLayout();
    setupListeners();
    mHandler = new Handler();
    mRunnable = new Runnable() {
      @Override
      public void run() {
        mPresenter.searchAgent(mSearchEt.getText().toString());
        mAgentListSrv.setOnMoreListener(mOnMoreAgentListener);
      }
    };
  }

  private void setupListeners() {
    mHeader.getLeftIconIv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.goBack();
      }
    });

    mAgentListSrv.setOnMoreListener(mOnMoreAgentListener);

    mSearchEt.addTextChangedListener(new TextWatcher() {
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
        mHandler.postDelayed(mRunnable, 500);
      }
    });
  }

  @Override
  public void bindData() {
    mAgentListSrv.setLayoutManager(new GridLayoutManager(getViewContext(), 1));
    mAgentListSrv.setAdapter(mPresenter.getAgentAdapter());
  }

  @Override
  public void onLoadMoreAgentDone(boolean canLoadMore) {
    mAgentListSrv.setLoadingMore(false);
    mAgentListSrv.setOnMoreListener(canLoadMore ? mOnMoreAgentListener : null);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mPresenter.doneSelectAgent();
  }

}

