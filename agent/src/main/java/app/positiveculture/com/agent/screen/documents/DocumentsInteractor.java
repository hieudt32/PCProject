package app.positiveculture.com.agent.screen.documents;

import com.gemvietnam.base.viper.Interactor;

/**
 * The Documents interactor
 */
class DocumentsInteractor extends Interactor<DocumentsContract.Presenter>
        implements DocumentsContract.Interactor {

  DocumentsInteractor(DocumentsContract.Presenter presenter) {
    super(presenter);
  }
}
