package com.positiveculture.app.utils;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;


public class DeviceUtils {
  public static Point getScreenSize(Activity activity) {
    Display display = activity.getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    return size;
  }

  public static boolean isNougat() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
  }
}
