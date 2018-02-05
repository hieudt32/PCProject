package app.positiveculture.com.agent.screen.summary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.TwoTextViewCustom;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.response.dto.MemberDTO;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * AdapterBuyerSummary
 * Created by hieudt on 9/26/2017.
 */

public class AdapterBuyerSummary extends RecyclerSwipeAdapter<AdapterBuyerSummary.BuyerSummaryViewHolder> {

  private List<MemberDTO> mListBuyer;
  private Context mContext;
  private OnRemoveItemListener mOnRemoveItemListener;

  public AdapterBuyerSummary(List<MemberDTO> memberDTOs) {
    this.mListBuyer = memberDTOs;
  }

  @Override
  public BuyerSummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_buyer_summary, parent, false);
    return new BuyerSummaryViewHolder(itemView);
  }

  public void setOnRemoveItemListener(OnRemoveItemListener mOnRemoveItemListener) {
    this.mOnRemoveItemListener = mOnRemoveItemListener;
  }

  @Override
  public void onBindViewHolder(final BuyerSummaryViewHolder holder, int position) {
    final MemberDTO itemMember = mListBuyer.get(position);
    if (itemMember.getAvatar() != null) {
      if (itemMember.getAvatar().getImgSmall() != null){
        ViewUtils.loadImageWithFresco(holder.mAvatarOwner, itemMember.getAvatar().getImgSmall());
      }else {
        holder.mAvatarOwner.setImageResource(R.drawable.ic_avatar);
      }
    } else {
      holder.mAvatarOwner.setImageResource(R.drawable.ic_avatar);
    }
    holder.mProfileOwner.setTitleItem(itemMember.getUsers().getFullName());
    holder.mProfileOwner.setContent(mContext.getResources().getString(R.string.buyer_side));
    holder.mItem.setShowMode(SwipeLayout.ShowMode.PullOut);
    mItemManger.bindView(holder.itemView, position);

    holder.mItem.addSwipeListener(new SimpleSwipeListener() {
      @Override
      public void onOpen(SwipeLayout layout) {
        mItemManger.closeAllExcept(holder.mItem);
      }
    });

    holder.mBuyerSummaryDeleteRl.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (mOnRemoveItemListener != null) {
          mOnRemoveItemListener.onRemove(itemMember);
          mItemManger.closeAllItems();
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return mListBuyer != null ? mListBuyer.size() : 0;
  }

  @Override
  public int getSwipeLayoutResourceId(int position) {
    return R.id.item_buyer_summary_sw;
  }

  public class BuyerSummaryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R2.id.item_buyer_summary_sw)
    SwipeLayout mItem;
    @BindView(R2.id.remove_owner_iv)
    ImageView mRemoveOwner;
    @BindView(R2.id.avatar_owner)
    SimpleDraweeView mAvatarOwner;
    @BindView(R2.id.profile_owner)
    TwoTextViewCustom mProfileOwner;
    @BindView(R2.id.next_item_buyer)
    ImageView mNext;
    @BindView(R2.id.buyer_summary_delete_rl)
    RelativeLayout mBuyerSummaryDeleteRl;

    public BuyerSummaryViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public interface OnRemoveItemListener {
    void onRemove(MemberDTO memberDTO);
  }
}
