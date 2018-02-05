package app.positiveculture.com.agent.screen.furnishingtenancy;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

/**
 * The FurnishingTenancy Contract
 */
interface FurnishingTenancyContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
    void bindViewForTenancy();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void onUnFurnishingTenancyClick();

    void onFurnishingTenancyClick();
  }
}



