package customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gemvietnam.utils.StringUtils;

import font.app.gem.widgetlibrary.R;
import font.app.gem.widgetlibrary.R2;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * CustomButton
 * Created by Chinh on 8/16/2017.
 */

public class CustomButton extends RelativeLayout {
  @BindView(R2.id.bg_button_rll)
  RelativeLayout mbgLayout;
  @BindView(R2.id.text_button_tv)
  TextView mTextButton;
  @BindView(R2.id.icon_button_iv)
  ImageView mIconButton;
  @BindView(R2.id.button_pg)
  ProgressBar mButtonPg;
  Animation mAnimation;


  public CustomButton(Context context) {
    super(context);
    init(null, 0);

  }

  public CustomButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs, 0);
  }

  public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(attrs, defStyleAttr);
  }


  public void init(AttributeSet attrs, int defStyleAttr) {
    LayoutInflater inflater = LayoutInflater.from(getContext());
    inflater.inflate(R.layout.custom_button, this, true);
    ButterKnife.bind(this);
    final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomButton, defStyleAttr, 0);
    String text = a.getString(R.styleable.CustomButton_custom_button_text);
    if (!StringUtils.isEmpty(text)) mTextButton.setText(text);
    a.recycle();
    mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_rotate_loading);
    statusDisabledButton();
    setEnabled(false);

    if (mButtonPg != null) {
      mButtonPg.setIndeterminate(true);
      mButtonPg.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
    }
  }

  public void statusDisabledButton() {
    setEnabled(false);
    mbgLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.charcoalGrey));
    if (mAnimation != null) mIconButton.clearAnimation();
    mIconButton.setVisibility(GONE);
    mTextButton.setVisibility(VISIBLE);
    mTextButton.setAlpha(0.5f);
    mButtonPg.setVisibility(GONE);
  }

  public void statusEnableButton() {
    setEnabled(true);
    if (mAnimation != null) mIconButton.clearAnimation();
    mbgLayout.setBackgroundColor(0xFFF95C00);
    mButtonPg.setVisibility(GONE);
    mTextButton.setVisibility(VISIBLE);
    mTextButton.setAlpha(1f);
    mIconButton.setVisibility(GONE);
  }

  public void statusLoadingButton() {
    setEnabled(false);
    if (!mIconButton.isShown()) mIconButton.setVisibility(VISIBLE);
    mbgLayout.setBackgroundColor(0xFFF95C00);
    mTextButton.setVisibility(GONE);
    mButtonPg.setVisibility(VISIBLE);
    mIconButton.setVisibility(GONE);

  }

  public void statusSuccessButton() {
    if (mAnimation != null) mIconButton.clearAnimation();
    mIconButton.setImageResource(R.drawable.ic_check);
    mbgLayout.setBackgroundColor(0xFFF95C00);
    mIconButton.setVisibility(VISIBLE);
    mButtonPg.setVisibility(GONE);
    mTextButton.setVisibility(GONE);
  }

  public void setTextButton(String text) {
    mTextButton.setText(text);
  }

}
