package font.app.gem.widget.ui;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;
import font.app.gem.widgetlibrary.utils.FontUtils;

/**
 * Created by hi on 11/7/16.
 */

public class CFTextView extends TextView{
  public CFTextView(Context context) {
    super(context);
    init(context, null);
  }

  public CFTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public CFTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

//  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public CFTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    FontUtils.applyCustomFont(this, context, attrs);
  }

  @Override
  public boolean isInEditMode() {
    return true;
  }
}
