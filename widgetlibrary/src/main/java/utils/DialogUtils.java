package utils;

import android.content.Context;

import dialog.CustomDialog;
import font.app.gem.widgetlibrary.R;

/**
 * DialogUtils
 * Created by hungdn on 8/22/2017.
 */

public class DialogUtils {
  public static void showDialog(Context context, String title, String des, CustomDialog.OnConfirmSelected onConfirmSelected, CustomDialog.OnCancelSelected onCancelSelected) {
    final CustomDialog dialog = new CustomDialog(context,
            context.getString(R.string.yes),
            context.getString(R.string.no),
            title,
            des,
            onConfirmSelected, onCancelSelected);
    dialog.show();
  }

  public static void showDialogOKCancel(Context context, String title, String des, CustomDialog.OnConfirmSelected onConfirmSelected, CustomDialog.OnCancelSelected onCancelSelected) {
    final CustomDialog dialog = new CustomDialog(context,
            context.getString(R.string.ok),
            context.getString(R.string.cancel),
            title,
            des,
            onConfirmSelected, onCancelSelected);
    dialog.show();
  }

  public static void showDialog(Context context, String title, String des, CustomDialog.OnConfirmSelected onConfirmSelected) {
    final CustomDialog dialog = new CustomDialog(context,
            context.getString(R.string.ok),
            null,
            title,
            des,
            onConfirmSelected);
    dialog.show();
  }
}
