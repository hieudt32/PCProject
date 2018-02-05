package app.positiveculture.com.agent.screen.searchproperty;

import com.gemvietnam.base.viper.Interactor;

/**
 * The SearchProperty interactor
 */
class SearchPropertyInteractor extends Interactor<SearchPropertyContract.Presenter>
        implements SearchPropertyContract.Interactor {

  SearchPropertyInteractor(SearchPropertyContract.Presenter presenter) {
    super(presenter);
  }
}
