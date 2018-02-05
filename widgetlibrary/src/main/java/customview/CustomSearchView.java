package customview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import font.app.gem.widget.ui.CFEditText;
import font.app.gem.widgetlibrary.utils.TextWatcherAdapter;

/**
 * CustomSearchView
 * Created by hungdn on 8/7/2017.
 */

public class CustomSearchView extends CFEditText implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcherAdapter.TextWatcherListener {
  public static enum Location {
    LEFT(0), RIGHT(2);

    final int x;

    private Location(int idx) {
      this.x = idx;
    }
  }

  public interface Listener {
    void didClearText();
  }

  public CustomSearchView(Context context) {
    super(context);
    init();
  }

  public CustomSearchView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public CustomSearchView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  public void setListener(CustomSearchView.Listener listener) {
    this.listener = listener;
  }

  /**
   * null disables the icon
   */
  public void setIconLocation(CustomSearchView.Location loc) {
    this.loc = loc;
    initIcon();
  }

  @Override
  public void setOnTouchListener(View.OnTouchListener l) {
    this.l = l;
  }

  @Override
  public void setOnFocusChangeListener(View.OnFocusChangeListener f) {
    this.f = f;
  }

  private CustomSearchView.Location loc = CustomSearchView.Location.RIGHT;

  private Drawable xD;
  private Drawable xGray;
  private CustomSearchView.Listener listener;

  private View.OnTouchListener l;
  private View.OnFocusChangeListener f;

  @Override
  public boolean onTouch(View v, MotionEvent event) {
    if (getDisplayedDrawable() != null) {
      int x = (int) event.getX();
      int y = (int) event.getY();
      int left = (loc == CustomSearchView.Location.LEFT) ? 0 : getWidth() - getPaddingRight() - xD.getIntrinsicWidth();
      int right = (loc == CustomSearchView.Location.LEFT) ? getPaddingLeft() + xD.getIntrinsicWidth() : getWidth();
      boolean tappedX = x >= left && x <= right && y >= 0 && y <= (getBottom() - getTop());
      if (tappedX) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
          setText("");
          if (listener != null) {
            listener.didClearText();
          }
        }
        return true;
      }
    }
    if (l != null) {
      return l.onTouch(v, event);
    }
    return false;
  }

  @Override
  public void onFocusChange(View v, boolean hasFocus) {
    if (hasFocus) {
      setClearIconVisible(isNotEmpty(getText()));
    } else {
      setClearIconVisible(false);
    }
    if (f != null) {
      f.onFocusChange(v, hasFocus);
    }
  }

  @Override
  public void onTextChanged(EditText view, String text) {
    if (isFocused()) {
      setClearIconVisible(isNotEmpty(text));
    }
  }

  @Override
  public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
    super.setCompoundDrawables(left, top, right, bottom);
    initIcon();
  }

  private void init() {
    super.setOnTouchListener(this);
    super.setOnFocusChangeListener(this);
    addTextChangedListener(new TextWatcherAdapter(this, this));
    initIcon();
    setClearIconVisible(false);
  }

  private void initIcon() {
    xD = null;
    if (loc != null) {
      xD = getCompoundDrawables()[loc.x];
    }
    if (xD == null) {
      xD = getResources().getDrawable(android.R.drawable.presence_offline);
    }
    xD.setBounds(0, 0, xD.getIntrinsicWidth(), xD.getIntrinsicHeight());
    int min = getPaddingTop() + xD.getIntrinsicHeight() + getPaddingBottom();
    if (getSuggestedMinimumHeight() < min) {
      setMinimumHeight(min);
    }

    //Init xGray
//    xGray = getResources().getDrawable(R.drawable.ic_cancel_gray);
//    xGray.setBounds(0, 0, xGray.getIntrinsicWidth(), xGray.getIntrinsicHeight());
//    int min2 = getPaddingTop() + xGray.getIntrinsicHeight() + getPaddingBottom();
//    if (getSuggestedMinimumHeight() < min2) {
//      setMinimumHeight(min2);
//    }
  }

  private Drawable getDisplayedDrawable() {
    return (loc != null) ? getCompoundDrawables()[loc.x] : null;
  }

  protected void setClearIconVisible(boolean visible) {
    Drawable[] cd = getCompoundDrawables();
    Drawable displayed = getDisplayedDrawable();
    boolean wasVisible = (displayed != null);
    //   if (visible != wasVisible) {
    Drawable x = visible ? xD : xGray;
    super.setCompoundDrawables((loc == CustomSearchView.Location.LEFT) ? x : cd[0], cd[1], (loc == CustomSearchView.Location.RIGHT) ? x : cd[2],
            cd[3]);
    //   }
  }
  public static boolean isEmpty(CharSequence s) {
    return s == null || s.length() == 0;
  }
  public static boolean isNotEmpty(CharSequence str) {
    return !isEmpty(str);
  }
}
