package app.positiveculture.com.agent.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.gemvietnam.utils.DeviceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Media utils
 * Created by NEO on 1/5/2017.
 */

public class MediaUtils {
  private static final String FILE_PROVIDER_SUFFIX = ".fileprovider";

  public static File createImageFile(Context context) throws IOException {
    // Create an image file name
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String imageFileName = "JPEG_" + timeStamp + "_";
    File storageDir = new File(getAppFolder(context));

    return File.createTempFile(
        imageFileName,  /* prefix */
        ".jpg",         /* suffix */
        storageDir      /* directory */
    );
  }

  private static String getAppFolder(Context context) {
    String appPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + context.getPackageName();
    File file = new File(appPath);
    if (!file.exists())
      file.mkdir();
    return file.getAbsolutePath();
  }

  public static void dispatchTakePictureIntent(Activity activity, int requestCode, Uri destination) {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    // Ensure that there's a camera activity to handle the intent
    if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
      if (destination != null) {
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, destination);
        activity.startActivityForResult(takePictureIntent, requestCode);
      }
    }
  }

  public static Bitmap decodeUri(Activity activity, Uri selectedImage) throws FileNotFoundException {

    // Decode image size
    BitmapFactory.Options o = new BitmapFactory.Options();
    o.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(activity.getContentResolver().openInputStream(selectedImage), null, o);

    // The new size we want to scale to
    final int REQUIRED_SIZE = DeviceUtils.getDeviceSize(activity).x;

    // Find the correct scale value. It should be the power of 2.
    int width_tmp = o.outWidth, height_tmp = o.outHeight;
    int scale = 1;
    while (true) {
      if (width_tmp / 2 < REQUIRED_SIZE
          || height_tmp / 2 < REQUIRED_SIZE) {
        break;
      }
      width_tmp /= 2;
      height_tmp /= 2;
      scale *= 2;
    }

    // Decode with inSampleSize
    BitmapFactory.Options o2 = new BitmapFactory.Options();
    o2.inSampleSize = scale;
    return BitmapFactory.decodeStream(activity.getContentResolver().openInputStream(selectedImage), null, o2);

  }

  public static String getFileExtension(String filePath) {
    return filePath.substring(filePath.lastIndexOf(".") + 1);
  }

  /**
   * helper to retrieve the path of an image URI
   */
  public static String getPath(Activity activity, Uri uri) {
    /**
    // just some safety built in
    if( uri == null ) {
      // TODO perform some logging or show user feedback
      return null;
    }
    // try to retrieve the image from the media store first
    // this will only work for images selected from gallery
    String[] projection = { MediaStore.Images.Media.DATA };
    Cursor cursor = context.managedQuery(uri, projection, null, null, null);
    if( cursor != null ){
      int column_index = cursor
              .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      cursor.moveToFirst();
      String path = cursor.getString(column_index);
      cursor.close();
      return path;
    }
    // this is our fallback here
    return uri.getPath();
     */

    if (uri.getScheme().equalsIgnoreCase("file")) {
      return uri.getPath();
    }

    String res = null;
    String[] proj = { MediaStore.Images.Media.DATA };
    Cursor cursor = activity.getContentResolver().query(uri, proj, null, null, null);
    if(cursor != null && cursor.moveToFirst()){
      int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      res = cursor.getString(column_index);
    }

    if (cursor != null) cursor.close();
    return res;
  }

  public static void dispatchTakePictureIntentWithNougat(Activity activity, int takePictureRequestCode, String captureImagePath) {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    // Ensure that there's a camera activity to handle the intent
    if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
      if (captureImagePath != null) {
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, getUriNougat(captureImagePath, activity));
        activity.startActivityForResult(takePictureIntent, takePictureRequestCode);
      }
    }
  }

  private static Uri getUriNougat(String captureImagePath, Activity activity) {
    File photoFile = new File(captureImagePath);

    // Continue only if the File was successfully created
    return FileProvider.getUriForFile(activity.getApplicationContext(),
            activity.getPackageName() + FILE_PROVIDER_SUFFIX,
            photoFile);
  }
}
