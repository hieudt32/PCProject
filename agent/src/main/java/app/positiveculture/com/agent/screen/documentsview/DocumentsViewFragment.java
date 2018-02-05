package app.positiveculture.com.agent.screen.documentsview;

import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.dto.Documents;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import butterknife.BindView;
import customview.CustomHeaderView;

/**
 * The DocumentsView Fragment
 */
public class DocumentsViewFragment extends ViewFragment<DocumentsViewContract.Presenter> implements DocumentsViewContract.View {

  public static DocumentsViewFragment getInstance() {
    return new DocumentsViewFragment();
  }

  @BindView(R2.id.documents_view_hd)
  CustomHeaderView mHeader;
  @BindView(R2.id.content_documents_view)
  TextView mContent;
  @BindView(R2.id.name_document_view)
  TextView mName;
  @BindView(R2.id.location_documents_view)
  TextView mLocation;

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_documents_view;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mHeader.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.goBack();
      }

      @Override
      public void onRightIconClick() {

      }
    });
  }

  @Override
  public void bindDocument(Documents mDocument) {
    mHeader.setTitle(mDocument.getmTitle());
    mContent.setText(mDocument.getmContent());
  }

  @Override
  public void bindNameLocation(PropertyDTO propertyDTO) {
    mName.setText(propertyDTO.getmStreetLineTwo());
    mLocation.setText(propertyDTO.getmStreetLineOne());
  }
}
