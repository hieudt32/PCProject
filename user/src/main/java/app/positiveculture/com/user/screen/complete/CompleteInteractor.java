package app.positiveculture.com.user.screen.complete;

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
	public void getComplete(int limit, int offSet, String filter, CommonCallback<List<PropertyDTO>> commonCallback) {
		ServiceBuilder.getInstance().getService().getListProperty(limit, offSet,filter).enqueue(commonCallback);
	}
}
