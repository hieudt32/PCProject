package com.positiveculture.app.utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.positiveculture.app.R;

/**
 * Image utils
 * Created by neo on 8/1/17.
 */

public class ImageUtils {
  public static void loadImageWithFresco(Context context, Integer stagemetroServiceHeight,
                                         Integer stagemetroServiceWidth, SimpleDraweeView coverIv, String serviceImageUrl) {
    int mCurrentWidth = (DeviceUtils
        .getScreenSize((Activity) context).x
        - context.getResources().getDimensionPixelSize(R.dimen.item_space_grid));

    int height = stagemetroServiceHeight
        * mCurrentWidth / stagemetroServiceWidth;

    ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(serviceImageUrl))
        .setResizeOptions(new ResizeOptions(mCurrentWidth, height))
        .build();

    DraweeController controller = Fresco.newDraweeControllerBuilder()
        .setOldController(coverIv.getController())
        .setImageRequest(request)
        .build();
    coverIv.setController(controller);
  }

  public static void loadAvatarWithFresco(Integer stagemetroServiceHeight,
                                          Integer stagemetroServiceWidth, SimpleDraweeView coverIv, String serviceImageUrl) {

    ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(serviceImageUrl))
        .setResizeOptions(new ResizeOptions(stagemetroServiceWidth, stagemetroServiceHeight))
        .build();

    DraweeController controller = Fresco.newDraweeControllerBuilder()
        .setOldController(coverIv.getController())
        .setImageRequest(request)
        .build();
    coverIv.setController(controller);
  }

  public static void loadImageWithFresco(SimpleDraweeView coverIv, String serviceImageUrl) {

    ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(serviceImageUrl))
        .build();

    DraweeController controller = Fresco.newDraweeControllerBuilder()
        .setOldController(coverIv.getController())
        .setImageRequest(request)
        .build();
    coverIv.setController(controller);
  }


  public static void loadAvatarWithFresco(SimpleDraweeView coverIv, String serviceImageUrl) {

    ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(serviceImageUrl))
        .setResizeOptions(new ResizeOptions(200, 200))
        .build();

    DraweeController controller = Fresco.newDraweeControllerBuilder()
        .setOldController(coverIv.getController())
        .setImageRequest(request)
        .build();
    coverIv.setController(controller);
  }

  public static void loadAvatarWithFresco(SimpleDraweeView coverIv, Uri serviceImageUrl) {

    ImageRequest request = ImageRequestBuilder.newBuilderWithSource(serviceImageUrl)
        .setResizeOptions(new ResizeOptions(200, 200))
        .build();

    DraweeController controller = Fresco.newDraweeControllerBuilder()
        .setOldController(coverIv.getController())
        .setImageRequest(request)
        .build();
    coverIv.setController(controller);
  }
}
