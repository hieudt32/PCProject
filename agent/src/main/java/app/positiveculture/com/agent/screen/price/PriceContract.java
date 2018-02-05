package app.positiveculture.com.agent.screen.price;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

/**
 * The Price Contract
 */
interface PriceContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
    void setPriceFromSummary(double price, double valuation);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goBack();

    void goToDescriptionScreen(double price, double valuation);

    void goBackToSummary(double price, double valuation);
  }
}



