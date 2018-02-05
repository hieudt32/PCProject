package app.positiveculture.com.agent.screen.properties.detail.photofullview;

import com.gemvietnam.base.viper.Interactor;

/**
 * The PhotoFullView interactor
 */
class PhotoFullViewInteractor extends Interactor<PhotoFullViewContract.Presenter>
        implements PhotoFullViewContract.Interactor {

  PhotoFullViewInteractor(PhotoFullViewContract.Presenter presenter) {
    super(presenter);
  }
}
