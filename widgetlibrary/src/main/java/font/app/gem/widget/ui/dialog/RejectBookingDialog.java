package font.app.gem.widget.ui.dialog;

import android.app.Dialog;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import font.app.gem.widgetlibrary.R;

/**
 * RejectBookingDialog
 * Created by NEO on 11/29/2016.
 */

public class RejectBookingDialog extends TranslucentDialog {
  public static final String TAG = RejectBookingDialog.class.getSimpleName();
  private OnItemSelected mOnItemSelected;
  private EditText mReasonEt;
  private TextView mRescheduleTimeTv;
  private TextView mRescheduleDateTv;
  private TextView mRescheduleOtherTv;

  /**
   * Create a new instance dialog
   */
  public static RejectBookingDialog newInstance(OnItemSelected onItemSelected) {
    return new RejectBookingDialog().setOnItemSelected(onItemSelected);
  }

  private int mSelectedPosition = 2;

  @Override
  protected int getLayoutRes() {
    return R.layout.dialog_reject_booking;
  }

  @Override
  protected void injectViews(Dialog dialog, View v) {
    mRescheduleTimeTv = (TextView) v.findViewById(R.id.dialog_reschedule_time_tv);
    mRescheduleDateTv = (TextView) v.findViewById(R.id.dialog_reschedule_date_tv);
    mRescheduleOtherTv = (TextView) v.findViewById(R.id.dialog_other_reason_tv);
    mReasonEt = (EditText) v.findViewById(R.id.dialog_other_reason_et);

    mRescheduleTimeTv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        selectItem(0);
      }
    });

    mRescheduleDateTv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        selectItem(1);
      }
    });

    mRescheduleOtherTv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        mReasonEt.setVisibility(View.VISIBLE);
        selectItem(2);
      }
    });

    v.findViewById(R.id.dialog_submit_tv).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
       processSubmit();

//        dismiss();
      }
    });

//    selectItem(0);
  }

  private void processSubmit() {
    String reason;
    switch (mSelectedPosition) {
      case 0:
        reason = mRescheduleTimeTv.getText().toString();
        break;
      case 1:
        reason = mRescheduleDateTv.getText().toString();
        break;
      case 2:
        reason = mReasonEt.getText().toString();
        break;
      default:
        reason = mReasonEt.getText().toString();
        break;
    }

    if (mOnItemSelected != null) {
      mOnItemSelected.onSelectItem(mSelectedPosition, reason);
    }
  }
  /**
   * Select item changes
   */
  private void selectItem(int itemPosition) {
    mSelectedPosition = itemPosition;

    switch (itemPosition) {
      case 0:
        mRescheduleTimeTv.setBackgroundResource(R.drawable.bt_round_pink_rectangle);
        mRescheduleTimeTv.setTextColor(ContextCompat.getColor(getContext(), R.color.white));

        mRescheduleDateTv.setBackgroundResource(R.drawable.bt_round_grey_rectangle);
        mRescheduleDateTv.setTextColor(ContextCompat.getColor(getContext(), R.color.bt_grey));
        mRescheduleOtherTv.setBackgroundResource(R.drawable.bt_round_grey_rectangle);
        mRescheduleOtherTv.setTextColor(ContextCompat.getColor(getContext(), R.color.bt_grey));
        mReasonEt.setVisibility(View.GONE);
        break;
      case 1:
        mRescheduleDateTv.setBackgroundResource(R.drawable.bt_round_pink_rectangle);
        mRescheduleDateTv.setTextColor(ContextCompat.getColor(getContext(), R.color.white));

        mRescheduleTimeTv.setBackgroundResource(R.drawable.bt_round_grey_rectangle);
        mRescheduleTimeTv.setTextColor(ContextCompat.getColor(getContext(), R.color.bt_grey));
        mRescheduleOtherTv.setBackgroundResource(R.drawable.bt_round_grey_rectangle);
        mRescheduleOtherTv.setTextColor(ContextCompat.getColor(getContext(), R.color.bt_grey));
        mReasonEt.setVisibility(View.GONE);
        break;
      case 2:
        mRescheduleOtherTv.setBackgroundResource(R.drawable.bt_round_pink_rectangle);
        mRescheduleOtherTv.setTextColor(ContextCompat.getColor(getContext(), R.color.white));

        mRescheduleDateTv.setBackgroundResource(R.drawable.bt_round_grey_rectangle);
        mRescheduleDateTv.setTextColor(ContextCompat.getColor(getContext(), R.color.bt_grey));
        mRescheduleTimeTv.setBackgroundResource(R.drawable.bt_round_grey_rectangle);
        mRescheduleTimeTv.setTextColor(ContextCompat.getColor(getContext(), R.color.bt_grey));
        mReasonEt.setVisibility(View.VISIBLE);
        break;
    }
  }

  public RejectBookingDialog setOnItemSelected(OnItemSelected onItemSelected) {
    mOnItemSelected = onItemSelected;
    return this;
  }

  /**
   * Dialog callback
   */
  public interface OnItemSelected {
    void onSelectItem(int itemPosition, String content);
  }
}
