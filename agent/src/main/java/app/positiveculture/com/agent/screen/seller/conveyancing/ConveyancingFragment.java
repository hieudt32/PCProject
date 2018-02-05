package app.positiveculture.com.agent.screen.seller.conveyancing;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.gemvietnam.base.viper.ViewFragment;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.data.response.dto.DocumentDTO;
import butterknife.BindView;
import customview.CustomHeaderView;

/**
 * The Conveyancing Fragment
 */
public class ConveyancingFragment extends ViewFragment<ConveyancingContract.Presenter> implements ConveyancingContract.View {

  @BindView(R2.id.conveyancing_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.conveyancing_document_rv)
  RecyclerView mDocumentRv;
  @BindView(R2.id.conveyancing_loan_information_ll)
  LinearLayout mLoanInformationLl;
  @BindView(R2.id.loan_status_view)
  View mLoanStatusView;

  private DocumentAdapter mDocumentAdapter;
  private List<DocumentDTO> mListDocument = new ArrayList<>();

  public static ConveyancingFragment getInstance() {
    return new ConveyancingFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_conveyancing;
  }

  @Override
  public void initLayout() {
    super.initLayout();

    mHeaderView.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.back();
      }

      @Override
      public void onRightIconClick() {

      }
    });

    mLoanInformationLl.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // go to loan details screen
      }
    });

    fakeData();
  }

  private void fakeData() {
    DocumentDTO documentDTO = new DocumentDTO();
    documentDTO.setDocumentName("Chief Planner");
    documentDTO.setDocumentAction("Pending Owners Actions");
    documentDTO.setIsMissing(true);

    for (int i = 0; i < 6; i++) {
      mListDocument.add(documentDTO);
    }

    mDocumentAdapter = new DocumentAdapter(getViewContext(), mListDocument);
    mDocumentRv.setAdapter(mDocumentAdapter);
    mDocumentRv.setLayoutManager(new GridLayoutManager(getViewContext(), 1));
    mDocumentRv.setHasFixedSize(true);
    mDocumentRv.setNestedScrollingEnabled(false);

    mLoanStatusView.setBackground(ContextCompat.getDrawable(getViewContext(), R.drawable.yellow_circle_bg));
  }
}
