package app.positiveculture.com.agent.screen.properties.complete;

import com.gemvietnam.base.viper.Interactor;

import java.util.List;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The Complete interactor
 */
class CompleteInteractor extends Interactor<CompleteContract.Presenter>
        implements CompleteContract.Interactor {

  CompleteInteractor(CompleteContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getListProperty(int limit, int offSet, String filterType, CommonCallback<List<PropertyDTO>> commonCallback) {
    ServiceBuilder.getInstance().getService().getListProperty(limit, offSet, filterType).enqueue(commonCallback);
  }
}
