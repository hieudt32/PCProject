package app.positiveculture.com.agent.screen.properties.createotp;


import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.positiveculture.com.agent.Constant;
import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.ItemSummaryCustom;
import app.positiveculture.com.agent.utils.NumberUtils;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.enumdata.StatusAndOrNominee;
import app.positiveculture.com.data.enumdata.TypeFurnishing;
import app.positiveculture.com.data.enumdata.TypeProperty;
import app.positiveculture.com.data.enumdata.TypeTenancy;
import app.positiveculture.com.data.response.dto.AgentDTO;
import app.positiveculture.com.data.response.dto.CreateOtpDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.data.response.dto.SolicitorDTO;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;
import customview.CustomButton;
import customview.CustomCreateOtp;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DateTimeUtils;
import utils.DialogUtils;

/**
 * The CreateOtp Fragment
 */
public class CreateOtpFragment extends ViewFragment<CreateOtpContract.Presenter> implements CreateOtpContract.View,
        CustomCreateOtp.OnDateChooseListener {

  @BindView(R2.id.close_create_otp_hv)
  CustomHeaderView mHeader;
  @BindView(R2.id.next_otp_contract)
  CustomButton mButtonGenerate;
  @BindView(R2.id.and_or_nominee_sw)
  Switch mNomineeSw;
  @BindView(R2.id.hdb_serial_number_cc)
  CustomCreateOtp mHdbSerialNumber;
  @BindView(R2.id.hdb_timestamp_cc)
  CustomCreateOtp mHdbTimeStamp;
  @BindView(R2.id.hdb_appointment_cc)
  CustomCreateOtp mHdbAppointment;
  @BindView(R2.id.otp_start_date_cc)
  CustomCreateOtp mDateOtpStart;
  @BindView(R2.id.avatar_member_otp)
  SimpleDraweeView mAvatar;
  @BindView(R2.id.member_next_tv)
  TextView mNameAgent;
  @BindView(R2.id.weeks_to_expiry_cc)
  CustomCreateOtp mWeekExpiry;
  @BindView(R2.id.weeks_to_completion)
  CustomCreateOtp mWeekCompletion;
  @BindView(R2.id.date_weeks_to_expiry)
  TextView mDateExpiry;
  @BindView(R2.id.date_weeks_to_completion)
  TextView mDateCompletion;
  @BindView(R2.id.solicitor_next_sc)
  ItemSummaryCustom mSolicitor;
  @BindView(R2.id.create_furnishing_ic)
  ItemSummaryCustom mFurnishing;
  @BindView(R2.id.create_tenancy_ic)
  ItemSummaryCustom mTenancy;
  @BindView(R2.id.total_price_cc)
  CustomCreateOtp mTotalPrice;
  @BindView(R2.id.option_fee_cc)
  CustomCreateOtp mOptionFee;
  @BindView(R2.id.exercise_fee_cc)
  CustomCreateOtp mExerciseFee;
  @BindView(R2.id.exact_amount_cc)
  CustomCreateOtp mExactAmount;
  @BindView(R2.id.percent_cc)
  CustomCreateOtp mPercent;
  PropertyDTO mPropertyDTO;

  private int defaultWeekExpiry = Constant.DEFAULT_VALUE_OTP.NON_HDB_WEEK_EXPIRY;

  @OnTouch(R2.id.create_otp_ll)
  boolean touchOutSide() {
    getBaseActivity().hideKeyboard();
    return false;
  }

  @OnClick(R2.id.select_agent_rl)
  void doSelectAgent() {
    getBaseActivity().hideKeyboard();
    mPresenter.gotoSelectAgent();
  }

  @OnClick(R2.id.next_otp_contract)
  void doGenerateClick() {
    getBaseActivity().hideKeyboard();
    if (generateCreateOTP() != null) {
      mPresenter.createOTP(generateCreateOTP());
    }
  }

  @OnClick(R2.id.right_text_tv)
  void saveEditOTP() {
    getBaseActivity().hideKeyboard();
    if (generateCreateOTP() != null) {
      mPresenter.updateOTP(generateCreateOTP());
    }
  }

  @Override
  public void showDialog(String message, final EditText viewRequestFocus) {
    DialogUtils.showDialog(getViewContext(), getString(R.string.error), message, new CustomDialog.OnConfirmSelected() {
      @Override
      public void onConfirmSelected() {
        if (viewRequestFocus != null) {
          viewRequestFocus.requestFocus();
          getBaseActivity().forceShowKeyboard();
        }
      }
    });
  }

  public static CreateOtpFragment getInstance() {
    return new CreateOtpFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_create_otp;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mDateOtpStart.selectDate(DateTimeUtils.dateFormatTwo(), Calendar.getInstance(), false);
    formatNumber(mTotalPrice.getInputCreate());
    formatNumber(mExerciseFee.getInputCreate());
    formatNumber(mOptionFee.getInputCreate());

    mExactAmount.getInputCreate().addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        mExactAmount.getInputCreate().removeTextChangedListener(this);
        NumberUtils.formatIntegerEditTextRunTime(mExactAmount.getInputCreate());
        mExactAmount.getInputCreate().addTextChangedListener(this);
      }
    });
    mButtonGenerate.statusEnableButton();
    mDateOtpStart.setOnDateChooseListener(this);
    mTotalPrice.getInputCreate().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    mOptionFee.getInputCreate().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    mExerciseFee.getInputCreate().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    mPercent.getInputCreate().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    mExactAmount.getInputCreate().setInputType(InputType.TYPE_CLASS_NUMBER);

    mHeader.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        getBaseActivity().hideKeyboard();
        mPresenter.back();
      }

      @Override
      public void onRightIconClick() {

      }
    });
    mSolicitor.setClickButtonNextItem(new ItemSummaryCustom.OnClickButtonNextItem() {
      @Override
      public void onNextButtonClick() {
        mPresenter.gotoSolicitor();
      }
    });
    mFurnishing.setClickButtonNextItem(new ItemSummaryCustom.OnClickButtonNextItem() {
      @Override
      public void onNextButtonClick() {
        mPresenter.gotoFurnishing();
      }
    });
    mTenancy.setClickButtonNextItem(new ItemSummaryCustom.OnClickButtonNextItem() {
      @Override
      public void onNextButtonClick() {
        mPresenter.gotoTenancy();
      }
    });


    mWeekExpiry.getInputCreate().addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
      }

      @Override
      public void afterTextChanged(Editable s) {
        changeDateOTP();
      }
    });

    mWeekCompletion.getInputCreate().addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
      }

      @Override
      public void afterTextChanged(Editable s) {
        changeDateOTP();
      }
    });
  }

  private void changeDateOTP() {
    int weekExpiry = NumberUtils.getNumberInteger(mWeekExpiry.getInputCreate().getText().toString());
    int weekCompletion = NumberUtils.getNumberInteger(mWeekCompletion.getInputCreate().getText().toString());
    setDateOTP(mDateOtpStart.getCalendar(), weekExpiry, weekCompletion);
  }

  @Override
  public void selectedAgent(AgentDTO selectedAgent) {
    if (selectedAgent != null) {
      if (selectedAgent.getAvatar() != null) {
        ViewUtils.loadAvatarWithFresco(mAvatar, selectedAgent.getAvatar().getImgSmall());
      } else {
        mAvatar.setImageResource(R.drawable.avatar_icon);
      }
      mNameAgent.setText(selectedAgent.getUsers().getFullName());
    } else {
      mAvatar.setImageResource(R.drawable.avatar_icon);
      mNameAgent.setText(getResources().getString(R.string.assign_buyer_agent));
    }
  }

  @Override
  public void bindSolicitorInfo(SolicitorDTO solicitorDTO) {
    if (solicitorDTO != null) {
      mSolicitor.setContent(solicitorDTO.getSolicitorName());
    }
  }

  @Override
  public void onDateChoose(Calendar calendar) {
    changeDateOTP();
  }

  private void setDateOTP(Calendar calendar, int weekExpiry, int weekCompletion) {
    if (weekExpiry == 0) {
      weekExpiry = defaultWeekExpiry;
    }
    if (weekCompletion == 0) weekCompletion = Constant.DEFAULT_VALUE_OTP.WEEK_COMPLETION;
    weekCompletion += weekExpiry;
    int dayExpiry = weekExpiry * 7;
    int dayCompletion = weekCompletion * 7;
    calendar.add(Calendar.DAY_OF_MONTH, dayExpiry);
    mDateExpiry.setText(DateTimeUtils.dateFormatOne().format(calendar.getTime()));
    calendar.add(Calendar.DAY_OF_MONTH, -dayExpiry);

    calendar.add(Calendar.DAY_OF_MONTH, dayCompletion);
    mDateCompletion.setText(DateTimeUtils.dateFormatOne().format(calendar.getTime()));
    calendar.add(Calendar.DAY_OF_MONTH, -dayCompletion);
  }

  private void formatNumber(final EditText mEditText) {
    mEditText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
      }

      @Override
      public void afterTextChanged(Editable s) {
        mEditText.removeTextChangedListener(this);
        NumberUtils.formatDoubleEditTextRunTime(mEditText);
        mEditText.addTextChangedListener(this);
      }
    });
  }


  private TextView.OnEditorActionListener editorAction(final EditText editText) {
    TextView.OnEditorActionListener editorAction = new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_NEXT) {
          editText.requestFocus();
        }
        return true;
      }
    };
    return editorAction;
  }

  @Override
  public void bindViewFromData(PropertyDTO propertyDTO) {
    mPropertyDTO = propertyDTO;
    List<EditText> editTextList = new ArrayList<>();
    editTextList.add(mHdbSerialNumber.getInputCreate());
    editTextList.add(mHdbAppointment.getInputCreate());
    editTextList.add(mWeekExpiry.getInputCreate());
    editTextList.add(mWeekCompletion.getInputCreate());
    editTextList.add(mTotalPrice.getInputCreate());
    editTextList.add(mOptionFee.getInputCreate());
    editTextList.add(mExerciseFee.getInputCreate());
    editTextList.add(mExactAmount.getInputCreate());
    editTextList.add(mPercent.getInputCreate());
    for (int i = 0; i < editTextList.size() - 1; i++) {
      editTextList.get(i).setOnEditorActionListener(editorAction(editTextList.get(i + 1)));
    }
    editTextList.get(editTextList.size() - 1).setImeOptions(EditorInfo.IME_ACTION_DONE);
    if (propertyDTO.getmType() == TypeProperty.hdb) {
      mHdbSerialNumber.setVisibility(View.VISIBLE);
      mHdbTimeStamp.setVisibility(View.VISIBLE);
      mHdbTimeStamp.selectDate(DateTimeUtils.dateFormatOne(), Calendar.getInstance(), true);
      mHdbAppointment.setVisibility(View.VISIBLE);
      mTenancy.setVisibility(View.GONE);
      defaultWeekExpiry = Constant.DEFAULT_VALUE_OTP.HDB_WEEK_EXPIRY;
      mWeekExpiry.getInputCreate().setHint("3");
      mOptionFee.getInputCreate().addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
          if (StringUtils.isEmpty(s)) {
            mExerciseFee.setTextInput(null);
          } else {
            double exerciseFree = Constant.DEFAULT_VALUE_OTP.HDB_FREE - NumberUtils.getNumberDouble(s.toString());
            if (exerciseFree > 0) {
              mExerciseFee.setTextInput(NumberUtils.formatNumber(exerciseFree));
            } else {
              mExerciseFee.setTextInput("0");
            }
          }
        }
      });
    } else {
      mHdbSerialNumber.setVisibility(View.GONE);
      mHdbTimeStamp.setVisibility(View.GONE);
      mHdbAppointment.setVisibility(View.GONE);
      mTenancy.setVisibility(View.VISIBLE);
      mTotalPrice.getInputCreate().addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
          if (StringUtils.isEmpty(s)) {
            mOptionFee.setTextInput(null);
            mExerciseFee.setTextInput(null);
          } else {
            double totalPrice = NumberUtils.getNumberDouble(s.toString());
            double optionFree = (totalPrice / 100) * Constant.DEFAULT_VALUE_OTP.NON_HDB_OPTION_FREE;
            double exerciseFree = (totalPrice / 100) * Constant.DEFAULT_VALUE_OTP.NON_HDB_EXERCISE_FREE;
            mOptionFee.setTextInput(NumberUtils.formatNumber(optionFree));
            mExerciseFee.setTextInput(NumberUtils.formatNumber(exerciseFree));
          }
        }
      });
    }
    setDateOTP(Calendar.getInstance(), defaultWeekExpiry, Constant.DEFAULT_VALUE_OTP.WEEK_COMPLETION);
  }

  @Override
  public void bindViewFurnishing(TypeFurnishing typeFurnishing) {
    if (typeFurnishing == TypeFurnishing.sold_furnished) {
      mFurnishing.setContent(getString(R.string.furnished));
    } else {
      mFurnishing.setContent(getString(R.string.unfurnished));
    }
  }

  @Override
  public void bindViewTenancy(TypeTenancy typeTenancy) {
    if (typeTenancy == TypeTenancy.existing_tenancy) {
      mTenancy.setContent(getString(R.string.existing_tenancy));
    } else {
      mTenancy.setContent(getString(R.string.vacant_possession));
    }
  }

  private CreateOtpDTO generateCreateOTP() {
    CreateOtpDTO createOtpDTO = new CreateOtpDTO();

    if (mPropertyDTO.getmType() == TypeProperty.hdb) {
      String hdbSerial = mHdbSerialNumber.getTextInput();
      String hdbTimeStamp = DateTimeUtils.dateTimeFormat().format(mHdbTimeStamp.getCalendar().getTime());
      int hdbAppointment = NumberUtils.getNumberInteger(mHdbAppointment.getTextInput());
      if (hdbAppointment == 0) hdbAppointment = Constant.DEFAULT_VALUE_OTP.HDB_APPT;
      if (hdbSerial.length() < 10) {
        showDialog(getString(R.string.hdb_serial_number_error), mHdbSerialNumber.getInputCreate());
        return null;
      }
      createOtpDTO.setHdbSerialNumber(hdbSerial);
      createOtpDTO.setHdbOtpTimestamp(hdbTimeStamp);
      createOtpDTO.setHdbAppointmentDays(hdbAppointment);
    }

    String otpStartDate = DateTimeUtils.dateFormatFour().format(mDateOtpStart.getCalendar().getTime());
    int weekExpiry = NumberUtils.getNumberInteger(mWeekExpiry.getTextInput());
    if (weekExpiry == 0) weekExpiry = defaultWeekExpiry;
    String otpDateExpiry = DateTimeUtils.convertDateFormat(mDateExpiry.getText().toString(),
            DateTimeUtils.dateFormatOne(), DateTimeUtils.dateFormatFour());
    int weekCompletion = NumberUtils.getNumberInteger(mWeekCompletion.getTextInput());
    if (weekCompletion == 0) weekCompletion = Constant.DEFAULT_VALUE_OTP.WEEK_COMPLETION;
    String otpDateCompletion = DateTimeUtils.convertDateFormat(mDateCompletion.getText().toString(),
            DateTimeUtils.dateFormatOne(), DateTimeUtils.dateFormatFour());

    double totalPrice = NumberUtils.getNumberDouble(mTotalPrice.getTextInput());
    if (totalPrice == 0) {
      showDialog("Total Price not empty", mTotalPrice.getInputCreate());
      return null;
    }


    double optionFree = NumberUtils.getNumberDouble(mOptionFee.getTextInput());
    if (optionFree == 0) {
      showDialog("Option free not empty", mOptionFee.getInputCreate());
      return null;
    }

    double exerciseFree = NumberUtils.getNumberDouble(mExerciseFee.getTextInput());

    int exactAmount = NumberUtils.getNumberInteger(mExactAmount.getTextInput());
    int percent = NumberUtils.getNumberInteger(mPercent.getTextInput());
    if (exactAmount == 0 && percent == 0) {
      showDialog("Exact Mount or Percent need fill", mExactAmount.getInputCreate());
      return null;
    }

    createOtpDTO.setOtpStartDate(otpStartDate);
    createOtpDTO.setWeekToExpiry(weekExpiry);
    createOtpDTO.setOtpDateExpiry(otpDateExpiry);
    createOtpDTO.setWeekToCompletion(weekCompletion);
    createOtpDTO.setOtpDateCompletion(otpDateCompletion);
    createOtpDTO.setTotalPrice(totalPrice);
    createOtpDTO.setOptionFee(optionFree);
    createOtpDTO.setExerciseFee(exerciseFree);
    createOtpDTO.setCommissionExactAmount(exactAmount);
    createOtpDTO.setCommissionPercent(percent);
    if (mNomineeSw.isChecked()) {
      createOtpDTO.setAndOrNominee(StatusAndOrNominee.on);
    } else {
      createOtpDTO.setAndOrNominee(StatusAndOrNominee.off);
    }
    return createOtpDTO;
  }

  @Override
  public void bindViewForEditOTP(OtpDTO mOTP) {
    Calendar calendar = Calendar.getInstance();
    if (mPropertyDTO.getmType() == TypeProperty.hdb) {
      mHdbSerialNumber.setTextInput(mOTP.getHdbSerialNumber());
      mHdbAppointment.setTextInput(String.valueOf(mOTP.getHdbAppointmentDays()));
      try {
        calendar.setTime(DateTimeUtils.dateTimeFormatOne().parse(mOTP.getHdbOtpTimestamp()));
      } catch (ParseException e) {
        e.printStackTrace();
      }
      mHdbTimeStamp.selectDate(DateTimeUtils.dateFormatOne(), calendar, true);
    }

    try {
      calendar.setTime(DateTimeUtils.dateFormatFour().parse(mOTP.getOtpDateCompletion()));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    mDateOtpStart.selectDate(DateTimeUtils.dateFormatTwo(), calendar, false);
    mWeekExpiry.setTextInput(String.valueOf(mOTP.getWeekToExpiry()));
    mWeekCompletion.setTextInput(String.valueOf(mOTP.getWeekToCompletion()));
    mDateCompletion.setText(DateTimeUtils.convertDateFormat(mOTP.getOtpDateCompletion(), DateTimeUtils.dateFormatFour(),
            DateTimeUtils.dateFormatOne()));
    mDateExpiry.setText(DateTimeUtils.convertDateFormat(mOTP.getOtpDateExpiry(), DateTimeUtils.dateFormatFour(),
            DateTimeUtils.dateFormatOne()));
    mTotalPrice.setTextInput(NumberUtils.formatNumber(mOTP.getTotalPrice()));
    mOptionFee.setTextInput(NumberUtils.formatNumber(mOTP.getOptionFee()));
    mExerciseFee.setTextInput(NumberUtils.formatNumber(mOTP.getExerciseFee()));
    mExactAmount.setTextInput(NumberUtils.formatNumber(mOTP.getCommissionExactAmount()));
    mPercent.setTextInput(NumberUtils.formatNumber(mOTP.getCommissionPercent()));
    mButtonGenerate.setVisibility(View.GONE);
    mHeader.getRightTextTv().setVisibility(View.VISIBLE);
    mHeader.getRightTextTv().setText(getString(R.string.save));
  }
}
