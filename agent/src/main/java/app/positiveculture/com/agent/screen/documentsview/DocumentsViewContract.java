package app.positiveculture.com.agent.screen.documentsview;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.agent.dto.Documents;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The DocumentsView Contract
 */
interface DocumentsViewContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
    void bindDocument(Documents mDocument);

    void bindNameLocation(PropertyDTO propertyDTO);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goBack();
  }
}



