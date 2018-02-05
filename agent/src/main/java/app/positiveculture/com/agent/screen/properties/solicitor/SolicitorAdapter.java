package app.positiveculture.com.agent.screen.properties.solicitor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.data.response.dto.SolicitorDTO;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HaiLS on 26/10/2017.
 */

public class SolicitorAdapter extends RecyclerView.Adapter<SolicitorAdapter.SolicitorViewHolder> {

  private ArrayList<SolicitorDTO> mSolicitorList = new ArrayList<>();
  private OnSolicitorSelect mOnSolicitorSelect;
  private int mPreviousPosition = -1;
  private int mCurrentPosition = -1;
  private SolicitorDTO mSelectedSolicitor;

  public SolicitorAdapter(ArrayList<SolicitorDTO> solicitorList) {
    mSolicitorList = solicitorList;
  }

  public void setSelectedSolicitor(SolicitorDTO solicitor) {
    mSelectedSolicitor = solicitor;
  }

  @Override
  public SolicitorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_solicitor, parent, false);
    return new SolicitorViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final SolicitorViewHolder holder, int position) {
    final SolicitorDTO solicitorDTO = mSolicitorList.get(position);

    holder.mSolicitorNameTv.setText(solicitorDTO.getSolicitorName());
    holder.mSolicitorAddressTv.setText(solicitorDTO.getSolicitorAddress());

    if (mSelectedSolicitor != null && solicitorDTO.getId() == mSelectedSolicitor.getId()) {
      holder.mSelectedSolicitorIv.setImageResource(R.drawable.ic_radio_selected);
      holder.mSelectedSolicitorIv.setSelected(true);
    } else {
      holder.mSelectedSolicitorIv.setImageResource(R.drawable.ic_radio_default);
      holder.mSelectedSolicitorIv.setSelected(false);
    }

    holder.mSelectedSolicitorIv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        holder.mSelectedSolicitorIv.setSelected(!holder.mSelectedSolicitorIv.isSelected());
        if (holder.mSelectedSolicitorIv.isSelected()) {
          mSelectedSolicitor = solicitorDTO;
        } else {
          mSelectedSolicitor = null;
        }
        mOnSolicitorSelect.onSolicitorSelect(mSelectedSolicitor);
        notifyDataSetChanged();
      }
    });
  }

  public void loadMore(List<SolicitorDTO> data) {
    if (data == null) return;
    mSolicitorList.addAll(data);
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return mSolicitorList != null ? mSolicitorList.size() : 0;
  }

  public void setOnSolicitorClick(OnSolicitorSelect onSolicitorSelect) {
    mOnSolicitorSelect = onSolicitorSelect;
  }

  class SolicitorViewHolder extends RecyclerView.ViewHolder {
    @BindView(R2.id.item_solicitor_name_tv)
    TextView mSolicitorNameTv;
    @BindView(R2.id.item_solicitor_address_tv)
    TextView mSolicitorAddressTv;
    @BindView(R2.id.item_solicitor_selected_iv)
    ImageView mSelectedSolicitorIv;

    public SolicitorViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public interface OnSolicitorSelect {
    void onSolicitorSelect(SolicitorDTO solicitorDTO);
  }
}
