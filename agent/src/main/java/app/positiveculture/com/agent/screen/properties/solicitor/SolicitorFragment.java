package app.positiveculture.com.agent.screen.properties.solicitor;

import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.data.response.dto.SolicitorDTO;
import butterknife.BindView;
import customview.CustomHeaderView;

/**
 * The Solicitor Fragment
 */
public class SolicitorFragment extends ViewFragment<SolicitorContract.Presenter> implements SolicitorContract.View {

  @BindView(R2.id.solicitor_header_cv)
  CustomHeaderView mHeader;
  @BindView(R2.id.solicitor_name_et)
  EditText mSolicitorNameEt;
  @BindView(R2.id.solicitor_address_et)
  EditText mSolicitorAddressEt;
  @BindView(R2.id.solicitor_clear_name_iv)
  ImageView mClearNameIv;
  @BindView(R2.id.solicitor_clear_address_iv)
  ImageView mClearAddressIv;
  @BindView(R2.id.solicitor_list_srv)
  SuperRecyclerView mSolicitorSrv;

  private Handler mHandler;
  private Runnable mRunnable;

  private OnMoreListener mOnMoreSolicitorListener = new OnMoreListener() {
    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
      mPresenter.onMoreSolicitorAsked();
    }
  };

  public static SolicitorFragment getInstance() {
    return new SolicitorFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_solicitor;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mHeader.getRightTextTv().setVisibility(View.GONE);
    setupListeners();
    mHandler = new Handler();
    mRunnable = new Runnable() {
      @Override
      public void run() {
        mPresenter.searchSolicitor(mSolicitorNameEt.getText().toString());
        mSolicitorSrv.setOnMoreListener(mOnMoreSolicitorListener);
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

    mHeader.getRightTextTv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.doneSelectSolicitor();
      }
    });

    mSolicitorSrv.setOnMoreListener(new OnMoreListener() {
      @Override
      public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        mPresenter.onMoreSolicitorAsked();
      }
    });

    mSolicitorNameEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(final CharSequence s, int start, int before, int count) {
        if (mRunnable != null) {
          mHandler.removeCallbacks(mRunnable);
        }
        if (s.length() == 0) {
          mClearNameIv.setVisibility(View.GONE);
        } else {
          mClearNameIv.setVisibility(View.VISIBLE);
        }
      }

      @Override
      public void afterTextChanged(final Editable s) {
        mHandler.postDelayed(mRunnable, 500);
      }
    });

    mSolicitorAddressEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (s.length() == 0) {
          mClearAddressIv.setVisibility(View.GONE);
        } else {
          mClearAddressIv.setVisibility(View.VISIBLE);
        }
      }
    });

    mClearNameIv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mSolicitorNameEt.setText("");
      }
    });

    mClearAddressIv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mSolicitorAddressEt.setText("");
      }
    });
  }

  @Override
  public void bindData() {
    mSolicitorSrv.setLayoutManager(new GridLayoutManager(getViewContext(), 1));
    mSolicitorSrv.setAdapter(mPresenter.getSolicitorAdapter());
  }

  @Override
  public void onLoadMoreSolicitorDone(boolean canLoadMore) {
    mSolicitorSrv.setLoadingMore(false);
    mSolicitorSrv.setOnMoreListener(canLoadMore ? mOnMoreSolicitorListener : null);
  }

  @Override
  public void showDoneTextView(SolicitorDTO solicitorDTO) {
    mHeader.getRightTextTv().setVisibility(View.VISIBLE);
  }

  @Override
  public void fillSolicitorInfo(SolicitorDTO solicitorDTO) {
    if (solicitorDTO != null) {
      mSolicitorNameEt.setText(solicitorDTO.getSolicitorName());
      mSolicitorAddressEt.setText(solicitorDTO.getSolicitorAddress());
    } else {
      mSolicitorNameEt.setText("");
      mSolicitorAddressEt .setText("");
    }
  }

}
