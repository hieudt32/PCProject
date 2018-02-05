package app.positiveculture.com.agent.utils;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.gemvietnam.utils.StringUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.dto.DisplayAddress;
import app.positiveculture.com.data.enumdata.PropertyStatus;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import utils.DateTimeUtils;

/**
 * PropertyUtils
 * Created by hieudt on 11/7/2017.
 */

public class PropertyUtils {
  public static DisplayAddress createDisplayAddress(String building, String floor, String unit
          , String streetOne, String streetTwo, String country, String postalCode) {
    DisplayAddress displayAddress = new DisplayAddress();
    String floorUnit;
    if (StringUtils.isEmpty(floor)) {
      if (StringUtils.isEmpty(unit)) {
        floorUnit = "";
      } else {
        floorUnit = unit;
      }
    } else {
      if (StringUtils.isEmpty(unit)) {
        floorUnit = floor;
      } else {
        floorUnit = floor + " " + unit;
      }
    }
    if (!StringUtils.isEmpty(building)) {
      displayAddress.setLineOne(building);
      if (!StringUtils.isEmpty(floorUnit)) {
        displayAddress.setLineTwo(streetOne + " " + floorUnit);
      } else {
        displayAddress.setLineTwo(streetOne);
      }
    } else {
      displayAddress.setLineOne(streetOne);
      if (!StringUtils.isEmpty(floorUnit)) {
        displayAddress.setLineTwo(floorUnit);
      } else {
        if (!StringUtils.isEmpty(streetTwo)) {
          displayAddress.setLineTwo(streetTwo);
        } else {
          displayAddress.setLineTwo(country + " " + postalCode);
        }
      }
    }
    return displayAddress;
  }

  public static String getDisplayAddressUser(String floor, String unit, String building, String streetOne,
                                             String streetTwo, String country, String postalCode) {
    String address = (!StringUtils.isEmpty(floor)) ? floor + "-" : "";
    address = (!StringUtils.isEmpty(unit)) ? address + unit + ", " : address;
    address = (!StringUtils.isEmpty(building)) ? address + building + ", " : address;
    address = (!StringUtils.isEmpty(streetOne)) ? address + streetOne + ", " : address;
    address = (!StringUtils.isEmpty(streetTwo)) ? address + streetTwo + ", " : address;
    address = (!StringUtils.isEmpty(country)) ? address + country + ", " : address;
    address = (!StringUtils.isEmpty(postalCode)) ? address + postalCode : address;
    return address;
  }

  public static String countTimeCreate(String time, Context context) {
    try {
      if (time == null) return null;
      Log.d("time", time);
      Date date = DateTimeUtils.dateTimeFormatOne().parse(time);
      Date now = Calendar.getInstance().getTime();
      Log.d("time", date.toString());
      Log.d("time", now.toString());

      long different = now.getTime() - date.getTime();

      long secondsInMilli = 1000;
      long minutesInMilli = secondsInMilli * 60;
      long hoursInMilli = minutesInMilli * 60;
      long daysInMilli = hoursInMilli * 24;
      long monthInMilli = daysInMilli * 30;
      long yearInMilli = monthInMilli * 12;

      long elapsedYears = different / yearInMilli;
      different = different % yearInMilli;
      long elapsedMonths = different / monthInMilli;
      different = different % monthInMilli;
      long elapsedDays = different / daysInMilli;
      different = different % daysInMilli;

      long elapsedHours = different / hoursInMilli;
      different = different % hoursInMilli;

      long elapsedMinutes = different / minutesInMilli;
      different = different % minutesInMilli;

      long elapsedSeconds = different / secondsInMilli;

      if (elapsedYears > 0) {
        if (elapsedMonths > 6) {
          return (elapsedDays + 1) + " " + context.getString(R.string.year) + "s " + context.getString(R.string.ago);
        } else {
          if (elapsedYears > 1) {
            return elapsedDays + " " + context.getString(R.string.year) + "s " + context.getString(R.string.ago);
          } else
            return elapsedDays + " " + context.getString(R.string.year) + " " + context.getString(R.string.ago);
        }
      } else if (elapsedMonths > 0) {
        if (elapsedDays > 15) {
          return (elapsedMonths + 1) + " " + context.getString(R.string.month) + "s " + context.getString(R.string.ago);
        } else {
          if (elapsedDays > 1) {
            return elapsedMonths + " " + context.getString(R.string.month) + "s " + context.getString(R.string.ago);
          } else
            return elapsedMonths + " " + context.getString(R.string.month) + " " + context.getString(R.string.ago);
        }
      } else if (elapsedDays > 0) {
        if (elapsedHours > 12) {
          return (elapsedDays + 1) + " " + context.getString(R.string.day) + "s " + context.getString(R.string.ago);
        } else {
          if (elapsedDays > 1) {
            return elapsedDays + " " + context.getString(R.string.day) + "s " + context.getString(R.string.ago);
          } else
            return elapsedDays + " " + context.getString(R.string.day) + " " + context.getString(R.string.ago);
        }
      } else if (elapsedHours > 0) {
        if (elapsedMinutes > 30) {
          return (elapsedHours + 1) + " " + context.getString(R.string.hour) + "s " + context.getString(R.string.ago);
        } else {
          if (elapsedHours > 1) {
            return elapsedHours + " " + context.getString(R.string.hour) + "s " + context.getString(R.string.ago);
          } else
            return elapsedHours + " " + context.getString(R.string.hour) + " " + context.getString(R.string.ago);
        }
      } else if (elapsedMinutes > 0) {
        if (elapsedSeconds > 30) {
          return (elapsedMinutes + 1) + " " + context.getString(R.string.minute) + "s " + context.getString(R.string.ago);
        } else {
          if (elapsedMinutes > 1) {
            return elapsedMinutes + " " + context.getString(R.string.minute) + "s " + context.getString(R.string.ago);
          } else
            return elapsedMinutes + " " + context.getString(R.string.minute) + " " + context.getString(R.string.ago);
        }
      } else {
        if (elapsedSeconds > 1) {
          return elapsedSeconds + " " + context.getString(R.string.second) + "s " + context.getString(R.string.ago);
        } else if (elapsedSeconds == 1) {
          return elapsedSeconds + " " + context.getString(R.string.second) + " " + context.getString(R.string.ago);
        } else return null;
      }

    } catch (ParseException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public static String setStatus(PropertyStatus status) {
    switch (status) {
      case new_property:
        return "New";
      case otp:
        return "OTP";
      case completed:
        return "Completed";
      case conveyancing:
        return "Conveyancing";
      case otp_completed:
        return "OTP Completed";
      default:
        return "";
    }
  }

  public static void displayProperty(PropertyDTO propertyDTO, TextView oneTv, TextView twoTv) {
    if (!StringUtils.isEmpty(propertyDTO.getmDisplayOne())) {
      oneTv.setText(propertyDTO.getmDisplayOne());
      if (!StringUtils.isEmpty(propertyDTO.getmDisplayTwo())) {
        twoTv.setText(propertyDTO.getmDisplayTwo());
      } else if (!StringUtils.isEmpty(propertyDTO.getmDisplayThree())) {
        twoTv.setText(propertyDTO.getmDisplayThree());
      } else if (!StringUtils.isEmpty(propertyDTO.getmDisplayFour())) {
        twoTv.setText(propertyDTO.getmDisplayFour());
      }
    } else if (!StringUtils.isEmpty(propertyDTO.getmDisplayTwo())) {
      oneTv.setText(propertyDTO.getmDisplayTwo());
      if (!StringUtils.isEmpty(propertyDTO.getmDisplayThree())) {
        twoTv.setText(propertyDTO.getmDisplayThree());
      } else if (!StringUtils.isEmpty(propertyDTO.getmDisplayFour())) {
        twoTv.setText(propertyDTO.getmDisplayFour());
      }
    } else if (!StringUtils.isEmpty(propertyDTO.getmDisplayThree())) {
      oneTv.setText(propertyDTO.getmDisplayThree());
      if (!StringUtils.isEmpty(propertyDTO.getmDisplayFour())) {
        twoTv.setText(propertyDTO.getmDisplayFour());
      }
    } else if (!StringUtils.isEmpty(propertyDTO.getmDisplayThree())) {
      oneTv.setText(propertyDTO.getmDisplayFour());
    }
  }
}
