package font.app.gem.widget.ui.dialog;

import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import font.app.gem.widgetlibrary.R;

/**
 * CustomerReviewDialog
 * Created by NEO on 12/7/2016.
 */

public class CustomerReviewDialog extends TranslucentDialog {
  private SeekBar mReviewSb;
  private EditText mCommentEt;
  private OnSubmitListener mOnSubmitListener;

  @Override
  protected int getLayoutRes() {
    return R.layout.dialog_customer_review;
  }

  @Override
  protected void injectViews(Dialog dialog, View v) {
    mReviewSb = (SeekBar) v.findViewById(R.id.customer_review_sb);
    mCommentEt = (EditText) v.findViewById(R.id.customer_review_et);
    Button submitBt = (Button) v.findViewById(R.id.customer_review_submit_bt);
    final TextView mValueTv = (TextView) v.findViewById(R.id.customer_review_value_tv);

    mReviewSb.setProgress(10);
    mReviewSb.setMax(20);
    mValueTv.setText(String.valueOf(getRatingValue(mReviewSb.getProgress())));

    mReviewSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
//        progress = progress / 0.5;
//        progress = progress * 0.5;
//        mValueTv.setText(String.valueOf(progress));
        mValueTv.setText(String.valueOf(getRatingValue(progress)));
        Log.e("@@@@", "" + getRatingValue(progress));
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });

    submitBt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (mOnSubmitListener != null) {
          mOnSubmitListener.onSubmit(getRatingValue(mReviewSb.getProgress()), mCommentEt.getText().toString());
        }
      }
    });

    closeBt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (mOnSubmitListener != null) {
          mOnSubmitListener.onCancel();
        }
        dismiss();
      }
    });
  }

  private float getRatingValue(int progress) {
//    return progress * 10.0f / mReviewSb.getMax();
    return (float) progress/2;
  }

  public CustomerReviewDialog setOnSubmitListener(OnSubmitListener onSubmitListener) {
    mOnSubmitListener = onSubmitListener;
    return this;
  }

  public interface OnSubmitListener {
    void onSubmit(float rating, String desc);
    void onCancel();
  }
}
