package app.positiveculture.com.user.screen.searchaddress;

import com.gemvietnam.base.viper.Interactor;

/**
 * The Search_Address interactor
 */
class SearchAddressInteractor extends Interactor<SearchAddressContract.Presenter>
        implements SearchAddressContract.Interactor {

    SearchAddressInteractor(SearchAddressContract.Presenter presenter) {
        super(presenter);
    }
}
