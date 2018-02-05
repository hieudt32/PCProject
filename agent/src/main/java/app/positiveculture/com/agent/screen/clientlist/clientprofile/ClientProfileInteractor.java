package app.positiveculture.com.agent.screen.clientlist.clientprofile;

import com.gemvietnam.base.viper.Interactor;

/**
 * The ClientProfile interactor
 */
class ClientProfileInteractor extends Interactor<ClientProfileContract.Presenter>
        implements ClientProfileContract.Interactor {

    ClientProfileInteractor(ClientProfileContract.Presenter presenter) {
        super(presenter);
    }
}
