package app.positiveculture.com.agent;

/**
 * Constant
 * Created by hungdn on 8/15/2017.
 */

public interface Constant {
  int PAGE_LIMIT=15;
  String LINK_LOAD_PDF="https://docs.google.com/gview?embedded=true&url=";
  interface TAB_POSITION {
    int PROPERTIES_TAB = 0;
    int CLIENTS_TAB = 1;
    int PROFILE_TAB = 2;
  }

  interface PHOTO_TYPE {
    int TYPE_PHOTO = 1;
    int TYPE_ADD_PHOTO = 2;
  }

  interface CONTRACT_STATUS {
    String PENDING = "pending";
    String APPROVED = "approved";
    String PENDING_APPROVAL = "- Pending Approval";
  }

  interface FLOOR_SIZE_UNIT {
    String SQM = "sqm";
    String SQFT = "sqft";
  }

  interface DEFAULT_VALUE_OTP {
    int HDB_FREE = 5000;
    int NON_HDB_WEEK_EXPIRY = 2;
    int HDB_WEEK_EXPIRY = 3;
    int WEEK_COMPLETION = 8;
    int HDB_APPT = 14;
    int NON_HDB_OPTION_FREE = 1;
    int NON_HDB_EXERCISE_FREE = 4;
    double MAX_PRICE = 999999999999.99;
    long MAX_AXACT = 999999999;
  }

}
