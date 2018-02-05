package font.app.gem.widget.ui.dialog;

import android.app.Dialog;
import android.content.res.Configuration;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import font.app.gem.widgetlibrary.R;

/**
 * ReportVideoDialog
 * Created by NEO on 11/29/2016.
 */

public class ReportVideoDialog extends TranslucentDialog {
  public static final String TAG = ReportVideoDialog.class.getSimpleName();
  private OnItemSelected mOnItemSelected;
  private EditText mReportEt;
  private String mName;

  /**
   * Create a new instance dialog
   */
  public static ReportVideoDialog newInstance() {
    return new ReportVideoDialog();
  }

  @Override
  protected int getLayoutRes() {
    return R.layout.dialog_report_video;
  }

  @Override
  protected void injectViews(Dialog dialog, View v) {
    mReportEt = (EditText) v.findViewById(R.id.dialog_report_video_et);

    v.findViewById(R.id.dialog_submit_tv).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
       processSubmit();
      }
    });
    ((TextView) v.findViewById(R.id.dialog_report_video_name_tv)).setText(mName);
    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) closeBt.getLayoutParams();

    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
      params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
      closeBt.setVisibility(View.GONE);
    } else {
      params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
      closeBt.setVisibility(View.VISIBLE);
    }
  }

  /**
   * Submit report
   */
  private void processSubmit() {
    String question = mReportEt.getText().toString();

    if (mOnItemSelected != null) {
      mOnItemSelected.onSubmitReport(question);
    }
  }

  public ReportVideoDialog setOnItemSelected(OnItemSelected onItemSelected) {
    mOnItemSelected = onItemSelected;
    return this;
  }

  public ReportVideoDialog setName(String name) {
    mName = name;
    return this;
  }

  /**
   * Dialog callback
   */
  public interface OnItemSelected {
    void onSubmitReport(String content);
  }
}
