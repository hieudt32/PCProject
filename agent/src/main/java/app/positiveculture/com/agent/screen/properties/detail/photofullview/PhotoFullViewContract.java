package app.positiveculture.com.agent.screen.properties.detail.photofullview;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

/**
 * The PhotoFullView Contract
 */
interface PhotoFullViewContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
    void bindPhoto(PhotoFullViewAdapter mAdapterPhoto, int size);
  }

  interface Presenter extends IPresenter<View, Interactor> {
  }
}



