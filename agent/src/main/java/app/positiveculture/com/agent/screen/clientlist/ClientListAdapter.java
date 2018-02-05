package app.positiveculture.com.agent.screen.clientlist;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.Constant;
import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.TwoTextViewCustom;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.response.dto.MemberDTO;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HaiLS on 11/09/2017.
 */

public class ClientListAdapter extends RecyclerView.Adapter<ClientListAdapter.ClientListViewHolder> {

  private List<MemberDTO> mListClient = new ArrayList<>();
  private List<MemberDTO> mCopyList = new ArrayList<>();
  private OnClientClick mOnClientClick;
  private Context mContext;

  public ClientListAdapter(List<MemberDTO> mListClient) {
    this.mListClient = mListClient;
    this.mCopyList.addAll(mListClient);
  }

  public ClientListAdapter setOnClientClick(OnClientClick onClientClick) {
    mOnClientClick = onClientClick;
    return this;
  }

  @Override
  public ClientListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    View view = LayoutInflater.from(mContext).inflate(R.layout.item_member, parent, false);
    return new ClientListViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ClientListViewHolder holder, int position) {
    final MemberDTO memberDTO = mListClient.get(position);
    if (memberDTO.getContractStatus() != null && memberDTO.getContractStatus().compareTo(Constant.CONTRACT_STATUS.PENDING) == 0) {
      holder.mAvatar.setVisibility(View.GONE);
      holder.mInformation.setContent(memberDTO.getmIdNumber() + " " + Constant.CONTRACT_STATUS.PENDING_APPROVAL);
    } else {
      holder.mAvatar.setVisibility(View.VISIBLE);
      String text = String.valueOf(memberDTO.getProperties());
      if (memberDTO.getProperties() > 1) {
        text += " " + mContext.getString(R.string.properties);
        holder.mInformation.setContent(text);
      } else {
        text += " " + mContext.getString(R.string.property);
        holder.mInformation.setContent(text);
      }
    }
    holder.mInformation.setTitleItem(memberDTO.getUsers().getFullName());
    if (memberDTO.getAvatar() != null && memberDTO.getAvatar().getImgSmall() != null) {
      ViewUtils.loadImageWithFresco(holder.mAvatar, memberDTO.getAvatar().getImgSmall());
    } else {
      holder.mAvatar.setImageResource(R.drawable.ic_avatar);
    }

    holder.mNextIv.setImageResource(R.drawable.ic_next);

    holder.mLayout.setBackgroundColor(Color.WHITE);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (mOnClientClick != null) {
          mOnClientClick.onClientClick(memberDTO);
        }
      }
    });
  }

  public void filter(String query) {
    mListClient.clear();
    if (query.isEmpty()) {
      mListClient.addAll(mCopyList);
    } else {
      query = query.toLowerCase();
      for (MemberDTO memberDTO : mCopyList) {
        String name = memberDTO.getUsers().getFullName().toLowerCase();
        if (name.contains(query)) {
          mListClient.add(memberDTO);
        }
      }
    }
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return mListClient.size();
  }

  class ClientListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R2.id.avatar_member)
    SimpleDraweeView mAvatar;
    @BindView(R2.id.member_selected_iv)
    ImageView mNextIv;
    @BindView(R2.id.information_member)
    TwoTextViewCustom mInformation;
    @BindView(R2.id.item_member)
    RelativeLayout mLayout;

    public ClientListViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public interface OnClientClick {
    public void onClientClick(MemberDTO memberDTO);
  }

}
