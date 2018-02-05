package app.positiveculture.com.agent.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import app.positiveculture.com.agent.utils.NumberUtils;
import butterknife.BindView;
import butterknife.OnClick;
import font.app.gem.widgetlibrary.R;
import font.app.gem.widgetlibrary.R2;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.gemvietnam.utils.StringUtils;

import java.util.Locale;

import butterknife.ButterKnife;
import utils.Constant;

/**
 * CustomSmallInput
 * Created by Chinh on 8/22/2017.
 */

public class CustomSmallInput extends LinearLayout {
  @BindView(R2.id.floor_size_et)
  EditText mFloorSize;
  @BindView(R2.id.floor_size_landed_et)
  EditText mFloorSizeLanded;
  @BindView(R2.id.sqft_rbt)
  RadioButton mSqft;
  @BindView(R2.id.sqm_rbt)
  RadioButton mSqm;
  @BindView(R2.id.floor_size_landed_ll)
  LinearLayout mFloorSizeLandedLayout;

  private float mFloorSizeSqft = 0;
  private float mFloorSizeSqm = 0;
  private float mFloorSizeLandedSqm = 0;
  private float mFloorSizeLandedSqft = 0;
  private boolean isEditFromUser = true;
  private boolean isSQM = false;

  @OnClick(R2.id.sqft_rbt)
  void onChangeToSQFT() {
    if (isSQM) {
      isEditFromUser = false;
      mFloorSize.setText(formatFloat(mFloorSizeSqft));
      mFloorSize.setSelection(mFloorSize.getText().length());
      mFloorSizeLanded.setText(formatFloat(mFloorSizeLandedSqft));
      mFloorSizeLanded.setSelection(mFloorSizeLanded.getText().length());
      isSQM = false;
    }
  }

  @OnClick(R2.id.sqm_rbt)
  void onChangeToSQM() {
    if (!isSQM) {
      isEditFromUser = false;
      mFloorSizeLanded.setText(formatFloat(mFloorSizeLandedSqm));
      mFloorSizeLanded.setSelection(mFloorSizeLanded.getText().length());
      mFloorSize.setText(formatFloat(mFloorSizeSqm));
      mFloorSize.setSelection(mFloorSize.getText().length());
      isSQM = true;
    }
  }

  public CustomSmallInput(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public CustomSmallInput(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  public CustomSmallInput(Context context) {
    super(context);
    init(context);
  }

  public void init(Context context) {
    LayoutInflater inflater = LayoutInflater.from(context);
    inflater.inflate(R.layout.custom_small_input, this, true);
    ButterKnife.bind(this);
    mFloorSize.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
      }

      @Override
      public void afterTextChanged(Editable s) {
        if (isEditFromUser) {
          if (mSqft.isChecked()) {
            if (!StringUtils.isEmpty(s)) {
              mFloorSizeSqft = Float.parseFloat(s.toString().replace(",", ""));
              mFloorSizeSqm = mFloorSizeSqft / Constant.CHANGE_ACREAGE.SQP_SQFT;
            } else {
              mFloorSizeSqm = mFloorSizeSqft = 0;
            }
          } else {
            if (!StringUtils.isEmpty(s)) {
              mFloorSizeSqm = Float.parseFloat(s.toString().replace(",", ""));
              mFloorSizeSqft = mFloorSizeSqm * Constant.CHANGE_ACREAGE.SQP_SQFT;
            } else {
              mFloorSizeSqft = mFloorSizeSqm = 0;
            }
          }
          mFloorSize.removeTextChangedListener(this);
          NumberUtils.formatDoubleEditTextRunTime(mFloorSize);
          mFloorSize.addTextChangedListener(this);
        }
      }

    });

    mFloorSizeLanded.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (isEditFromUser) {
          if (mSqft.isChecked()) {
            if (!StringUtils.isEmpty(s)) {
              mFloorSizeLandedSqft = Float.parseFloat(s.toString().replace(",", ""));
              mFloorSizeLandedSqm = mFloorSizeLandedSqft / Constant.CHANGE_ACREAGE.SQP_SQFT;
            } else {
              mFloorSizeLandedSqm = mFloorSizeLandedSqft = 0;
            }
          } else {
            if (!StringUtils.isEmpty(s)) {
              mFloorSizeLandedSqm = Float.parseFloat(s.toString().replace(",", ""));
              mFloorSizeLandedSqft = mFloorSizeLandedSqm * Constant.CHANGE_ACREAGE.SQP_SQFT;
            } else {
              mFloorSizeLandedSqft = mFloorSizeLandedSqm = 0;
            }
          }
          mFloorSizeLanded.removeTextChangedListener(this);
          NumberUtils.formatDoubleEditTextRunTime(mFloorSizeLanded);
          mFloorSizeLanded.addTextChangedListener(this);
        }
      }
    });

    mFloorSize.setOnTouchListener(new OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        isEditFromUser = true;
        return false;
      }
    });
    mFloorSizeLanded.setOnTouchListener(new OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        isEditFromUser = true;
        return false;
      }
    });
  }


  private String formatFloat(float value) {
    java.text.NumberFormat nf = java.text.NumberFormat.getInstance(Locale.US);
    nf.setMaximumFractionDigits(2);
    return nf.format(value);
  }

  public RadioButton getmSqft() {
    return mSqft;
  }

  public RadioButton getmSqm() {
    return mSqm;
  }

  public EditText getmFloorSize() {
    return mFloorSize;
  }

  public LinearLayout getmFloorSizeLandedLayout() {
    return mFloorSizeLandedLayout;
  }

  public EditText getmFloorSizeLanded() {
    return mFloorSizeLanded;
  }

  public float getValueFloorSize() {
    if (mSqft.isChecked()) return mFloorSizeSqft;
    return mFloorSizeSqm;
  }

  public float getValueFloorSizeLanded() {
    if (mSqft.isChecked()) return mFloorSizeLandedSqft;
    return mFloorSizeLandedSqm;
  }

}
