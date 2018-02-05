package font.app.gem.widget.ui.dialog;

import android.app.Dialog;
import android.view.View;
import android.widget.EditText;

import font.app.gem.widgetlibrary.R;


public class RedemptionDialog extends TranslucentDialog {
  public static final String TAG = AskQuestionDialog.class.getSimpleName();
  private OnItemSelected mOnItemSelected;
  private EditText mQuestionEt;

  /**
   * Create a new instance dialog
   */
  public static RedemptionDialog newInstance() {
    return new RedemptionDialog();
  }

  @Override
  protected int getLayoutRes() {
    return R.layout.dialog_redemption_code;
  }

  @Override
  protected void injectViews(Dialog dialog, View v) {
    mQuestionEt = (EditText) v.findViewById(R.id.dialog_ask_question_et);

    v.findViewById(R.id.dialog_submit_tv).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        processSubmit();
      }
    });
  }

  private void processSubmit() {
    String question = mQuestionEt.getText().toString();

    if (mOnItemSelected != null) {
      mOnItemSelected.onSelectItem(question);
    }
  }

  public RedemptionDialog setOnItemSelected(OnItemSelected onItemSelected) {
    mOnItemSelected = onItemSelected;
    return this;
  }

  /**
   * Dialog callback
   */
  public interface OnItemSelected {
    void onSelectItem(String content);
  }
}