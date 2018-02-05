package app.positiveculture.com.agent.screen.seller.congratulation;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

/**
 * The Congratulation Contract
 */
interface CongratulationContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
    void bindView(String mThumbUrl);
  }

  interface Presenter extends IPresenter<View, Interactor> {
  }
}



