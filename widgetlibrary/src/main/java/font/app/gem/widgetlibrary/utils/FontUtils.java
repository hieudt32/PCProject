package font.app.gem.widgetlibrary.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import font.app.gem.widgetlibrary.R;


public class FontUtils {
  // TODO: change this if use custom font
  private static boolean USE_DEFAULT = true;

  private static final int TYPE_FACE_EXTRA_LIGHT = 10;
  private static final int TYPE_FACE_EXTRA_BOLD = 11;

  public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

  public static void applyCustomFont(TextView customFontTextView, Context context, AttributeSet attrs) {
    if (USE_DEFAULT) {
      return;
    }

    TypedArray attributeArray = context.obtainStyledAttributes(
        attrs,
        R.styleable.CustomFontTextView);

    String fontName = attributeArray.getString(R.styleable.CustomFontTextView_font);

    // check if a special textStyle was used (e.g. extra bold)
    int textStyle = attributeArray.getInt(R.styleable.CustomFontTextView_textStyle, 0);

    // if nothing extra was used, fall back to regular android:textStyle parameter
    if (textStyle == 0) {
      textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
    }
    String typedArray = attrs.getAttributeValue(ANDROID_SCHEMA,"style");

    Typeface customFont = selectTypeface(context, fontName, textStyle);
    customFontTextView.setTypeface(customFont);

    attributeArray.recycle();
  }

  private static Typeface selectTypeface(Context context, String fontName, int textStyle) {
    if(fontName== null){
      fontName = context.getString(R.string.font_name_avenir);
    }
    if (fontName.contentEquals(context.getString(R.string.font_name_fontawesome))) {
      return FontCache.getTypeface(context.getString(R.string.font_awesome), context);
    }
    else if (fontName.contentEquals(context.getString(R.string.font_name_source_sans_pro))) {
            /*
            information about the TextView textStyle:
            http://developer.android.com/reference/android/R.styleable.html#TextView_textStyle
            */
      switch (textStyle) {
        case Typeface.BOLD: // bold
          return FontCache.getTypeface(context.getString(R.string.font_bold), context);

        case Typeface.ITALIC: // italic
          return FontCache.getTypeface(context.getString(R.string.font_italic), context);

        case Typeface.BOLD_ITALIC: // bold italic
          return FontCache.getTypeface(context.getString(R.string.font_bold_italic), context);

        case TYPE_FACE_EXTRA_LIGHT: // extra light, equals @integer/font_style_extra_light
          return FontCache.getTypeface(context.getString(R.string.font_extra_light), context);

        case TYPE_FACE_EXTRA_BOLD: // extra bold, equals @integer/font_style_extra_bold
          return FontCache.getTypeface(context.getString(R.string.font_extra_bold), context);

        case Typeface.NORMAL: // regular
        default:
          return FontCache.getTypeface(context.getString(R.string.font_regular), context);
      }
    }
    else if(fontName.contentEquals(context.getString(R.string.font_name_avenir))){
      switch (textStyle){
        case Typeface.BOLD:
          return FontCache.getTypeface(context.getString(R.string.font_avenir_bold),context);
        case Typeface.NORMAL:
          default:
            return FontCache.getTypeface(context.getString(R.string.font_avenir_regular),context);
      }
    }
    else {
      return null;
    }
  }
}
