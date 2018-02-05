package font.app.gem.widget.ui.dialog;

import android.app.Dialog;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import font.app.gem.widgetlibrary.R;

/**
 * RejectBookingDialog
 * Created by NEO on 11/29/2016.
 */

public abstract class AbsDialog extends TranslucentDialog {
  protected TextView mTitleTv;
  protected TextView mMessageTv;

  protected LayoutInflater mInflater;

  protected
  @StringRes
  int mTitleId;

  protected
  @StringRes
  int mMessageId;

  private String mTitle;
  private String mMessage;
  private boolean isHideCloseBtn;

  @Override
  protected int getLayoutRes() {
    return R.layout.dialog_buttons_title_msg;
  }

  @Override
  protected void injectViews(Dialog dialog, View v) {
    mInflater = LayoutInflater.from(getContext());

    mTitleTv = (TextView) v.findViewById(R.id.dialog_title_tv);
    String title = mTitleId == 0 ? mTitle : getActivity().getString(mTitleId);
    mTitleTv.setText(title);

    ImageView mCloseBtn = (ImageView) v.findViewById(R.id.dialog_close_bt);

    mMessageTv = (TextView) v.findViewById(R.id.dialog_msg_tv);
    String msg = mMessageId == 0 ? mMessage : getActivity().getString(mMessageId);
    mMessageTv.setText(msg);

    if (isHideCloseBtn) {
      mCloseBtn.setVisibility(View.INVISIBLE);
    } else {
      mCloseBtn.setVisibility(View.VISIBLE);
    }

    // Content layout
    FrameLayout container = (FrameLayout) v.findViewById(R.id.dialog_buttons_layout);
    ViewGroup contentLayout = (ViewGroup) mInflater.inflate(getContentLayoutId(), null);
    container.addView(contentLayout);


    injectContentLayoutViews(mInflater, contentLayout);
  }

  /**
   * Set dialog title by given string
   */
  public AbsDialog setTitle(String title) {
    mTitle = title;
    return this;
  }

  /**
   * Set dialog title by given string id
   */
  public AbsDialog setTitle(@StringRes int titleId) {
    mTitleId = titleId;
    return this;
  }

  /**
   * Set dialog message by given string
   */
  public AbsDialog setMessage(String message) {
    mMessage = message;
    return this;
  }

  /**
   * Set dialog message by given string id
   */
  public AbsDialog setMessage(@StringRes int messageId) {
    mMessageId = messageId;
    return this;
  }

  protected abstract int getContentLayoutId();

  protected abstract void injectContentLayoutViews(LayoutInflater inflater, ViewGroup contentLayout);

  public AbsDialog setHideCloseButton(boolean b) {
    isHideCloseBtn = b;
    return this;
  }

  public interface OnItemClicked {
    void onItemClick();
  }
}
