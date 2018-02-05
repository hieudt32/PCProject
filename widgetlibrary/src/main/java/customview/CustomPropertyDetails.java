package customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gemvietnam.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import font.app.gem.widgetlibrary.R;
import font.app.gem.widgetlibrary.R2;

/**
 * CustomPropertyDetails
 * Created by Chinh on 8/22/2017.
 */

public class CustomPropertyDetails extends RelativeLayout {
  @BindView(R2.id.title_property_details_tv)
  TextView mTitle;
  @BindView(R2.id.value_property_details_tv)
  TextView mValue;
  @BindView(R2.id.add_property_details_iv)
  ImageView mAdd;
  @BindView(R2.id.minus_property_details_iv)
  ImageView mMinus;

  private boolean isRoom = false;
  private int value = 0;

  public CustomPropertyDetails(Context context) {
    super(context);
    init(context, null, 0);
  }

  public CustomPropertyDetails(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs, 0);
  }

  public CustomPropertyDetails(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs, 0);
  }

  public void init(Context context, AttributeSet attrs, int defStyle) {
    LayoutInflater inflater = LayoutInflater.from(context);
    inflater.inflate(R.layout.custom_property_details, this, true);
    ButterKnife.bind(this);
    final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomPropertyDetails, defStyle, 0);
    String text = a.getString(R.styleable.CustomPropertyDetails_custom_property_details_title);
    if (!StringUtils.isEmpty(text)) mTitle.setText(text);
    String value = a.getString(R.styleable.CustomPropertyDetails_custom_property_details_value);
    if (!StringUtils.isEmpty(value)) mValue.setText(value);
    int color = a.getColor(R.styleable.CustomPropertyDetails_custom_property_details_color, 0);
    mValue.setTextColor(color);
    a.recycle();
  }

  @OnClick(R2.id.add_property_details_iv)
  void onAdd() {
    add();
  }

  @OnClick(R2.id.minus_property_details_iv)
  void onMinus() {
    minus();
  }

  private void add() {
    mValue.setTextColor(ContextCompat.getColor(getContext(), R.color.header_color));
    value++;
    mValue.setText(String.valueOf(value));
  }

  private void minus() {
    if (!isRoom) {
      if (value == 0) return;
      value--;
      if (value == 0) {
        mValue.setTextColor(ContextCompat.getColor(getContext(), R.color.hint_text_alpha));
      } else mValue.setTextColor(ContextCompat.getColor(getContext(), R.color.header_color));
      mValue.setText(String.valueOf(value));
    } else {
      mValue.setTextColor(ContextCompat.getColor(getContext(), R.color.header_color));
      if (value == 0) return;
      value--;
      if (value == 0) {
        mValue.setText(getResources().getString(R.string.studio));
      } else mValue.setText(String.valueOf(value));
    }
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
    if (isRoom) {
      if (value == 0) {
        mValue.setText(getResources().getString(R.string.studio));
      } else mValue.setText(String.valueOf(this.value));
    } else {
      if (value == 0) {
        mValue.setTextColor(ContextCompat.getColor(getContext(), R.color.hint_text_alpha));
      } else {
        mValue.setTextColor(ContextCompat.getColor(getContext(), R.color.header_color));
      }
      mValue.setText(String.valueOf(this.value));
    }
  }

  public void setRoom(boolean room) {
    isRoom = room;
  }

  public TextView getTitleView() {
    return mTitle;
  }

  public TextView getValueView() {
    return mValue;
  }

  public ImageView getAddView() {
    return mAdd;
  }

  public ImageView getMinusView() {
    return mMinus;
  }
}
