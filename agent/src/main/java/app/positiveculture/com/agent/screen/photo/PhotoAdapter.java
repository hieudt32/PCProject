package app.positiveculture.com.agent.screen.photo;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import app.positiveculture.com.agent.Constant;
import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.response.dto.FileDTO;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * PhotoAdapter
 * Created by hungdn on 8/23/2017.
 */

public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private Context mContext;
  private ArrayList<FileDTO> mPhotos = new ArrayList<>();
  private OnPhotoItemClickListener mOnPhotoItemClickListener;
  private boolean mIsEditing = false;
  private OnDragEventListener mOnDragEventListener;

  public PhotoAdapter(ArrayList<FileDTO> photos) {
    this.mPhotos = photos;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    View view;

    RecyclerView.ViewHolder holder = null;
    if (viewType == Constant.PHOTO_TYPE.TYPE_ADD_PHOTO) {
      view = LayoutInflater.from(mContext).inflate(R.layout.item_add_photo, parent, false);
      holder = new AddPhotoVH(view);
    } else if (viewType == Constant.PHOTO_TYPE.TYPE_PHOTO) {
      view = LayoutInflater.from(mContext).inflate(R.layout.item_photo, parent, false);
      holder = new PhotoVH(view);
    }
    return holder;
  }

  public void setOnPhotoItemClickListener(OnPhotoItemClickListener onPhotoItemClickListener) {
    mOnPhotoItemClickListener = onPhotoItemClickListener;
  }

  public boolean isEditing() {
    return mIsEditing;
  }

  public void setIsEditing(boolean isEditing) {
    mIsEditing = isEditing;
  }

  public void setOnDragListener(OnDragEventListener onDragEventListener) {
    mOnDragEventListener = onDragEventListener;
  }

  public ArrayList<FileDTO> getPhotoList() {
    return mPhotos;
  }

  @Override
  public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

    int height;

    if (position == 0) {
      height = 2 * mContext.getResources().getDimensionPixelSize(R.dimen.photo_height);
    } else {
      height = mContext.getResources().getDimensionPixelSize(R.dimen.photo_height);
    }

    if (holder instanceof PhotoVH) {
      FileDTO photo = mPhotos.get(position);

      RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
//      params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
      params.setMargins(5, 5, 5, 5);

      ((PhotoVH) holder).mPhotoItemRl.setLayoutParams(params);

      PhotoVH photoVH = (PhotoVH) holder;

      ViewUtils.loadImageWithFresco(photoVH.mPhotoItemIv, photo.getImgMedium());

      ((PhotoVH) holder).itemView.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
          if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN
                  && mOnDragEventListener != null) {
            mOnDragEventListener.onStartDrag(holder);
          }
          return false;
        }
      });

      if (mIsEditing) {
        ((PhotoVH) holder).mCloseIv.setVisibility(View.VISIBLE);
        ((PhotoVH) holder).mCloseIv.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            DialogUtils.showDialogOKCancel(
                    mContext,
                    mContext.getString(R.string.delete_photo_title),
                    mContext.getString(R.string.delete_photo_msg),
                    new CustomDialog.OnConfirmSelected() {
                      @Override
                      public void onConfirmSelected() {
                        mPhotos.remove(holder.getAdapterPosition());
                        notifyDataSetChanged();
                      }
                    },
                    new CustomDialog.OnCancelSelected() {
                      @Override
                      public void onCancelSelected() {

                      }
                    }
            );

          }
        });
      } else {
        ((PhotoVH) holder).mCloseIv.setVisibility(View.GONE);
      }
    } else if (holder instanceof AddPhotoVH) {
      RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
      params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
      params.setMargins(8, 8, 8, 8);

      ((AddPhotoVH) holder).mAddPhotoItemRl.setLayoutParams(params);

      ((AddPhotoVH) holder).itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if (mOnPhotoItemClickListener != null) {
            mOnPhotoItemClickListener.onAddNewPhoto();
          }
        }
      });
    }
  }

  @Override
  public int getItemCount() {
    if (mPhotos.size() >= 6) {
      return 6;
    }
    if (mIsEditing) {
      return mPhotos.size();
    }
    return mPhotos.size() + 1;
  }

  @Override
  public int getItemViewType(int position) {
    if (position >= 6 || mIsEditing) {
      return Constant.PHOTO_TYPE.TYPE_PHOTO;
    } else {
      if (position >= mPhotos.size()) {
        return Constant.PHOTO_TYPE.TYPE_ADD_PHOTO;
      } else {
        return Constant.PHOTO_TYPE.TYPE_PHOTO;
      }
    }
  }

  public class PhotoVH extends RecyclerView.ViewHolder {

    private SimpleDraweeView mPhotoItemIv;
    private ImageView mCloseIv;
    private RelativeLayout mPhotoItemRl;

    public PhotoVH(View itemView) {
      super(itemView);
      mPhotoItemIv = (SimpleDraweeView) itemView.findViewById(R.id.photo_iv);
      mCloseIv = (ImageView) itemView.findViewById(R.id.close_iv);
      mPhotoItemRl = (RelativeLayout) itemView.findViewById(R.id.photo_item_rl);
    }

  }

  public class AddPhotoVH extends RecyclerView.ViewHolder {

    private RelativeLayout mAddPhotoItemRl;

    public AddPhotoVH(View itemView) {
      super(itemView);
      mAddPhotoItemRl = (RelativeLayout) itemView.findViewById(R.id.add_photo_item_rl);
    }
  }

  public interface OnPhotoItemClickListener {
    void onAddNewPhoto();
  }

  public interface OnDragEventListener {
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
  }
}
