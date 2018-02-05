package app.positiveculture.com.agent.screen.properties.property;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.utils.NumberUtils;
import app.positiveculture.com.agent.utils.PropertyUtils;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import butterknife.BindView;
import butterknife.ButterKnife;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * PropertyAdapter
 * Created by hieudt on 8/16/2017.
 */

public class PropertyAdapter extends RecyclerSwipeAdapter<PropertyAdapter.PropertyViewHolder> {

  private List<PropertyDTO> mListProperty;
  private OnPropertyClickListener mOnPropertyClickListener;
  private Context mContext;
  private boolean isSwipeable;

  public PropertyAdapter(List<PropertyDTO> mListProperty) {
    this.mListProperty = mListProperty;
  }

  public void setSwipeable(boolean swipeable) {
    isSwipeable = swipeable;
  }

  public void setOnPropertyClickListener(OnPropertyClickListener mOnPropertyClickListener) {
    this.mOnPropertyClickListener = mOnPropertyClickListener;
  }

  @Override
  public PropertyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_property_agent, parent, false);
    return new PropertyViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(final PropertyViewHolder holder, final int position) {
    final PropertyDTO property = mListProperty.get(position);
    ViewUtils.loadImageWithFresco(holder.thumbPropertyIv, property.getmFeatrureImage().getImgMedium());
    holder.namePropertyTv.setText(property.getmDisplayOne());
    holder.locationPropertyTv.setText(property.getmDisplayTwo());
    if (property.getmAgent() != null && property.getmAgent().getUsers() != null) {
      holder.agentPropertyTv.setText(property.getmAgent().getUsers().getFullName());
    }
    holder.pricePropertyTv.setText(NumberUtils.formatNumber(property.getmPrice()));
    String acreage = NumberUtils.formatNumber(property.getmFloorSizeBuilt()) + " " + property.getmFloorSizeUnit();
    holder.acreagePropertyTv.setText(acreage);
    if (property.getmStatus() != null) {
      holder.statusPropertyTv.setText(PropertyUtils.setStatus(property.getmStatus()));
    }

    holder.mSurfaceView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mItemManger.closeItem(position);
        if (mOnPropertyClickListener != null) {
          mOnPropertyClickListener.onItemPropertyClick(property);
        }
      }
    });

    holder.thumbPropertyIv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {
        holder.thumbPropertyIv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        int width = holder.thumbPropertyIv.getWidth();
        ViewGroup.LayoutParams params = holder.mRemoveRl.getLayoutParams();
        params.width = width;
        holder.mRemoveRl.setLayoutParams(params);
      }
    });

    if (isSwipeable) {
      holder.mItem.setShowMode(SwipeLayout.ShowMode.PullOut);
      mItemManger.bindView(holder.itemView, position);
      holder.mItem.addSwipeListener(new SimpleSwipeListener() {
        @Override
        public void onOpen(SwipeLayout layout) {
          mItemManger.closeAllExcept(holder.mItem);
        }
      });

      holder.mRemoveIv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          DialogUtils.showDialog(mContext, mContext.getString(R.string.delete), mContext.getString(R.string.delete_property_mes),
                  new CustomDialog.OnConfirmSelected() {
                    @Override
                    public void onConfirmSelected() {
                      if (mOnPropertyClickListener != null) {
                        mOnPropertyClickListener.onDeletePropertyClick(property, position);
                      }
                    }
                  }, new CustomDialog.OnCancelSelected() {
                    @Override
                    public void onCancelSelected() {
                      mItemManger.closeItem(position);
                    }
                  });
        }
      });
    } else {
      holder.mItem.setSwipeEnabled(false);
    }
  }

  @Override
  public int getItemCount() {
    return (mListProperty != null) ? mListProperty.size() : 0;
  }

  @Override
  public int getSwipeLayoutResourceId(int position) {
    return R.id.item_property_sw;
  }

  public class PropertyViewHolder extends RecyclerView.ViewHolder {
    @BindView(R2.id.item_property_sw)
    SwipeLayout mItem;
    @BindView(R2.id.item_surface_ll)
    LinearLayout mSurfaceView;
    @BindView(R2.id.property_delete_rl)
    RelativeLayout mRemoveRl;
    @BindView(R2.id.remove_property_iv)
    ImageView mRemoveIv;
    @BindView(R2.id.item_property_thumb_iv)
    SimpleDraweeView thumbPropertyIv;
    @BindView(R2.id.item_property_name_tv)
    TextView namePropertyTv;
    @BindView(R2.id.item_property_location_tv)
    TextView locationPropertyTv;
    @BindView(R2.id.item_property_agent_tv)
    TextView agentPropertyTv;
    @BindView(R2.id.item_property_agent_price_tv)
    TextView pricePropertyTv;
    @BindView(R2.id.item_property_agent_acreage_tv)
    TextView acreagePropertyTv;
    @BindView(R2.id.item_property_agent_status_tv)
    TextView statusPropertyTv;

    private PropertyViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

  }

  public interface OnPropertyClickListener {
    void onItemPropertyClick(PropertyDTO propertyDTO);

    void onDeletePropertyClick(PropertyDTO propertyDTO, int position);
  }
}
