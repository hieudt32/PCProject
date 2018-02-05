package app.positiveculture.com.agent.screen.seller.otp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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
import app.positiveculture.com.data.enumdata.MemberType;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.User;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * OTPSignaturesAdapter
 * Created by HaiLS on 07/09/2017.
 */

public class OTPSignaturesAdapter extends RecyclerView.Adapter<OTPSignaturesAdapter.SignOTPViewHolder> {

  private List<MemberDTO> mListUser = new ArrayList<>();
  private Context mContext;

  public OTPSignaturesAdapter(List<MemberDTO> listUser) {
    this.mListUser = listUser;
  }

  @Override
  public SignOTPViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_otp_item, parent, false);
    mContext = parent.getContext();
    return new SignOTPViewHolder(view);
  }

  @Override
  public void onBindViewHolder(SignOTPViewHolder holder, int position) {
    MemberDTO memberDTO = mListUser.get(position);

    if (memberDTO.isSelected()) {
      holder.mStatusView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.blue_circle_bg));
    } else {
      holder.mStatusView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.yellow_circle_bg));
    }

    holder.mNameTv.setText(memberDTO.getUsers().getFullName());

    if (memberDTO.getTypeInOTP() == MemberType.seller) {
      holder.mTypeTv.setText(mContext.getString(R.string.owner));
      if ((position < mListUser.size() - 1) && mListUser.get(position + 1).getTypeInOTP() == MemberType.buyer) {
        holder.mLineSeparator.setVisibility(View.VISIBLE);
      } else {
        holder.mLineSeparator.setVisibility(View.GONE);
      }
    } else {
      holder.mTypeTv.setText(mContext.getText(R.string.buyer));
    }


  }

  @Override
  public int getItemCount() {
    return mListUser.size();
  }

  class SignOTPViewHolder extends RecyclerView.ViewHolder {
    @BindView(R2.id.otp_item_status_view)
    View mStatusView;
    @BindView(R2.id.otp_item_name_tv)
    TextView mNameTv;
    @BindView(R2.id.otp_item_type_tv)
    TextView mTypeTv;
    @BindView(R2.id.otp_item_line_separator)
    View mLineSeparator;

    public SignOTPViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
