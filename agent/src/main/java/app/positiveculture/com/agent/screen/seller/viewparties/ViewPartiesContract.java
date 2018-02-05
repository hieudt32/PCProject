package app.positiveculture.com.agent.screen.seller.viewparties;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

/**
 * The ViewParties Contract
 */
interface ViewPartiesContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
    void bindOwnerSide(ViewPartiesAdapter mOwnerAdapter);

    void bindBuyerSide(ViewPartiesAdapter mBuyerAdapter);

    void showButtonReassign();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void reassignOTP();
  }
}



