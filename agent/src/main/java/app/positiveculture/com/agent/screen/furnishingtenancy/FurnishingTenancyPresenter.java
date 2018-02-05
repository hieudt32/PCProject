package app.positiveculture.com.agent.screen.furnishingtenancy;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.data.enumdata.TypeFurnishing;
import app.positiveculture.com.data.enumdata.TypeTenancy;

/**
 * The FurnishingTenancy Presenter
 */
public class FurnishingTenancyPresenter extends Presenter<FurnishingTenancyContract.View, FurnishingTenancyContract.Interactor>
        implements FurnishingTenancyContract.Presenter {

  private boolean isTenancy = false;
  private OnChooseTypeFurnishingOrTenancyListener mOnChooseTypeFurnishingOrTenancyListener;

  public FurnishingTenancyPresenter setOnChooseTypeFurnishingOrTenancyListener(OnChooseTypeFurnishingOrTenancyListener mOnChooseTypeFurnishingOrTenancyListener) {
    this.mOnChooseTypeFurnishingOrTenancyListener = mOnChooseTypeFurnishingOrTenancyListener;
    return this;
  }

  public FurnishingTenancyPresenter setTenancy(boolean tenancy) {
    isTenancy = tenancy;
    return this;
  }

  public FurnishingTenancyPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public FurnishingTenancyContract.View onCreateView() {
    return FurnishingTenancyFragment.getInstance();
  }

  @Override
  public void start() {
    if (isTenancy) {
      mView.bindViewForTenancy();
    }
  }

  @Override
  public void onFurnishingTenancyClick() {
    if (mOnChooseTypeFurnishingOrTenancyListener != null) {
      if (isTenancy) {
        mOnChooseTypeFurnishingOrTenancyListener.onChooseTypeTenancy(TypeTenancy.existing_tenancy);
      } else {
        mOnChooseTypeFurnishingOrTenancyListener.onChooseTypeFurnishing(TypeFurnishing.sold_furnished);
      }
    }
    back();
  }

  @Override
  public void onUnFurnishingTenancyClick() {
    if (mOnChooseTypeFurnishingOrTenancyListener != null) {
      if (isTenancy) {
        mOnChooseTypeFurnishingOrTenancyListener.onChooseTypeTenancy(TypeTenancy.vacant_possession);
      } else {
        mOnChooseTypeFurnishingOrTenancyListener.onChooseTypeFurnishing(TypeFurnishing.sold_unfurnished);
      }
    }
    back();
  }

  @Override
  public FurnishingTenancyContract.Interactor onCreateInteractor() {
    return new FurnishingTenancyInteractor(this);
  }

  public interface OnChooseTypeFurnishingOrTenancyListener {
    void onChooseTypeFurnishing(TypeFurnishing typeFurnishing);

    void onChooseTypeTenancy(TypeTenancy typeTenancy);
  }
}
