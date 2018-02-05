package font.app.gem.widget.ui.dialog;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import font.app.gem.widgetlibrary.R;


/**
 * Created by hi on 11/22/16.
 */

/**
 * How to use:
 *
 * <com.app.ntuc.widgets.CustomHeaderLayout
 android:layout_width="match_parent"
 android:layout_height="@dimen/tool_bar_height"
 app:chl_bg_color="@color/tool_bar_bg"
 app:chl_center_text="@string/su_sign_up"
 app:chl_left_icon="@drawable/ic_arrow_left"
 app:chl_text_color="@color/fc_white"
 >

 * */
public class CustomHeaderLayout extends RelativeLayout {
  public CustomHeaderLayout(Context context) {
    super(context);
    init(context,null);
  }

  public CustomHeaderLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context,attrs);
  }

  public CustomHeaderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context,attrs);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public CustomHeaderLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context,attrs);
  }

  private void init(Context context, AttributeSet attrs){
    TypedArray array = context.getTheme().obtainStyledAttributes(attrs,R.styleable.CustomHeaderLayout,0,0);
    String leftText,centerText,rightText;
    Drawable leftIcon,rightIcon,leftIcon2;
    int textColor,bgColor;
    try {
      leftText = array.getString(R.styleable.CustomHeaderLayout_chl_left_text);
      rightText = array.getString(R.styleable.CustomHeaderLayout_chl_right_text);
      centerText = array.getString(R.styleable.CustomHeaderLayout_chl_center_text);

      leftIcon = array.getDrawable(R.styleable.CustomHeaderLayout_chl_left_icon);
      leftIcon2 = array.getDrawable(R.styleable.CustomHeaderLayout_chl_left_icon_2);
      rightIcon = array.getDrawable(R.styleable.CustomHeaderLayout_chl_right_icon);

      textColor = array.getColor(R.styleable.CustomHeaderLayout_chl_text_color,getResources().
          getColor(android.R.color.white));
      bgColor = array.getColor(R.styleable.CustomHeaderLayout_chl_bg_color,getResources().
          getColor(android.R.color.holo_blue_dark));
    }finally {
      array.recycle();
    }

    View rootView = (View)inflate(getContext(),R.layout.custom_header_layout,this);
    rootView.setBackgroundColor(bgColor);

    TextView leftTxtView = (TextView)rootView.findViewById(R.id.left_text);
    TextView rightTxtView  = (TextView)rootView.findViewById(R.id.right_text);
    TextView centerTxtView = (TextView)rootView.findViewById(R.id.center_text);

    ImageView leftIconView = (ImageView)rootView.findViewById(R.id.ic_left);
    ImageView leftIconView2 = (ImageView)rootView.findViewById(R.id.ic_left_2);
    ImageView rightIconView = (ImageView)rootView.findViewById(R.id.ic_right);

    if(leftText != null && !TextUtils.isEmpty(leftText)){
      leftTxtView.setVisibility(View.VISIBLE);
      leftTxtView.setText(leftText);
      leftTxtView.setTextColor(textColor);
    }

    if(rightText != null && !TextUtils.isEmpty(rightText)){
      rightTxtView.setVisibility(View.VISIBLE);
      rightTxtView.setText(rightText);
      rightTxtView.setTextColor(textColor);
    }

    if(centerText != null && !TextUtils.isEmpty(centerText)){
      centerTxtView.setVisibility(View.VISIBLE);
      centerTxtView.setText(centerText);
      centerTxtView.setTextColor(textColor);
    }

    if(leftIcon != null){
      leftIconView.setVisibility(View.VISIBLE);
      leftIconView.setImageDrawable(leftIcon);
    }
    //Use for case icon 2 is "x" icon
    if(leftIcon2 != null){
      leftIconView2.setVisibility(View.VISIBLE);
      leftIconView2.setImageDrawable(leftIcon2);
    }

    if(rightIcon != null){
      rightIconView.setVisibility(View.VISIBLE);
      rightIconView.setImageDrawable(rightIcon);
    }
  }
}
