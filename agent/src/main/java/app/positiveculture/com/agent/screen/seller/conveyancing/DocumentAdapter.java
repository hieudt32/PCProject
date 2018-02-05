package app.positiveculture.com.agent.screen.seller.conveyancing;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.data.response.dto.DocumentDTO;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HaiLS on 08/09/2017.
 */

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder> {

  private Context mContext;
  private List<DocumentDTO> mListDocument = new ArrayList<>();

  public DocumentAdapter(Context context, List<DocumentDTO> listDocument) {
    this.mContext = context;
    this.mListDocument = listDocument;
  }

  @Override
  public DocumentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document, parent, false);
    return new DocumentViewHolder(view);
  }

  @Override
  public void onBindViewHolder(DocumentViewHolder holder, int position) {
    DocumentDTO documentDTO = mListDocument.get(position);

    holder.mDocumentStatusView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.blue_circle_bg));

    holder.mDocumentNameTv.setText(documentDTO.getDocumentName());

    holder.mDocumentActionTv.setText(documentDTO.getDocumentAction());

    if (documentDTO.isMissing()) {
      holder.mDocumentStatusTv.setVisibility(View.VISIBLE);
    } else {
      holder.mDocumentStatusTv.setVisibility(View.GONE);
    }

    if (position == (mListDocument.size() - 1)) {
      holder.mUnderLine.setVisibility(View.GONE);
    }
  }

  @Override
  public int getItemCount() {
    return mListDocument.size();
  }

  class DocumentViewHolder extends RecyclerView.ViewHolder {
    @BindView(R2.id.document_status_view)
    View mDocumentStatusView;
    @BindView(R2.id.document_name)
    TextView mDocumentNameTv;
    @BindView(R2.id.document_action)
    TextView mDocumentActionTv;
    @BindView(R2.id.document_status_tv)
    TextView mDocumentStatusTv;
    @BindView(R2.id.document_under_line)
    View mUnderLine;

    public DocumentViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

}
