package app.positiveculture.com.user.screen.extentotp;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.BindView;
import butterknife.OnClick;
import utils.DateTimeUtils;

/**
 * The ExtentOTP Fragment
 */
public class ExtentOTPFragment extends ViewFragment<ExtentOTPContract.Presenter> implements ExtentOTPContract.View {

  @BindView(R2.id.otp_completion_date_layout)
  LinearLayout mOtpCompletionDateLayout;
  @BindView(R2.id.otp_completion_date_tv)
  TextView mOtpCompletionDateTv;
  private Calendar mCalender= Calendar.getInstance();
  private Date mDate;

  @OnClick(R2.id.right_text_tv)
  void doSaveClick() {
    mPresenter.extendOTP(DateTimeUtils.dateFormatFour().format(mCalender.getTime()));
  }

  @Override
  public void initLayout() {
    super.initLayout();
  }

  public static ExtentOTPFragment getInstance() {
    return new ExtentOTPFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_extent_otp;
  }

  @OnClick(R2.id.left_icon_iv)
  public void back() {
    mPresenter.back();
  }

  @Override
  public void bindOtpInfo(String dateCompletion) {
    try {
      this.mDate = DateTimeUtils.dateFormatFour().parse(dateCompletion);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    String otpCompletionDate = DateTimeUtils.convertDateFormat(dateCompletion,
            DateTimeUtils.dateFormatFour(), DateTimeUtils.dateFormatTwo());
    mOtpCompletionDateTv.setText(otpCompletionDate);
    mOtpCompletionDateLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mCalender.setTime(mDate);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                ExtentOTPFragment.this.getViewContext(),
                new DatePickerDialog.OnDateSetListener() {
                  @Override
                  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mCalender.set(Calendar.YEAR, year);
                    mCalender.set(Calendar.MONTH, month);
                    mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    bindOtpInfo(DateTimeUtils.dateFormatFour().format(mCalender.getTime()));
                  }
                }, mCalender.get(Calendar.YEAR), mCalender.get(Calendar.MONTH), mCalender.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
      }
    });
  }

}
