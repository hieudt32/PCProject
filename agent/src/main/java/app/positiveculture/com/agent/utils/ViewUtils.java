package app.positiveculture.com.agent.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.gemvietnam.utils.DeviceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import app.positiveculture.com.agent.R;

/**
 * ViewUtils
 * Created by hungdn on 8/22/2017.
 */

public class ViewUtils {

  // get path of image from gallery
  public static String getRealPathFromURI(Uri contentURI, Activity activity) {
    Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
    if (cursor == null) { // Source is Dropbox or other similar local file path
      return contentURI.getPath();
    } else {
      cursor.moveToFirst();
      int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
      return cursor.getString(idx);
    }
  }

  public static void rotateImage(Uri uri) {
    try {
      Bitmap bmp = BitmapFactory.decodeFile(uri.getPath());
      Matrix matrix = new Matrix();
      File img = new File(uri.getPath());
      ExifInterface exif = new ExifInterface(img.toString());
      int rotate = 0;
      int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
      if (rotation == ExifInterface.ORIENTATION_ROTATE_90) {
        rotate = 90;
      } else if (rotation == ExifInterface.ORIENTATION_ROTATE_180) {
        rotate = 180;
      } else if (rotation == ExifInterface.ORIENTATION_ROTATE_270) {
        rotate = 270;
      }
      if (rotate != 0)
        matrix.postRotate(rotate);
      bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
      File imgFile = new File(uri.getPath());
      bmp.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(imgFile));
      bmp.recycle();
    } catch (IOException | NullPointerException e) {
      e.getStackTrace();
    }
  }

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

  public static void loadImageWithFresco(SimpleDraweeView coverIv, Uri serviceImageUri) {

    ImageRequest request = ImageRequestBuilder.newBuilderWithSource(serviceImageUri)
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

  public static Uri getImageUri(Context inContext, Bitmap bitmapImage) {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
    String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), bitmapImage, "Title", null);
    return Uri.parse(path);
  }

  public static void dismissKeyboard(Activity context) {
    View view = context.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    context.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
  }

}
