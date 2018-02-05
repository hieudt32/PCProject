package app.positiveculture.com.agent.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * BitmapUtils
 * Created by hieudt on 10/2/2017.
 */

public class BitmapUtils {
  public static File resizePhoto(Uri photoUri, String realImagePath, Activity activity, float scaleX, float scaleY) {
    try {
      Bitmap bitmap = MediaUtils.decodeUri(activity, photoUri);
      int width = (int) (bitmap.getWidth() * scaleX);
      int height = (int) (bitmap.getHeight() * scaleY);
      bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
      String parent = realImagePath.substring(0, realImagePath.lastIndexOf("/"));
      String newName = realImagePath.substring(realImagePath.lastIndexOf("/") + 1);
      if (!newName.contentEquals("half_")) {
        newName = "half_" + newName;
      }
      File newFile = new File(parent, newName);
      if (newFile.exists()) {
        return newFile;
      }
      FileOutputStream outputStream = new FileOutputStream(newFile);
      Bitmap.CompressFormat format;
      String ext = MediaUtils.getFileExtension(realImagePath);
      if ("jpg".equalsIgnoreCase(ext) || "jpeg".equalsIgnoreCase(ext)) {
        format = Bitmap.CompressFormat.JPEG;
      } else {
        format = Bitmap.CompressFormat.PNG;
      }
      bitmap.compress(format, 100, outputStream);
      bitmap.recycle();
      outputStream.flush();
      outputStream.close();
      return newFile;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String saveBitmap(Context context, Bitmap bitmap, Bitmap.CompressFormat format) {
    String path = FileUtils.getPathTempFilePNG(context);
    File file = new File(path);
    try {
      FileOutputStream outputStream = new FileOutputStream(file);
      bitmap.compress(format, 100, outputStream);
      bitmap.recycle();
      outputStream.flush();
      outputStream.close();
      return file.getAbsolutePath();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static Bitmap getBitmapFromView(View view) {
    Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(returnedBitmap);
    Drawable bgDrawable = view.getBackground();
    if (bgDrawable != null)
      bgDrawable.draw(canvas);
    else
      canvas.drawColor(Color.TRANSPARENT);
    view.draw(canvas);
    return returnedBitmap;
  }
}
