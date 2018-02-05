package app.positiveculture.com.agent.screen.documents;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.BindView;
import customview.CustomHeaderView;

/**
 * The Documents Fragment
 */
public class DocumentsFragment extends ViewFragment<DocumentsContract.Presenter> implements DocumentsContract.View, ItemDocumentsAdapter.OnDocumentClickListener {

  @BindView(R2.id.list_documents_rv)
  RecyclerView mListDocuments;
  @BindView(R2.id.documents_hd)
  CustomHeaderView mHeader;
  ItemDocumentsAdapter mAdapter;

  public static DocumentsFragment getInstance() {
    return new DocumentsFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_documents;
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
  public void bindData(ItemDocumentsAdapter Adapter) {
    this.mAdapter = Adapter;
    mListDocuments.setAdapter(mAdapter);
    mListDocuments.setLayoutManager(new GridLayoutManager(getViewContext(), 1));
    mAdapter.setmOnDocumentClickListener(this);
  }

  @Override
  public void onDocumentClick(int position) {
    mPresenter.onDocumentClicked(position);
  }
}
