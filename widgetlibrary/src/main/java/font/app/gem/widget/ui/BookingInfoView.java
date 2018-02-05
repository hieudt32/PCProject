package font.app.gem.widget.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;

import font.app.gem.widgetlibrary.R;

/**
 * View for common booking info
 * Created by phong on 11/14/16.
 */

public class BookingInfoView extends FrameLayout {
  private CFTextView contentTextView;
  private CFTextView titleTextView;

  public BookingInfoView(Context context) {
    super(context);
    init(context, null);
  }

  public BookingInfoView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public BookingInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

//  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public BookingInfoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
        R.styleable.BookingInfoView, 0, 0);
    String textTitle;
    String textValue;
    int titleTextColor;
    int contentTextColor;

    try {
      // get the text and colors specified using the names in attrs.xml
      textTitle = a.getString(R.styleable.BookingInfoView_biv_title);
      textValue = a.getString(R.styleable.BookingInfoView_biv_content);

      titleTextColor = a.getInteger(R.styleable.BookingInfoView_biv_title_text_color,
          ContextCompat.getColor(getContext(), R.color.booking_title_color));
      contentTextColor = a.getInteger(R.styleable.BookingInfoView_biv_content_text_color,
          ContextCompat.getColor(getContext(), R.color.booking_content_color));

    } finally {
      a.recycle();
    }

    inflate(getContext(), R.layout.booking_info_view, this);
    titleTextView = (CFTextView) findViewById(R.id.title_tv);
    contentTextView = (CFTextView) findViewById(R.id.content_tv);

    titleTextView.setGravity(Gravity.CENTER_HORIZONTAL);
    contentTextView.setGravity(Gravity.CENTER_HORIZONTAL);

    titleTextView.setText(textTitle);
    titleTextView.setTextColor(titleTextColor);
    contentTextView.setText(textValue);
    contentTextView.setTextColor(contentTextColor);

  }

  public void setContentBooking(String contentBooking) {
    contentTextView.setText(contentBooking);
  }

  public void setTitle(String title) {
    titleTextView.setText(title);
  }

  public void setTitle(@StringRes int titleId) {
    titleTextView.setText(titleId);
  }

  public void setColor(@ColorRes int colorRes) {
    int color = ContextCompat.getColor(getContext(), colorRes);
    titleTextView.setTextColor(color);
    contentTextView.setTextColor(color);
  }

  public void setContentColor(@ColorRes int colorRes) {
    int color = ContextCompat.getColor(getContext(), colorRes);
    contentTextView.setTextColor(color);
  }

}
