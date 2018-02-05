package font.app.gem.widget.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import font.app.gem.widgetlibrary.R;

/**
 * Created by hi on 11/10/16.
 */

public class CFTextViewIconLeft extends RelativeLayout {
  public CFTextViewIconLeft(Context context) {
    super(context);
    init(context,null);
  }

  public CFTextViewIconLeft(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context,attrs);
  }

  public CFTextViewIconLeft(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context,attrs);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public CFTextViewIconLeft(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context,attrs);
  }

  private void init(Context context, AttributeSet attrs){
    TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
        R.styleable.TextViewIconLeft, 0, 0);
    String textTitle;
    Drawable leftDrawable;
    int bgColor;
    try {
      // get the text and colors specified using the names in attrs.xml
      textTitle = a.getString(R.styleable.TextViewIconLeft_til_text);
      leftDrawable = a.getDrawable(R.styleable.TextViewIconLeft_til_icon_left);
      bgColor = a.getInteger(R.styleable.TextViewIconLeft_til_text_color,getResources().
       getColor(android.R.color.black));

    } finally {
      a.recycle();
    }

    inflate(getContext(), R.layout.textview_icon_left, this);
    CFTextView textView = (CFTextView) findViewById(R.id.til_text);
    ImageView leftImage = (ImageView)findViewById(R.id.til_icon_left);

    textView.setText(textTitle);
    textView.setTextColor(bgColor);
    leftImage.setImageDrawable(leftDrawable);

  }
}
