package app.positiveculture.com.user;

/**
 * Constant
 * Created by hungdn on 8/15/2017.
 */

public interface Constant {
  interface TAB_POSITION {
    int PROPERTIES_TAB = 0;
    int CLIENTS_TAB = 1;
    int SETTINGS_TAB = 2;
  }

  interface LoanType {
    String NEW_LOAN = "new_loan";
    String REFINANCE = "refinance";
  }

  String KEY_OTP_OBJECT = "disclaimer_presenter_otp";
  String KEY_TYPE_PROCESS = "disclaimer_type_progress";
  int SIGNED_OTP_SUCCESS = 1001;
}
