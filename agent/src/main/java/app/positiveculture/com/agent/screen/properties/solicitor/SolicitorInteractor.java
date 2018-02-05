package app.positiveculture.com.agent.screen.properties.solicitor;

import com.gemvietnam.base.viper.Interactor;

import java.util.List;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.SolicitorDTO;

/**
 * The Solicitor interactor
 */
class SolicitorInteractor extends Interactor<SolicitorContract.Presenter>
        implements SolicitorContract.Interactor {

  SolicitorInteractor(SolicitorContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getListSolicitor(int limit, int offset, String search, CommonCallback<List<SolicitorDTO>> commonCallback) {
    ServiceBuilder.getInstance().getService().getListSolicitor(limit, offset, search).enqueue(commonCallback);
  }
}
