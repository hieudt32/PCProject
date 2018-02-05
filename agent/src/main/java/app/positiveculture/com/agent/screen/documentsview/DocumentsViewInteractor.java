package app.positiveculture.com.agent.screen.documentsview;

import com.gemvietnam.base.viper.Interactor;

/**
 * The DocumentsView interactor
 */
class DocumentsViewInteractor extends Interactor<DocumentsViewContract.Presenter>
        implements DocumentsViewContract.Interactor {

  DocumentsViewInteractor(DocumentsViewContract.Presenter presenter) {
    super(presenter);
  }
}
