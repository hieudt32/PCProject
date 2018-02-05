package font.app.gem.widget.ui.dialog;

import android.app.Dialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import font.app.gem.widgetlibrary.R;

/**
 * Reply question comment dialog
 * Created by hungdn on 1/18/2017.
 */

public class ReplyQuestionDialog extends TranslucentDialog{

  public static final String TAG = ReplyQuestionDialog.class.getSimpleName();
  private String mTitle;
  private OnItemSelected mOnItemSelected;
  private EditText mAnswerEt;
  private TextView mTitleTv;

  /**
   * Create a new instance dialog
   */
  public static ReplyQuestionDialog newInstance(String title) {
    return new ReplyQuestionDialog().setTitle(title);
  }

  private ReplyQuestionDialog setTitle(String title) {
    this.mTitle = title;
    return this;
  }

  @Override
  protected int getLayoutRes() {
    return R.layout.dialog_reply_question;
  }

  @Override
  protected void injectViews(Dialog dialog, View v) {
    mAnswerEt = (EditText) v.findViewById(R.id.dialog_answer_question_et);
    mTitleTv = (TextView) v.findViewById(R.id.dialog_title_tv);

    mTitleTv.setText(mTitle);
    v.findViewById(R.id.dialog_submit_tv).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        processSubmit();
      }
    });
  }

  private void processSubmit() {
    String question = mAnswerEt.getText().toString();

    if (mOnItemSelected != null) {
      mOnItemSelected.onSelectItem(question);
    }
  }

  public ReplyQuestionDialog setOnItemSelected(OnItemSelected onItemSelected) {
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
