package app.positiveculture.com.agent.screen.seller.viewparties;

import com.gemvietnam.base.viper.Interactor;

/**
 * The ViewParties interactor
 */
class ViewPartiesInteractor extends Interactor<ViewPartiesContract.Presenter>
        implements ViewPartiesContract.Interactor {

  ViewPartiesInteractor(ViewPartiesContract.Presenter presenter) {
    super(presenter);
  }
}
