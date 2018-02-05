package app.positiveculture.com.agent.screen.description;

import com.gemvietnam.base.viper.Interactor;

/**
 * The Description interactor
 */
class DescriptionInteractor extends Interactor<DescriptionContract.Presenter>
        implements DescriptionContract.Interactor {

  DescriptionInteractor(DescriptionContract.Presenter presenter) {
    super(presenter);
  }
}
