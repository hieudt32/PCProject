package app.positiveculture.com.user.screen.userconveyancing;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.screen.seller.conveyancing.DocumentAdapter;
import app.positiveculture.com.data.response.dto.DocumentDTO;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomHeaderView;

/**
 * The UserConveyancing Fragment
 */
public class UserConveyancingFragment extends ViewFragment<UserConveyancingContract.Presenter> implements UserConveyancingContract.View {

  @BindView(R2.id.conveyancing_sv)
  ScrollView mConveyancingSv;
  @BindView(R2.id.conveyancing_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.conveyancing_document_rv)
  RecyclerView mDocumentRv;
  @BindView(R2.id.conveyancing_loan_information_ll)
  LinearLayout mLoanInformationLl;
  @BindView(R2.id.conveyancing_loan_rg)
  RadioGroup mLoanRg;
  @BindView(R2.id.conveyancing_loan_yes_btn)
  RadioButton mLoanYesRb;
  @BindView(R2.id.conveyancing_loan_no_btn)
  RadioButton mLoanNoRb;
  @BindView(R2.id.conveyancing_select_bank_layout)
  RelativeLayout mSelectBankLayout;
  @BindView(R2.id.conveyancing_select_bank_tv)
  TextView mSelectBankTv;

  private DocumentAdapter mDocumentAdapter;
  private List<DocumentDTO> mListDocument = new ArrayList<>();

  @OnClick(R2.id.conveyancing_select_bank_layout)
  public void onSelectBankClick() {
    mPresenter.goToLoanDetails();
  }

  public static UserConveyancingFragment getInstance() {
    return new UserConveyancingFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_conveyancing;
  }

  @Override
  public void initLayout() {
    mLoanInformationLl.setVisibility(View.GONE);
    setupListeners();
    fakeData();
  }

  private void setupListeners() {
    mHeaderView.getLeftIconIv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.back();
      }
    });

    mLoanRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == mLoanYesRb.getId()) {
          mSelectBankLayout.setVisibility(View.VISIBLE);
          scrollDownToBottom(mConveyancingSv);
        } else if (checkedId == mLoanNoRb.getId()) {
          mSelectBankLayout.setVisibility(View.GONE);
        }
      }
    });
  }

  private void scrollDownToBottom(final ScrollView scrollView) {
    scrollView.post(new Runnable() {
      @Override
      public void run() {
        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
      }
    });
  }

  private void fakeData() {
    DocumentDTO documentDTO = new DocumentDTO();
    documentDTO.setDocumentName("Chief Planner");
    documentDTO.setDocumentAction("Pending Owners Actions");
    documentDTO.setIsMissing(true);

    for (int i = 0; i < 6; i++) {
      mListDocument.add(documentDTO);
    }

    setupDocumentRecyclerView();
  }

  private void setupDocumentRecyclerView() {
    mDocumentAdapter = new DocumentAdapter(getViewContext(), mListDocument);
    mDocumentRv.setAdapter(mDocumentAdapter);
    mDocumentRv.setLayoutManager(new GridLayoutManager(getViewContext(), 1));
    mDocumentRv.setHasFixedSize(true);
    mDocumentRv.setNestedScrollingEnabled(false);
  }

  @Override
  public void bindBankInfo(String bank) {
    mSelectBankTv.setText(bank);
    mSelectBankTv.setTextColor(getResources().getColor(R.color.tc_black87));
  }
}
