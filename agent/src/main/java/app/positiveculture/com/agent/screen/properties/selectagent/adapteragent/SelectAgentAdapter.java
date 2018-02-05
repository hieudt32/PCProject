package app.positiveculture.com.agent.screen.properties.selectagent.adapteragent;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.TwoTextViewCustom;
import app.positiveculture.com.data.response.dto.AgentDTO;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * SelectAgentAdapter
 * Created by Chinh on 9/5/2017.
 */

public class SelectAgentAdapter extends RecyclerView.Adapter<SelectAgentAdapter.SelectAgentViewHolder> {
  private Context mContext;
  private ArrayList<AgentDTO> mAgentList;
  private OnAgentClickListener mOnAgentClickListener;
  private AgentDTO mSelectedAgent;

  public SelectAgentAdapter(ArrayList<AgentDTO> mAgentList) {
    this.mAgentList = mAgentList;
  }

  @Override
  public SelectAgentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member, parent, false);
    return new SelectAgentViewHolder(itemView);
  }

  public void onBindViewHolder(final SelectAgentViewHolder holder, final int position) {
    final AgentDTO agentDTO = mAgentList.get(position);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      holder.mItem.setBackgroundColor(mContext.getColor(R.color.white));
    } else {
      holder.mItem.setBackgroundColor(mContext.getResources().getColor(R.color.white));
    }

    String avatarUrl;
    if (agentDTO.getAvatar() != null) {
      avatarUrl = agentDTO.getAvatar().getImgSmall();
    } else {
      Context context = holder.mAvatar.getContext();
      int id = context.getResources().getIdentifier("ic_avatar", "drawable", context.getPackageName());
      avatarUrl = String.valueOf(id);
    }
    Glide.with(mContext)
            .load(avatarUrl)
            .asBitmap()
            .centerCrop()
            .placeholder(R.drawable.ic_avatar)
            .into(new BitmapImageViewTarget(holder.mAvatar) {
              @Override
              protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.mAvatar.setImageDrawable(circularBitmapDrawable);
              }
            });

    holder.mInformation.setTitleItem(agentDTO.getUsers().getFullName());
    holder.mInformation.setContent(agentDTO.getCEA().getCEANumber());

    if (mSelectedAgent != null && agentDTO.getId() == mSelectedAgent.getId()) {
      holder.mSelected.setImageResource(R.drawable.ic_radio_selected);
    } else {
      holder.mSelected.setImageResource(R.drawable.ic_radio_default);
    }

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (mOnAgentClickListener != null) {
          if (mSelectedAgent != null && mSelectedAgent.getId() == agentDTO.getId()) {
            mSelectedAgent = null;
          } else {
            mSelectedAgent = agentDTO;
          }
          mOnAgentClickListener.onAgentClick(mSelectedAgent);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return mAgentList != null ? mAgentList.size() : 0;
  }

  public void loadMore(List<AgentDTO> data) {
    if (data == null) return;
    mAgentList.addAll(data);
    notifyDataSetChanged();
  }

  public void setOnAgentClickListener(OnAgentClickListener onAgentClickListener) {
    this.mOnAgentClickListener = onAgentClickListener;
  }

  public void setSelectedAgent(AgentDTO agent) {
    mSelectedAgent = agent;
  }

  public class SelectAgentViewHolder extends RecyclerView.ViewHolder {
    @BindView(R2.id.avatar_member)
    SimpleDraweeView mAvatar;
    @BindView(R2.id.member_selected_iv)
    ImageView mSelected;
    @BindView(R2.id.information_member)
    TwoTextViewCustom mInformation;
    @BindView(R2.id.item_member)
    RelativeLayout mItem;

    public SelectAgentViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public interface OnAgentClickListener {
    void onAgentClick(AgentDTO agentDTO);
  }
}
