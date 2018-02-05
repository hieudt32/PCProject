package font.app.gem.widget.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import font.app.gem.widgetlibrary.R;

/**
 * Created by hi on 11/9/16.
 */

public class CFProgressBar extends RelativeLayout {
  private CFTextView mContent;
  private ProgressBar mProgressBar;

  public CFProgressBar(Context context) {
    super(context);
    init(context,null);
  }

  public CFProgressBar(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context,attrs);
  }

  public CFProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context,attrs);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public CFProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context,attrs);
  }

  void init(Context context,AttributeSet attrs) {

    TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
        R.styleable.CustomProgressView, 0, 0);
    String textTitle;
    try {
      textTitle = a.getString(R.styleable.CustomProgressView_textLabel);

    }finally {
      a.recycle();
    }
    LayoutInflater mInflater = LayoutInflater.from(context);
    View rootView = mInflater.inflate(R.layout.custom_progressbar, this, true);
    mContent = (CFTextView)rootView.findViewById(R.id.txtProgress);
    mProgressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
    CFTextView label = (CFTextView)rootView.findViewById(R.id.text_label);

    label.setText(textTitle);

  }
  public void setProgressBar(int progress){
    mProgressBar.setProgress(progress);
    mProgressBar.setRotation((-progress / 100f * 360f) - 90f);
  }
  public void setContent(String content){
    mContent.setText(content);
  }
}
