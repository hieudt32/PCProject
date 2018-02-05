package font.app.gem.widget.ui.dialog;

import android.support.annotation.StringRes;

/**
 * Dialog Button Item
 * Created by NEO on 12/5/2016.
 */

public class DialogItem {
  @StringRes int titleId;
  String title;
  AbsDialog.OnItemClicked itemClickListener;
  boolean highlight = false;

  public DialogItem(String title, AbsDialog.OnItemClicked itemClickListener) {
    this(title, itemClickListener, false);
  }

  public DialogItem(@StringRes int titleId, AbsDialog.OnItemClicked itemClickListener) {
    this(titleId, itemClickListener, false);
  }

  public DialogItem(String title, AbsDialog.OnItemClicked itemClickListener, boolean highlight) {
    this.title = title;
    this.itemClickListener = itemClickListener;
    this.highlight = highlight;
  }

  public DialogItem(@StringRes int titleId, AbsDialog.OnItemClicked itemClickListener, boolean highlight) {
    this.titleId = titleId;
    this.itemClickListener = itemClickListener;
    this.highlight = highlight;
  }
}