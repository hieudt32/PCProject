package font.app.gem.widget.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import font.app.gem.widgetlibrary.R;

/**
 * Common HeaderView
 * Created by hi on 11/11/16.
 */

public class HeaderView extends RelativeLayout {
  private TextView mRightButtonTv;

  public HeaderView(Context context) {
    super(context);
    init(context,null);
  }

  public HeaderView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context,attrs);
  }

  public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context,attrs);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public HeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context,attrs);
  }
  private void init(Context context, AttributeSet attrs){
    TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
        R.styleable.HeaderView, 0, 0);
    String textTitle, rightText;
    Drawable leftDrawable;
    int bgColor;
    int textColor;

    try {
      // get the text and colors specified using the names in attrs.xml
      textTitle = a.getString(R.styleable.HeaderView_hdv_title);
      rightText = a.getString(R.styleable.HeaderView_hdv_right_text);
      leftDrawable = a.getDrawable(R.styleable.HeaderView_hdv_icon);
      bgColor = a.getInteger(R.styleable.HeaderView_hdv_bg_color,getResources().
          getColor(android.R.color.white));
      textColor = a.getInteger(R.styleable.HeaderView_hdv_text_color,getResources().
          getColor(android.R.color.holo_red_dark));

    } finally {
      a.recycle();
    }

    View rootVIew = inflate(getContext(), R.layout.header_view, this);
    TextView textView = (CFTextView) findViewById(R.id.header_title);
    mRightButtonTv = (TextView) findViewById(R.id.header_right_text);
    ImageView leftImage = (ImageView)findViewById(R.id.action_back);

    textView.setText(textTitle);
    mRightButtonTv.setText(rightText);
    textView.setTextColor(textColor);
    rootVIew.setBackgroundColor(bgColor);
    leftImage.setImageDrawable(leftDrawable);
  }

  public void setRightText(String title, OnClickListener onClickListener) {
    if (title == null && onClickListener == null) {
      mRightButtonTv.setVisibility(INVISIBLE);
    } else {
      mRightButtonTv.setText(title);
      mRightButtonTv.setOnClickListener(onClickListener);
      mRightButtonTv.setVisibility(VISIBLE);
    }
  }

  public void setRightTextClick(OnClickListener onClickListener) {
    mRightButtonTv.setOnClickListener(onClickListener);
  }

  public void visibleRightText(boolean visible) {
    mRightButtonTv.setVisibility(visible ? VISIBLE : INVISIBLE);
  }
  @Override
  public boolean isInEditMode() {
    return true;
  }
}
