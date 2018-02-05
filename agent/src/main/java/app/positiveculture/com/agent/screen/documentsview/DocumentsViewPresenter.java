package app.positiveculture.com.agent.screen.documentsview;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import app.positiveculture.com.agent.dto.Documents;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The DocumentsView Presenter
 */
public class DocumentsViewPresenter extends Presenter<DocumentsViewContract.View, DocumentsViewContract.Interactor>
        implements DocumentsViewContract.Presenter {

  private Documents mDocument;
  private PropertyDTO propertyDTO;

  public DocumentsViewPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public DocumentsViewContract.View onCreateView() {
    return DocumentsViewFragment.getInstance();
  }

  @Override
  public void start() {
    if (mDocument != null) {
      mView.bindDocument(mDocument);
    }
    if (propertyDTO != null) {
      mView.bindNameLocation(propertyDTO);
    }
  }

  @Override
  public void goBack() {
    mContainerView.back();
  }

  @Override
  public DocumentsViewContract.Interactor onCreateInteractor() {
    return new DocumentsViewInteractor(this);
  }

  public DocumentsViewPresenter setDocuments(Documents document) {
    mDocument = document;
    return this;
  }

  public DocumentsViewPresenter setPropertyDTO(PropertyDTO propertyDTO) {
    this.propertyDTO = propertyDTO;
    return this;
  }
}
