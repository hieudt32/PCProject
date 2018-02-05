package app.positiveculture.com.agent.screen.documents;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.dto.Documents;
import app.positiveculture.com.agent.screen.documentsview.DocumentsViewPresenter;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The Documents Presenter
 */
public class DocumentsPresenter extends Presenter<DocumentsContract.View, DocumentsContract.Interactor>
        implements DocumentsContract.Presenter {

  private ItemDocumentsAdapter mAdapter;
  private List<Documents> mList = new ArrayList<>();
  private PropertyDTO mPropertyDTO;

  public DocumentsPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public DocumentsContract.View onCreateView() {
    return DocumentsFragment.getInstance();
  }

  @Override
  public void start() {
    fakeData();
    mAdapter = new ItemDocumentsAdapter(mList);
    mView.bindData(mAdapter);
  }

  @Override
  public void goBack() {
    mContainerView.back();
  }

  private void fakeData() {
    for (int i = 0; i < 8; i++) {
      Documents document = new Documents();
      document.setmTitle(mView.getBaseActivity().getString(R.string.document) + i+1);
      document.setmContent(mView.getBaseActivity().getString(R.string.fk_documents) + i+1);
      mList.add(document);
    }
  }

  @Override
  public DocumentsContract.Interactor onCreateInteractor() {
    return new DocumentsInteractor(this);
  }

  @Override
  public void onDocumentClicked(int position) {
    new DocumentsViewPresenter(mContainerView)
            .setDocuments(mList.get(position))
            .setPropertyDTO(mPropertyDTO)
            .pushView();
  }

  public DocumentsPresenter setPropertyDTO(PropertyDTO propertyDTO) {
    this.mPropertyDTO = propertyDTO;
    return this;
  }
}
