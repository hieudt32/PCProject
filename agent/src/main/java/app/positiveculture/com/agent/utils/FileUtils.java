package app.positiveculture.com.agent.utils;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * FileUtils
 * Created by hungdn on 4/21/2017.
 */

public class FileUtils {
  public static String getPathTempFile(Context context) {
    Date currentDate = new Date(System.currentTimeMillis());
    return getAppImagePath(context) + "/" + currentDate.getTime() + ".JPG";
  }

  public static String getPathTempFilePNG(Context context) {
    Date currentDate = new Date(System.currentTimeMillis());
    return getAppImagePath(context) + "/" + currentDate.getTime() + ".PNG";
  }

  private static String getAppImagePath(Context context) {
    return context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
  }

  public static String readAssetFile(Context context, String filePath) {
    StringBuilder buf = new StringBuilder();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(
              new InputStreamReader(context.getAssets().open(filePath)));

      // do reading, usually loop until end of file reading
      String line;
      while ((line = reader.readLine()) != null) {
        buf.append(line);

      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return buf.toString();
  }

}
