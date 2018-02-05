package dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.gemvietnam.utils.StringUtils;

import font.app.gem.widgetlibrary.R;


/**
 * CustomDialog
 * Created by hungdn on 8/18/2017.
 */

public class CustomDialog extends Dialog {

  private OnCancelSelected mOnCancelSelected;
  private OnConfirmSelected mOnConfirmSelected;

  private String title, des;
  private String yesText, noText;

  public CustomDialog(@NonNull Context context, String yesText, String noText, String title, String des, OnConfirmSelected onOkClick, OnCancelSelected onCancelClick) {
    super(context);
    this.title = title;
    this.des = des;
    if (onOkClick != null) this.mOnConfirmSelected = onOkClick;
    if (onCancelClick != null) this.mOnCancelSelected = onCancelClick;
    this.yesText = yesText;
    this.noText = noText;
  }

  public CustomDialog(Context context, String yesText, String noText, String title, String des, OnConfirmSelected onOkClick) {
    this(context, yesText, noText, title, des, onOkClick, null);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.layout_custom_dialog);
    getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    setCancelable(false);
    getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

    TextView mDialogTitleTv = (TextView) findViewById(R.id.dialog_title_tv);
    TextView mDialogDesTv = (TextView) findViewById(R.id.dialog_des_tv);
    TextView mDialogOkTv = (TextView) findViewById(R.id.dialog_ok_tv);
    TextView mDialogCancelTv = (TextView) findViewById(R.id.dialog_cancel_tv);
    TextView mDialogSubmitTv = (TextView) findViewById(R.id.dialog_submit_tv);
    View v = findViewById(R.id.view_line_devider);

    mDialogOkTv.setText(yesText);
    if (noText == null) {
      mDialogCancelTv.setVisibility(View.GONE);
      v.setVisibility(View.GONE);
    } else {
      mDialogCancelTv.setText(noText);
    }
    mDialogSubmitTv.setText(yesText);

    View layoutOneBtn = findViewById(R.id.layout_one_button);
    View layoutTwoBtn = findViewById(R.id.layout_two_button);

    mDialogTitleTv.setText(title);
    mDialogTitleTv.setTypeface(null, Typeface.BOLD);
    mDialogDesTv.setText(des);

    if (mOnConfirmSelected != null && mOnCancelSelected != null) {
      layoutOneBtn.setVisibility(View.GONE);
      layoutTwoBtn.setVisibility(View.VISIBLE);
      mDialogOkTv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          dismiss();
          mOnConfirmSelected.onConfirmSelected();
        }
      });

      mDialogCancelTv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          dismiss();
          mOnCancelSelected.onCancelSelected();
        }
      });
    } else if (mOnConfirmSelected != null) {
      layoutOneBtn.setVisibility(View.VISIBLE);
      layoutTwoBtn.setVisibility(View.GONE);
      mDialogSubmitTv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          dismiss();
          mOnConfirmSelected.onConfirmSelected();
        }
      });
    }


  }

  public interface OnCancelSelected {
    void onCancelSelected();
  }

  public interface OnConfirmSelected {
    void onConfirmSelected();
  }
}
