package app.positiveculture.com.agent.screen.seller.viewparties;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.User;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ViewPartiesAdapter
 * Created by HaiLS on 07/09/2017.
 */

public class ViewPartiesAdapter extends RecyclerView.Adapter<ViewPartiesAdapter.ViewPartiesViewHolder> {

  private List<User> listUser;
  private OnItemPartiesClickListener mOnItemPartiesClickListener;
  private boolean typeParties = false;
  private User mUser;
  private Context mContext;

  public void setOnItemPartiesClickListener(OnItemPartiesClickListener mOnItemPartiesClickListener) {
    this.mOnItemPartiesClickListener = mOnItemPartiesClickListener;
  }

  public ViewPartiesAdapter(List<User> listUser, boolean typeParties, User user) {
    this.listUser = listUser;
    this.typeParties = typeParties;
    this.mUser = user;
  }

  @Override
  public ViewPartiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    View view = LayoutInflater.from(mContext).inflate(R.layout.item_owner_buyer, parent, false);
    return new ViewPartiesViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewPartiesViewHolder holder, int position) {
    final User user = listUser.get(position);
    if (mUser != null && mUser.getId() == user.getId()) {
      holder.mNextIv.setVisibility(View.GONE);
    }
    if (user.getAvatar() != null && user.getAvatar().getImgSmall() != null) {
      ViewUtils.loadImageWithFresco(holder.userAvatarIv, user.getAvatar().getImgSmall());
    }

    if (user.getUsers() != null) {
      holder.userNameTv.setText(user.getUsers().getFullName());
    }

    if (typeParties) {
      String text = mContext.getString(R.string.owner);
      if (user instanceof AgentDTO) {
        text += " " + mContext.getString(R.string.agent);
      }
      holder.userTypeTv.setText(text);

    } else {
      String text = mContext.getString(R.string.buyer);
      if (user instanceof AgentDTO) {
        text += " " + mContext.getString(R.string.agent);
      }
      holder.userTypeTv.setText(text);
    }
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (mOnItemPartiesClickListener != null) {
          mOnItemPartiesClickListener.onItemPartiesClick(user);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return listUser.size();
  }

  class ViewPartiesViewHolder extends RecyclerView.ViewHolder {
    @BindView(R2.id.user_avatar_iv)
    SimpleDraweeView userAvatarIv;
    @BindView(R2.id.user_name_tv)
    TextView userNameTv;
    @BindView(R2.id.user_type_tv)
    TextView userTypeTv;
    @BindView(R2.id.next_iv)
    View mNextIv;

    public ViewPartiesViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public interface OnItemPartiesClickListener {
    void onItemPartiesClick(User user);
  }
}
