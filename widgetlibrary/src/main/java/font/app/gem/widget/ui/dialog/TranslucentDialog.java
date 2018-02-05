package font.app.gem.widget.ui.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import font.app.gem.widgetlibrary.R;

/**
 * TranslucentDialog
 * Created by NEO on 11/30/2016.
 */

public abstract class TranslucentDialog extends DialogFragment {

  protected View closeBt;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
  }

  @Override
  public void onStart() {
    super.onStart();
    Dialog dialog = getDialog();
    if (dialog != null) {
      int width = ViewGroup.LayoutParams.MATCH_PARENT;
      int height = ViewGroup.LayoutParams.MATCH_PARENT;
      Window window = dialog.getWindow();
      if (window != null) {
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(width, height);

        WindowManager.LayoutParams windowParams = window.getAttributes();
//        windowParams.alpha = 0.8f;
        windowParams.dimAmount = 0.0f;

        window.setAttributes(windowParams);
      }
    }
  }

  @Override
  public void onActivityCreated(Bundle arg0) {
    super.onActivityCreated(arg0);
    if (getDialog().getWindow() != null) {
      getDialog().getWindow()
          .getAttributes().windowAnimations = R.style.DialogAnimation;
    }
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
    final View view = getActivity().getLayoutInflater().inflate(getLayoutRes(), null);
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        dismiss();
      }
    });

    final Drawable d = new ColorDrawable(Color.BLACK);
    d.setAlpha(130);

    Window window = dialog.getWindow();
    if (window != null) {
      window.setBackgroundDrawable(d);
      window.setContentView(view);
    }

    final WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
    params.width = WindowManager.LayoutParams.MATCH_PARENT;
    params.height = WindowManager.LayoutParams.MATCH_PARENT;
    params.gravity = Gravity.CENTER;

    dialog.setCanceledOnTouchOutside(true);

    intiCommonViews(view);
    injectViews(dialog, view);
    return dialog;
  }

  protected abstract int getLayoutRes();

  protected abstract void injectViews(Dialog dialog, View v);

  private void intiCommonViews(View view) {
    closeBt = view.findViewById(R.id.dialog_close_bt);
    if (closeBt != null) {
      closeBt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          dismiss();
        }
      });
    }
  }

}
