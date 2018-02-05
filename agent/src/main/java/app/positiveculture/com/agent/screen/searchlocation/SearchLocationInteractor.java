package app.positiveculture.com.agent.screen.searchlocation;

import com.gemvietnam.base.viper.Interactor;

/**
 * The SearchLocation interactor
 */
class SearchLocationInteractor extends Interactor<SearchLocationContract.Presenter>
	implements SearchLocationContract.Interactor {

	SearchLocationInteractor(SearchLocationContract.Presenter presenter) {
		super(presenter);
	}
}
