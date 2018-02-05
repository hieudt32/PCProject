package app.positiveculture.com.agent.utils;

import android.content.Intent;
import android.net.Uri;

/**
 * DeviceUtils
 * Created by hieudt on 9/27/2017.
 */

public class DeviceUtils {
  public static void Call(String Number) {
    Intent callIntent = new Intent(Intent.ACTION_CALL);
    callIntent.setData(Uri.parse("tel:"+Number));

  }

  public static void SendMessage(String Number) {

  }

  public static void SendEmail(String Email) {

  }
}
