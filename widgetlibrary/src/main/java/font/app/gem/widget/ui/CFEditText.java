package font.app.gem.widget.ui;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.EditText;

import font.app.gem.widgetlibrary.utils.FontUtils;

/**
 * Created by hi on 11/7/16.
 */

public class CFEditText extends EditText {
  public CFEditText(Context context) {
    super(context);
    FontUtils.applyCustomFont(this, context, null);
  }

  public CFEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    FontUtils.applyCustomFont(this, context, attrs);
  }

  public CFEditText(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    FontUtils.applyCustomFont(this, context, attrs);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public CFEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    FontUtils.applyCustomFont(this, context, attrs);
  }
}
