package font.app.gem.widget.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import font.app.gem.widgetlibrary.R;

/**
 * Auto dismiss message popup
 * Created by NEO on 12/5/2016.
 */

public class MessagePopup extends TranslucentDialog {
  private static final long DISMISS_TIME = 2500; // in milliseconds

  private String mMessage;
  private String mTitle;
  private
  @StringRes
  int mMessageId;

  private OnDismissListener mOnDismissListener;

  @Override
  protected int getLayoutRes() {
    return R.layout.dialog_message;
  }

  @Override
  protected void injectViews(Dialog dialog, View v) {
    TextView messageTv = (TextView) v.findViewById(R.id.dialog_msg_tv);
    TextView titleTv = (TextView) v.findViewById(R.id.dialog_title_tv);
    String message = mMessageId == 0 ? mMessage : getActivity().getString(mMessageId);
    messageTv.setText(message);
    if (mTitle != null) {
      titleTv.setVisibility(View.VISIBLE);
      titleTv.setText(mTitle);
    }

  }

  @Override
  public void onDismiss(DialogInterface dialog) {
    super.onDismiss(dialog);
    if (mOnDismissListener != null) {
      mOnDismissListener.onDismiss();
    }
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        try {
          MessagePopup.this.dismiss();
        } catch (IllegalArgumentException ex) {
          Log.e("MessagePopup", "Error dismissing: " + ex.getMessage());
        }
      }
    }, DISMISS_TIME);
  }

  public MessagePopup setMessage(String message) {
    mMessage = message;
    return this;
  }

  public MessagePopup setTitle(String title) {
    mTitle = title;
    return this;
  }

  public MessagePopup setMessageId(int messageId) {
    mMessageId = messageId;
    return this;
  }

  public MessagePopup setOnDismissListener(OnDismissListener onDismissListener) {
    mOnDismissListener = onDismissListener;
    return this;
  }

  public interface OnDismissListener {
    void onDismiss();
  }
}
