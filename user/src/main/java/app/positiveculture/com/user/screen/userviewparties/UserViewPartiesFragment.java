package app.positiveculture.com.user.screen.userviewparties;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.screen.seller.viewparties.ViewPartiesAdapter;
import app.positiveculture.com.user.R;
import butterknife.BindView;
import customview.CustomHeaderView;

/**
 * The UserViewParties Fragment
 */
public class UserViewPartiesFragment extends ViewFragment<UserViewPartiesContract.Presenter> implements UserViewPartiesContract.View {

  @BindView(R2.id.view_parties_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.view_parties_owner_rv)
  RecyclerView mOwnerRv;
  @BindView(R2.id.view_parties_buyer_rv)
  RecyclerView mBuyerRv;
  @BindView(R2.id.view_parties_buyer_tv)
  TextView mBuyerSideTv;

  public static UserViewPartiesFragment getInstance() {
    return new UserViewPartiesFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_view_parties;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mHeaderView.getRightTextTv().setVisibility(View.GONE);
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
}
