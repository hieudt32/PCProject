package app.positiveculture.com.agent.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * CustomViewPager
 * Created by hieudt on 12/5/2017.
 */

public class CustomViewPager extends ViewPager {
  private boolean swipeable;

  public CustomViewPager(Context context) {
    super(context);
  }

  public CustomViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void setSwipeable(boolean swipeable) {
    this.swipeable = swipeable;
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return (this.swipeable) && super.onInterceptTouchEvent(ev);
  }
}
