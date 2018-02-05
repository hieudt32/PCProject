package app.positiveculture.com.agent.screen.properties.detail.photofullview;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.response.dto.FileDTO;

/**
 * PhotoFullViewAdapter
 * Created by hieudt on 10/27/2017.
 */

public class PhotoFullViewAdapter extends PagerAdapter {

  private List<FileDTO> mListPhoto;

  public PhotoFullViewAdapter(List<FileDTO> listPhoto) {
    this.mListPhoto = listPhoto;
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    Context context = container.getContext();
    View itemView = LayoutInflater.from(context).inflate(R.layout.item_photo_full_view, container, false);
    final SimpleDraweeView imageView = (SimpleDraweeView) itemView.findViewById(R.id.item_photo_iv);
    imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

      @Override
      public void onGlobalLayout() {
        imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        imageView.setAspectRatio((float) imageView.getWidth() / imageView.getHeight());

      }
    });
    ViewUtils.loadImageWithFresco(imageView, mListPhoto.get(position).getImgLarge());
    container.addView(itemView);
    return itemView;
  }

  @Override
  public int getCount() {
    return mListPhoto != null ? mListPhoto.size() : 0;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }
}
