package app.positiveculture.com.agent.screen.documents;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.dto.Documents;
import app.positiveculture.com.data.response.dto.DocumentDTO;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ItemDocumentsAdapter
 * Created by hieudt on 9/18/2017.
 */

public class ItemDocumentsAdapter extends RecyclerView.Adapter<ItemDocumentsAdapter.DocumentsViewHolder> {

  private List<Documents> mListDocument;
  private OnDocumentClickListener mOnDocumentClickListener;

  public ItemDocumentsAdapter(List<Documents> mListDocument) {
    this.mListDocument = mListDocument;
  }

  public void setmOnDocumentClickListener(OnDocumentClickListener mOnDocumentClickListener) {
    this.mOnDocumentClickListener = mOnDocumentClickListener;
  }

  @Override
  public DocumentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_documents, parent, false);
    return new DocumentsViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(DocumentsViewHolder holder, final int position) {
    Documents document = mListDocument.get(position);
    holder.mTitleDocuments.setText(document.getmTitle());
    holder.mNextDocuments.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (mOnDocumentClickListener != null) mOnDocumentClickListener.onDocumentClick(position);
      }
    });
    if (position == mListDocument.size() - 1) {
      holder.mBottomLine.setVisibility(View.GONE);
    } else {
      holder.mBottomLine.setVisibility(View.VISIBLE);
    }
  }

  @Override
  public int getItemCount() {
    return mListDocument != null ? mListDocument.size() : 0;
  }

  public class DocumentsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R2.id.title_documents_tv)
    TextView mTitleDocuments;
    @BindView(R2.id.next_documents_iv)
    ImageView mNextDocuments;
    @BindView(R2.id.bottom_line_documents)
    View mBottomLine;

    public DocumentsViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public interface OnDocumentClickListener {
    void onDocumentClick(int position);
  }
}
