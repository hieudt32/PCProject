package customview;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gemvietnam.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import font.app.gem.widgetlibrary.R;
import font.app.gem.widgetlibrary.R2;
import utils.DateTimeUtils;


/**
 * CustomCreateOtp
 * Created by Chinh on 8/17/2017.
 */
public class CustomCreateOtp extends LinearLayout {

  @BindView(R2.id.title_create_otp_tv)
  TextView mTitleCreate;
  @BindView(R2.id.input_create_otp_tv)
  EditText mInputCreate;
  @BindView(R2.id.text_start_create_otp)
  TextView mStartText;
  @BindView(R2.id.text_end_create_otp)
  TextView mEndText;
  @BindView(R2.id.select_date_tv)
  TextView mDate;
  Calendar mCalendar;
  private OnDateChooseListener mOnDateChooseListener;

  public void setOnDateChooseListener(OnDateChooseListener mOnDateChooseListener) {
    this.mOnDateChooseListener = mOnDateChooseListener;
  }


  public EditText getInputCreate() {
    return this.mInputCreate;
  }

  public TextView getEndText() {
    return this.mEndText;
  }

  public Calendar getCalendar() {
    return this.mCalendar;
  }

  public String getTextInput() {
    return mInputCreate.getText().toString();
  }

  public void setTextInput(String text) {
    mInputCreate.setText(text);
  }

  public CustomCreateOtp(Context context) {
    super(context);
    init(context, null);
  }

  public CustomCreateOtp(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public CustomCreateOtp(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  public void init(Context context, AttributeSet attrs) {
    LayoutInflater inflater = LayoutInflater.from(context);
    inflater.inflate(R.layout.custom_create_otp, this, true);
    ButterKnife.bind(this);

    final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomCreateOtp, 0, 0);
    String text = a.getString(R.styleable.CustomCreateOtp_custom_create_otp_text);
    String editText = a.getString(R.styleable.CustomCreateOtp_custom_create_otp_text_hint);
    String textStart = a.getString(R.styleable.CustomCreateOtp_custom_text_start_otp);
    String textEnd = a.getString(R.styleable.CustomCreateOtp_custom_text_end_otp);
    if (!StringUtils.isEmpty(text))
      mTitleCreate.setText(text);


    if (!StringUtils.isEmpty(editText)) {
      mInputCreate.setHint(editText);
      if (StringUtils.isEmpty(textEnd))
        mInputCreate.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
    }

    if (!StringUtils.isEmpty(textStart)) {
      mStartText.setVisibility(VISIBLE);
      mStartText.setText(textStart);
      mEndText.setVisibility(GONE);
      mInputCreate.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
              RelativeLayout.LayoutParams.MATCH_PARENT));
    } else {
      mStartText.setVisibility(GONE);
    }

    if (StringUtils.isEmpty(textEnd)) {
      mEndText.setVisibility(GONE);
    } else {
      mEndText.setVisibility(VISIBLE);
      mEndText.setText(textEnd);
      mStartText.setVisibility(GONE);
    }
    a.recycle();
  }

  public void selectDate(final SimpleDateFormat sdf, final Calendar calendar, final boolean showTime) {
    mInputCreate.setVisibility(GONE);
    mDate.setVisibility(VISIBLE);
    if (showTime) {
      mEndText.setVisibility(VISIBLE);
      mEndText.setText(DateTimeUtils.timeFormatOne().format(calendar.getTime()));
    }
    mDate.setText(sdf.format(calendar.getTime()));
    mCalendar = calendar;
    mDate.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                  @Override
                  public void onDateSet(DatePicker view, int year, int monthOfYear, int day) {

                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, day);
                    mCalendar = calendar;
                    mDate.setText(sdf.format(calendar.getTime()));
                    if (showTime)
                      mEndText.setText(DateTimeUtils.timeFormatOne().format(new Date()));
                    if (mOnDateChooseListener != null) mOnDateChooseListener.onDateChoose(calendar);
                  }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
      }
    });
  }

  public interface OnDateChooseListener {
    void onDateChoose(Calendar calendar);
  }

}