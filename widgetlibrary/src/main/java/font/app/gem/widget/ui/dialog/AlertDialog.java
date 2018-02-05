package font.app.gem.widget.ui.dialog;

import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import font.app.gem.widgetlibrary.R;

/**
 * Alert dialogs
 * Created by NEO on 12/5/2016.
 */

public class AlertDialog extends AbsDialog {
  private String mButtonTitle = "";
  private
  @StringRes
  int mButtonTitleId;
  private OnDismiss mOnDismiss;

  @Override
  protected int getContentLayoutId() {
    return R.layout.dialog_content_alert;
  }

  @Override
  protected void injectContentLayoutViews(LayoutInflater inflater, ViewGroup contentLayout) {
    Button button = (Button) contentLayout.findViewById(R.id.item_button);
    String title = mButtonTitleId == 0 ? mButtonTitle : getActivity().getString(mButtonTitleId);
    if (title.equalsIgnoreCase("")) {
      button.setVisibility(View.GONE);
    }
    button.setText(title);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        dismiss();
        if (mOnDismiss != null) {
          mOnDismiss.onDismiss();
        }
      }
    });
  }

  public AlertDialog setButtonTitle(String buttonTitle) {
    mButtonTitle = buttonTitle;
    return this;
  }

  public AlertDialog setButtonTitleId(int buttonTitleId) {
    mButtonTitleId = buttonTitleId;
    return this;
  }

  public AlertDialog setMessageId(int messageId) {
    mMessageId = messageId;
    return this;
  }

  public AlertDialog setOnDismiss(OnDismiss onDismiss) {
    mOnDismiss = onDismiss;
    return this;
  }

  public interface OnDismiss {
    void onDismiss();
  }
}
