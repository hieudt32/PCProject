package font.app.gem.widget.ui.dialog;

import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import font.app.gem.widgetlibrary.R;

/**
 * Dialog with 2 buttons in a Horizontal layout
 * Created by NEO on 12/5/2016.
 */

public class HorizontalButonsDialog extends AbsDialog {

  private List<DialogItem> mDialogItems = new ArrayList<>();

  @Override
  protected int getContentLayoutId() {
    return R.layout.dialog_content_buttons_horizontal;
  }

  @Override
  protected void injectContentLayoutViews(LayoutInflater inflater, ViewGroup contentLayout) {
    for (int i = 0; i < mDialogItems.size(); i++) {
      if (i % 2 == 1) {
        // Add items spacing
        contentLayout.addView(getSpace(inflater));
      }

      DialogItem item = mDialogItems.get(i);
      addItemView(inflater, contentLayout, item, i);
    }
  }

  public HorizontalButonsDialog addItem(@StringRes int titleId, AbsDialog.OnItemClicked itemClickListener) {
    return addItem(titleId, itemClickListener, false);
  }

  public HorizontalButonsDialog addItem(@StringRes int titleId, AbsDialog.OnItemClicked itemClickListener, boolean highlight) {
    mDialogItems.add(new DialogItem(titleId, itemClickListener, highlight));
    return this;
  }

  public void addItemView(LayoutInflater inflater, final ViewGroup contentLayout, final DialogItem item, int position) {
    int layoutId = item.highlight ? R.layout.item_button_horizontal_highlight : R.layout.item_button_horizontal_normal;
    final View view =  inflater.inflate(layoutId, null);

    // Layout param for item
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.WRAP_CONTENT);
    params.weight = 1;
    view.setLayoutParams(params);

    // Button in this item, should has id item_button
    Button itemButton = (Button) view.findViewById(R.id.item_button);
    String title = item.titleId == 0 ? item.title : getActivity().getString(item.titleId);
    itemButton.setText(title);
    itemButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (item.itemClickListener != null) {
          item.itemClickListener.onItemClick();
        }
        dismiss();
      }
    });

    contentLayout.addView(view);
  }

  public View getSpace(LayoutInflater inflater) {
    return inflater.inflate(R.layout.dialog_space_horizontal, null);
  }
}
