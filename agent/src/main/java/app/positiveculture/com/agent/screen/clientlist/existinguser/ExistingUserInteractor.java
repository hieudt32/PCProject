package app.positiveculture.com.agent.screen.clientlist.existinguser;

import com.gemvietnam.base.viper.Interactor;

import app.positiveculture.com.data.ApiService;
import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.AgentDTO;

/**
 * The ExistingUser interactor
 */
class ExistingUserInteractor extends Interactor<ExistingUserContract.Presenter>
        implements ExistingUserContract.Interactor {

    ExistingUserInteractor(ExistingUserContract.Presenter presenter) {
        super(presenter);
    }

    @Override
    public void addExistingClient(Long clientId, CommonCallback<AgentDTO> commonCallback) {
        ServiceBuilder.getInstance().getService().addExistingClient(clientId).enqueue(commonCallback);
    }
}
