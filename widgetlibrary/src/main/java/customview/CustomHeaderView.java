package customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gemvietnam.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import font.app.gem.widgetlibrary.R;
import font.app.gem.widgetlibrary.R2;

/**
 * CustomHeaderView
 * Created by hungdn on 8/7/2017.
 */

public class CustomHeaderView extends LinearLayout {

  @BindView(R2.id.header_title_tv)
  TextView mHeaderTitleTv;
  @BindView(R2.id.right_icon_iv)
  ImageView mRightIconIv;
  @BindView(R2.id.left_icon_iv)
  ImageView mLeftIconIv;
  @BindView(R2.id.left_text_tv)
  TextView mLeftTextTv;
  @BindView(R2.id.right_text_tv)
  TextView mRightTextTv;
  @BindView(R2.id.right_icon2_iv)
  ImageView mRightIcon2Iv;

  @OnClick(R2.id.left_icon_iv)
  void onClickLeftIcon() {
    if (mOnHeaderEventListener != null) {
      mOnHeaderEventListener.onLeftIconClick();
    }
  }

  @OnClick(R2.id.right_icon_iv)
  void onClickRightIcon() {
    if (mOnHeaderEventListener != null) {
      mOnHeaderEventListener.onRightIconClick();
    }
  }

  private OnHeaderEventListener mOnHeaderEventListener;

  public CustomHeaderView(Context context) {
    super(context);
    init(null, 0);
  }

  public CustomHeaderView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs, 0);
  }

  public CustomHeaderView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(attrs, defStyle);
  }

  public void setOnHeaderEventListener(OnHeaderEventListener onHeaderEventListener) {
    mOnHeaderEventListener = onHeaderEventListener;
  }

  private void init(AttributeSet attrs, int defStyle) {
    LayoutInflater inflater = (LayoutInflater) getContext()
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.custom_header, this, true);

    ButterKnife.bind(this);

    // Load attributes
    final TypedArray a = getContext().obtainStyledAttributes(
            attrs, R.styleable.CustomHeaderView, defStyle, 0);
    String text = a.getString(R.styleable.CustomHeaderView_custom_header_title);
    if (!StringUtils.isEmpty(text)) {
      mHeaderTitleTv.setVisibility(VISIBLE);
      mHeaderTitleTv.setText(text);
    } else {
      mHeaderTitleTv.setVisibility(GONE);
    }

    String leftText = a.getString(R.styleable.CustomHeaderView_custom_header_left_text);
    if (!StringUtils.isEmpty(leftText)) {
      mLeftTextTv.setVisibility(VISIBLE);
      mLeftTextTv.setText(leftText);
    } else {
      mLeftTextTv.setVisibility(GONE);
    }

    String rightText = a.getString(R.styleable.CustomHeaderView_custom_header_right_text);
    if (!StringUtils.isEmpty(rightText)) {
      mRightTextTv.setVisibility(VISIBLE);
      mRightTextTv.setText(rightText);
    } else {
      mRightTextTv.setVisibility(GONE);
    }

    if (a.hasValue(R.styleable.CustomHeaderView_custom_header_left_action_ic_src)) {
      mLeftIconIv.setImageDrawable(a.getDrawable(
              R.styleable.CustomHeaderView_custom_header_left_action_ic_src));
      mLeftIconIv.setVisibility(VISIBLE);
    } else {
      mLeftIconIv.setVisibility(GONE);
    }

    if (a.hasValue(R.styleable.CustomHeaderView_custom_header_action_ic_src)) {
      mRightIconIv.setImageDrawable(a.getDrawable(
              R.styleable.CustomHeaderView_custom_header_action_ic_src));
    } else {
      mRightIconIv.setVisibility(GONE);
    }

    if (a.hasValue(R.styleable.CustomHeaderView_custom_header_right_action_ic_src)) {
      mRightIcon2Iv.setImageDrawable(a.getDrawable(
              R.styleable.CustomHeaderView_custom_header_right_action_ic_src));
    } else {
      mRightIcon2Iv.setVisibility(GONE);
    }
    a.recycle();
  }

  public void setTitle(String title) {
    mHeaderTitleTv.setText(title);
  }

  public TextView getLeftTextTv() {
    return mLeftTextTv;
  }

  public TextView getRightTextTv() {
    return mRightTextTv;
  }

  public ImageView getLeftIconIv() {
    return mLeftIconIv;
  }

  public ImageView getRightIcon2Iv() {
    return mRightIcon2Iv;
  }

  public void setRightText(String text) {
    mRightTextTv.setText(text);
    if (!mRightTextTv.isShown()) mRightTextTv.setVisibility(VISIBLE);
  }

  public interface OnHeaderEventListener {
    void onLeftIconClick();

    void onRightIconClick();
  }
}
