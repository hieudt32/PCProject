package app.positiveculture.com.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.SettingDTO;
import app.positiveculture.com.data.response.dto.User;

/**
 * Shared Preferences wrapper
 * Created by hungdn on 23/8/2017.
 */

public class PrefWrapper {
  private static PrefWrapper sInstance;

  public static final String MY_PREFERENCES = "Pref";
  public static final String KEY_AGENT = "agent";
  public static final String KEY_SETTING = "setting";
  public static final String KEY_MEMBER = "member";
  public static final String KEY_USER = "user";

  private static Context mContext;

  private PrefWrapper(Context context) {
    mContext = context;
  }

  public static SharedPreferences getPreference() {
    return mContext.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
  }

  public static void init(Context context) {
    sInstance = new PrefWrapper(context);
  }

  public static PrefWrapper getInstance() {
    return sInstance;
  }

  /**
   * Save Setting as json
   */
  public static void saveSetting(SettingDTO settingDTO) {
    String settingJson = new Gson().toJson(settingDTO);
    SharedPreferences.Editor editor = getPreference().edit();
    editor.putString(KEY_SETTING, settingJson);
    editor.commit();
  }

  /**
   * Get Setting from saved json
   */
  public static SettingDTO getSetting() {
    String settingJson = getPreference().getString(KEY_SETTING, null);
    if (settingJson == null) {
      return null;
    }

    return new Gson().fromJson(settingJson, SettingDTO.class);
  }

  /**
   * Save Agent as json
   */
  public static void saveAgent(AgentDTO agentDTO) {
    String userJson = new Gson().toJson(agentDTO);
    SharedPreferences.Editor editor = getPreference().edit();
    editor.putString(KEY_AGENT, userJson);
    editor.commit();
  }

  /**
   * Get Agent from saved json
   */
  public static AgentDTO getAgent() {
    String userJson = getPreference().getString(KEY_AGENT, null);
    if (userJson == null) {
      return null;
    }

    return new Gson().fromJson(userJson, AgentDTO.class);
  }

  /**
   * Save Member as json
   */
  public static void saveMember(MemberDTO memberDTO) {
    String userJson = new Gson().toJson(memberDTO);
    SharedPreferences.Editor editor = getPreference().edit();
    editor.putString(KEY_MEMBER, userJson);
    editor.commit();
  }

  /**
   * Get Member from saved json
   */
  public static MemberDTO getMember() {
    String userJson = getPreference().getString(KEY_MEMBER, null);
    if (userJson == null) {
      return null;
    }

    return new Gson().fromJson(userJson, MemberDTO.class);
  }

  /**
   * Save User as json
   */
  public static void saveUser(User user) {
    String userJson = new Gson().toJson(user);
    SharedPreferences.Editor editor = getPreference().edit();
    editor.putString(KEY_USER, userJson);
    editor.commit();
  }

  /**
   * Get User from saved json
   */
  public static User getUser() {
    String userJson = getPreference().getString(KEY_USER, null);
    if (userJson == null) {
      return null;
    }

    return new Gson().fromJson(userJson, MemberDTO.class);
  }

  /**
   * Remove setting by {@code key}
   */
  public static void remove(String key) {
    getPreference().edit()
            .remove(key)
            .apply();
  }

}
