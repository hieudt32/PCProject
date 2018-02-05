package app.positiveculture.com.agent.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.data.pref.PrefWrapper;

/**
 * CountryCode
 * Created by hieudt on 11/14/2017.
 */

public class CountryCode {
  public static List<String> getCode() {
    JsonArray listCode = PrefWrapper.getSetting().getmCountryCode();
    List<String> listCountryCode = new ArrayList<>();
    for (int i = 0; i < listCode.size(); i++) {
      JsonObject objectCode = (JsonObject) listCode.get(i);
      String objCode[] = objectCode.toString().split(":");
      listCountryCode.add(objCode[0].substring(2, objCode[0].length() - 1));
    }
    return listCountryCode;
  }
}
