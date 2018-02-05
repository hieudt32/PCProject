package app.positiveculture.com.agent.utils;

import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import app.positiveculture.com.agent.Constant;

/**
 * NumberUtils
 * Created by hieudt on 9/6/2017.
 */

public class NumberUtils {
  public static String formatNumber(double number) {
    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
    formatter.applyPattern("#,###,###,###.##");
    return formatter.format(number);
  }

  public static String formatNumberTwo(double number) {
    DecimalFormat formatter = new DecimalFormat("#,###,###,###.00", new DecimalFormatSymbols(Locale.US));
    return formatter.format(number);
  }

  public static void formatDoubleEditTextRunTime(EditText mEditText) {
    try {
      String originalString = mEditText.getText().toString();
      if (originalString.contains(",")) originalString = originalString.replaceAll(",", "");
      if (originalString.contains(".")) {
        int index = originalString.indexOf(".");
        if (index == originalString.length() - 1) return;
      }
      double doubleValue = Double.parseDouble(originalString);
      if (doubleValue > Constant.DEFAULT_VALUE_OTP.MAX_PRICE) {
        if (doubleValue % 1 == 0) {
          doubleValue = Constant.DEFAULT_VALUE_OTP.MAX_PRICE - 0.99;
        } else {
          doubleValue = Constant.DEFAULT_VALUE_OTP.MAX_PRICE;
        }
      }
      String formattedString = formatNumber(doubleValue);
      mEditText.setText(formattedString);
      mEditText.setSelection(mEditText.getText().length());
    } catch (NumberFormatException nfe) {
      nfe.printStackTrace();
    }
  }

  public static void formatIntegerEditTextRunTime(EditText mEditText) {
    try {
      String originalString = mEditText.getText().toString();
      if (originalString.contains(",")) originalString = originalString.replaceAll(",", "");

      long longValue = Long.parseLong(originalString);
      if (longValue > Constant.DEFAULT_VALUE_OTP.MAX_AXACT) {
        longValue = Constant.DEFAULT_VALUE_OTP.MAX_AXACT;
      }
      String formattedString = formatNumber(longValue);
      mEditText.setText(formattedString);
      mEditText.setSelection(mEditText.getText().length());
    } catch (NumberFormatException nfe) {
      nfe.printStackTrace();
    }
  }

  public static int getNumberInteger(String s) {
    try {
      if (s.contains(",")) s = s.replaceAll(",", "");
      return Integer.parseInt(s);
    } catch (NumberFormatException nfe) {
      return 0;
    }
  }

  public static double getNumberDouble(String s) {
    try {
      if (s.contains(",")) s = s.replaceAll(",", "");
      return Double.parseDouble(s);
    } catch (NumberFormatException nfe) {
      return 0;
    }
  }

  public static float getNumberFloat(String s) {
    try {
      if (s.contains(",")) s = s.replaceAll(",", "");
      return Float.parseFloat(s);
    } catch (NumberFormatException nfe) {
      return 0;
    }
  }
}
