package font.app.gem.widget.ui;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.RadioButton;

import font.app.gem.widgetlibrary.utils.FontUtils;

/**
 * Created by hi on 11/11/16.
 */

public class CFRadioButton extends RadioButton {
  public CFRadioButton(Context context) {
    super(context);
    FontUtils.applyCustomFont(this, context, null);
  }

  public CFRadioButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    FontUtils.applyCustomFont(this, context, attrs);
  }

  public CFRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    FontUtils.applyCustomFont(this, context, attrs);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public CFRadioButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    FontUtils.applyCustomFont(this, context, attrs);
  }
}
