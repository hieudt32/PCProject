package app.positiveculture.com.agent.screen.furnishingtenancy;

import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomHeaderView;

/**
 * The Furnishing and Tenacy Fragment
 * (using the same layout)
 */
public class FurnishingTenancyFragment extends ViewFragment<FurnishingTenancyContract.Presenter> implements FurnishingTenancyContract.View {

  @BindView(R2.id.furnishing_header)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.furnishing_hint_tv)
  TextView mDescriptionHintTv;
  @BindView(R2.id.furnishing_tenancy_tv)
  TextView mFurnishingTenancyTv;
  @BindView(R2.id.un_furnishing_tenancy_tv)
  TextView mUnFurnishingTenancyTv;


  @OnClick(R2.id.furnishing_tenancy_tv)
  void onFurnishingTenancyClick(){
    mPresenter.onFurnishingTenancyClick();
  }
  @OnClick(R2.id.un_furnishing_tenancy_tv)
  void onUnFurnishingTenancyClick(){
    mPresenter.onUnFurnishingTenancyClick();
  }

  public static FurnishingTenancyFragment getInstance() {
    return new FurnishingTenancyFragment();
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
  protected int getLayoutId() {
    return R.layout.fragment_furnishing_tenancy;
  }

  @Override
  public void bindViewForTenancy() {
    mHeaderView.setTitle(getString(R.string.tenancy));
    mFurnishingTenancyTv.setText(getString(R.string.property_subject_existing));
    mUnFurnishingTenancyTv.setText(getString(R.string.property_vacant_possession));
    mDescriptionHintTv.setText(getString(R.string.see_tenancy_agreement_in_property_details));
  }
}
