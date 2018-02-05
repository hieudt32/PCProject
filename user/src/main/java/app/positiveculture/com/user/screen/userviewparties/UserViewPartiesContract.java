package app.positiveculture.com.user.screen.userviewparties;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.agent.screen.seller.viewparties.ViewPartiesAdapter;

/**
 * The UserViewParties Contract
 */
interface UserViewPartiesContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
    void bindOwnerSide(ViewPartiesAdapter mOwnerAdapter);

    void bindBuyerSide(ViewPartiesAdapter mBuyerAdapter);
  }

  interface Presenter extends IPresenter<View, Interactor> {
  }
}



