package app.positiveculture.com.agent.screen.seller.viewparties;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The ViewParties Fragment
 */
public class ViewPartiesFragment extends ViewFragment<ViewPartiesContract.Presenter> implements ViewPartiesContract.View {

  @BindView(R2.id.view_parties_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.view_parties_owner_rv)
  RecyclerView mOwnerRv;
  @BindView(R2.id.view_parties_buyer_rv)
  RecyclerView mBuyerRv;
  @BindView(R2.id.view_parties_buyer_tv)
  TextView mBuyerSideTv;

  @OnClick(R2.id.right_text_tv)
  void doReassignClick() {
    DialogUtils.showDialog(getViewContext(), getString(R.string.reassign), getString(R.string.reassign_mes),
            new CustomDialog.OnConfirmSelected() {
              @Override
              public void onConfirmSelected() {
                mPresenter.reassignOTP();
              }
            }, new CustomDialog.OnCancelSelected() {
              @Override
              public void onCancelSelected() {

              }
            });
  }

  public static ViewPartiesFragment getInstance() {
    return new ViewPartiesFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_view_parties;
  }

  @Override
  public void initLayout() {
    super.initLayout();

    mHeaderView.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.back();
      }

      @Override
      public void onRightIconClick() {

      }
    });

  }

  @Override
  public void bindOwnerSide(ViewPartiesAdapter mOwnerAdapter) {
    mBuyerRv.setVisibility(View.GONE);
    mBuyerSideTv.setVisibility(View.GONE);
    mOwnerRv.setAdapter(mOwnerAdapter);
    mOwnerRv.setLayoutManager(new LinearLayoutManager(getViewContext()));
    mOwnerRv.setNestedScrollingEnabled(false);
  }

  @Override
  public void bindBuyerSide(ViewPartiesAdapter mBuyerAdapter) {
    mBuyerRv.setVisibility(View.VISIBLE);
    mBuyerSideTv.setVisibility(View.VISIBLE);
    mBuyerRv.setAdapter(mBuyerAdapter);
    mBuyerRv.setLayoutManager(new LinearLayoutManager(getViewContext()));
    mBuyerRv.setNestedScrollingEnabled(false);
  }

  @Override
  public void showButtonReassign() {
    mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
    mHeaderView.getRightTextTv().setText(getString(R.string.reassign));
  }
}
