package app.positiveculture.com.agent.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HaiLS on 02/09/2017.
 */

public class ProcessTracker extends LinearLayout {

  @BindView(R2.id.tracker_overall_status_iv)
  View overallStatusIv;
  @BindView(R2.id.tracker_overall_title_tv)
  TextView overallTitleTv;
  @BindView(R2.id.tracker_top_line)
  View overallTopLine;
  @BindView(R2.id.tracker_bottom_line)
  View overallBottomLine;
  @BindView(R2.id.tracker_first_process_ll)
  LinearLayout firstProcessLl;
  @BindView(R2.id.tracker_first_status_iv)
  View firstInnerStatusIv;
  @BindView(R2.id.tracker_first_title_tv)
  TextView firstInnerProcessTitleTv;
  @BindView(R2.id.tracker_second_process_ll)
  LinearLayout secondProcessLl;
  @BindView(R2.id.tracker_second_status_iv)
  View secondInnerStatusIv;
  @BindView(R2.id.tracker_second_title_tv)
  TextView secondInnerProcessTitleTv;
  @BindView(R2.id.tracker_middle_line)
  View middleInnerLine;
  @BindView(R2.id.tracker_process_next_iv)
  ImageView nextIv;

  public ProcessTracker(Context context) {
    super(context);
    init(null, 0);
  }

  public ProcessTracker(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(attrs, 0);
  }

  public ProcessTracker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(attrs, defStyleAttr);
  }

  private void init(AttributeSet attrs, int defStyleAttr) {
    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.item_process_tracker, this, true);

    ButterKnife.bind(this);

    final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ProcessTracker, defStyleAttr, 0);

    String overallProcessTitle = a.getString(R.styleable.ProcessTracker_overall_process_title);
    boolean isHideTopLine = a.getBoolean(R.styleable.ProcessTracker_hide_top_line, false);
    boolean isHideBottomLine = a.getBoolean(R.styleable.ProcessTracker_hide_bottom_line, false);
    int numberOfProcess = a.getInt(R.styleable.ProcessTracker_number_of_process, 0);
    String firstInnerProcessTitle = a.getString(R.styleable.ProcessTracker_first_inner_process_title);
    String secondInnerProcessTitle = a.getString(R.styleable.ProcessTracker_second_inner_process_title);

    if (!StringUtils.isEmpty(overallProcessTitle)) {
      overallTitleTv.setText(overallProcessTitle);
    }
    if (isHideTopLine) {
      overallTopLine.setVisibility(INVISIBLE);
    }
    if (isHideBottomLine) {
      overallBottomLine.setVisibility(INVISIBLE);
    }
    if (numberOfProcess == 0) {
      firstProcessLl.setVisibility(GONE);
      middleInnerLine.setVisibility(GONE);
      secondProcessLl.setVisibility(GONE);
    } else if (numberOfProcess == 1) {
      middleInnerLine.setVisibility(GONE);
      secondProcessLl.setVisibility(GONE);
    }
    if (!StringUtils.isEmpty(firstInnerProcessTitle)) {
      firstInnerProcessTitleTv.setText(firstInnerProcessTitle);
    }
    if (!StringUtils.isEmpty(secondInnerProcessTitle)) {
      secondInnerProcessTitleTv.setText(secondInnerProcessTitle);
    }

    a.recycle();
  }

  public void setInProgressStatus(View view) {
    view.setBackground(getResources().getDrawable(R.drawable.yellow_circle_bg));
  }

  public void setNoProgressStatus(View view) {
    view.setBackground(getResources().getDrawable(R.drawable.grey_circle_bg));
  }

  public void setCompletedStatus(View view) {
    view.setBackground(getResources().getDrawable(R.drawable.blue_circle_bg));
  }

  public View getOverallStatusIv() {
    return overallStatusIv;
  }

  public View getFirstInnerStatusIv() {
    return firstInnerStatusIv;
  }

  public View getSecondInnerStatusIv() {
    return secondInnerStatusIv;
  }

  public TextView getOverallTitleTv() {
    return overallTitleTv;
  }

  public ImageView getNextIv() {
    return nextIv;
  }

  public void setOverallProcessTitle(String title) {
    overallTitleTv.setText(title);
  }

  public void setFirstInnerProcessTitle(String title) {
    firstInnerProcessTitleTv.setText(title);
  }

  public void setSecondInnerProcessTitle(String title) {
    secondInnerProcessTitleTv.setText(title);
  }

}
