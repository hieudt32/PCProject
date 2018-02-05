package font.app.gem.widget.ui.dialog;

import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

import font.app.gem.widgetlibrary.R;

/**
 * dialog show when register successful
 * Created by hungdn on 2/7/2017.
 */

public class RegisterSuccessDialog extends TranslucentDialog {

  private String mUserStageID;
  private OnSubmitRegisterOk mOnSubmitRegisterOk;

  /**
   * Create a new instance dialog
   */
  public static RegisterSuccessDialog newInstance(String userStageID) {
    return new RegisterSuccessDialog().setStageId(userStageID);
  }

  private RegisterSuccessDialog setStageId(String userStageID) {
    this.mUserStageID = userStageID;
    return this;
  }

  @Override
  protected int getLayoutRes() {
    return R.layout.dialog_register_success;
  }

  @Override
  protected void injectViews(Dialog dialog, View v) {
    TextView mUserStageId = (TextView) v.findViewById(R.id.user_stage_id_tv);
    TextView mSubmitTv = (TextView) v.findViewById(R.id.dialog_submit_tv);

    mUserStageId.setText(mUserStageID);
    mSubmitTv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        submitDialog();
      }
    });
  }

  private void submitDialog() {
    if (mOnSubmitRegisterOk != null) {
      mOnSubmitRegisterOk.onSubmitRegister();
    }
  }

  public RegisterSuccessDialog setOnItemSelected(OnSubmitRegisterOk onItemSelected) {
    mOnSubmitRegisterOk = onItemSelected;
    return this;
  }

  /**
   * Dialog callback
   */
  public interface OnSubmitRegisterOk {
    void onSubmitRegister();
  }
}
