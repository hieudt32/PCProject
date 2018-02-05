package app.positiveculture.com.agent.screen.selectowners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;

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
 * SelectOwnersAdapter
 * Created by hieudt on 8/25/2017.
 */

public class SelectOwnersAdapter extends RecyclerView.Adapter<SelectOwnersAdapter.SelectOwnersViewHolder> {
  private List<MemberDTO> mListOwners;
  private OnOwnerClickListener mOwnerClickListener;
  private Context mContext;

  public void setOwnerClickListener(OnOwnerClickListener mOwnerClickListener) {
    this.mOwnerClickListener = mOwnerClickListener;
  }

  public SelectOwnersAdapter(List<MemberDTO> mListOwners) {
    this.mListOwners = mListOwners;
  }

  @Override
  public SelectOwnersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member, parent, false);
    return new SelectOwnersViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(SelectOwnersViewHolder holder, final int position) {
    final MemberDTO owner = mListOwners.get(position);
    if (owner.getContractStatus() != null && owner.getContractStatus().compareTo(Constant.CONTRACT_STATUS.PENDING) == 0) {
      holder.mAvatar.setVisibility(View.GONE);
      holder.mInformation.setContent(owner.getmIdNumber() + " " + Constant.CONTRACT_STATUS.PENDING_APPROVAL);
    } else {
      holder.mAvatar.setVisibility(View.VISIBLE);
      if (owner.getProperties() > 1) {
        holder.mInformation.setContent(owner.getProperties() + " " + mContext.getString(R.string.properties));
      } else {
        holder.mInformation.setContent(owner.getProperties() + " " + mContext.getString(R.string.property));
      }
    }

    holder.mInformation.setTitleItem(owner.getUsers().getFullName());

    if (owner.getAvatar() != null && owner.getAvatar().getImgSmall() != null) {
      ViewUtils.loadImageWithFresco(holder.mAvatar, owner.getAvatar().getImgSmall());
    } else {
      holder.mAvatar.setImageResource(R.drawable.ic_avatar);
    }

    if (!owner.isSelected()) {
      holder.mSelected.setImageResource(R.drawable.ic_radio_default);
    } else {
      holder.mSelected.setImageResource(R.drawable.ic_radio_selected);
    }

    holder.mItem.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (mOwnerClickListener != null) {
          mOwnerClickListener.onOwnerClick(position);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return mListOwners != null ? mListOwners.size() : 0;
  }

  public class SelectOwnersViewHolder extends RecyclerView.ViewHolder {

    @BindView(R2.id.avatar_member)
    SimpleDraweeView mAvatar;
    @BindView(R2.id.member_selected_iv)
    ImageView mSelected;
    @BindView(R2.id.information_member)
    TwoTextViewCustom mInformation;
    @BindView(R2.id.item_member)
    RelativeLayout mItem;

    public SelectOwnersViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public interface OnOwnerClickListener {
    void onOwnerClick(int position);
  }
}
