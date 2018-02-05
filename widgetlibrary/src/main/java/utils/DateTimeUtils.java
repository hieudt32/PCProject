package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * DateTimeUtils
 * Created by hieudt on 11/1/2017.
 */

public class DateTimeUtils {
  public static SimpleDateFormat dateFormatOne() {
    return new SimpleDateFormat("d MMM yyyy ", Locale.US);
  }

  public static SimpleDateFormat dateFormatTwo() {
    return new SimpleDateFormat("EE d MMMM yyyy ", Locale.US);
  }

  public static SimpleDateFormat dateFormatThree() {
    return new SimpleDateFormat("EE d MMMM yyyy ", Locale.US);
  }

  public static SimpleDateFormat timeFormatOne() {
    return new SimpleDateFormat("hh:mm a", Locale.US);
  }

  public static SimpleDateFormat dateTimeFormatOne() {
    return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
  }
  public static SimpleDateFormat dateTimeFormat() {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
  }


  public static SimpleDateFormat dateFormatFour() {
    return new SimpleDateFormat("yyyy-MM-dd", Locale.US);
  }

  public static String convertDateFormat(String date, SimpleDateFormat formatOld, SimpleDateFormat formatNew) {
    try {
      return formatNew.format(formatOld.parse(date));
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }
}
