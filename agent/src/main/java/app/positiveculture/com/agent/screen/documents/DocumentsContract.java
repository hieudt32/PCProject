package app.positiveculture.com.agent.screen.documents;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

/**
 * The Documents Contract
 */
interface DocumentsContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
    void bindData(ItemDocumentsAdapter mAdapter);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void goBack();

    void onDocumentClicked(int position);
  }
}



