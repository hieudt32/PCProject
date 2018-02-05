package app.positiveculture.com.data.enumdata;

/**
 * Created by BB on 10/11/2017.
 */

public enum ResidencyStatus {

  SINGAPORE_CITIZEN("singapore_citizen ", "Singapore Citizen"),
  SINGAPORE_PERMANENT_RESIDENT("singapore_permanent_resident", "Singapore Permanent Resident"),
  FOREIGNER("foreigner", "Foreigner");

  String value;
  String code;

  ResidencyStatus(String code, String value) {
    this.code = code;
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
