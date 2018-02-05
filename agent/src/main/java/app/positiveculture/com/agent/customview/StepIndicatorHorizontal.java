package app.positiveculture.com.agent.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.gemvietnam.utils.StringUtils;

import java.util.ArrayList;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * StepIndicatorHorizontal
 * Created by hungdn on 8/18/2017.
 */

public class StepIndicatorHorizontal extends LinearLayout {

  @BindView(R2.id.step1_view)
  View mStep1View;
  @BindView(R2.id.step2_view)
  View mStep2View;
  @BindView(R2.id.step3_view)
  View mStep3View;
  @BindView(R2.id.step4_view)
  View mStep4View;
  @BindView(R2.id.step5_view)
  View mStep5View;
  @BindView(R2.id.step6_view)
  View mStep6View;
  @BindView(R2.id.step7_view)
  View mStep7View;

  private ArrayList<View> mStepsView = new ArrayList<>();

  public StepIndicatorHorizontal(Context context) {
    super(context);
    init(null, 0);
  }

  public StepIndicatorHorizontal(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs, 0);
  }

  public StepIndicatorHorizontal(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(attrs, defStyle);
  }

  private void init(AttributeSet attrs, int defStyle) {
    LayoutInflater inflater = (LayoutInflater) getContext()
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.custom_step_indicator_horizontal, this, true);

    ButterKnife.bind(this);

    mStepsView.add(mStep1View);
    mStepsView.add(mStep2View);
    mStepsView.add(mStep3View);
    mStepsView.add(mStep4View);
    mStepsView.add(mStep5View);
    mStepsView.add(mStep6View);
    mStepsView.add(mStep7View);

    setCurrentStep(0);

  }

  public void setCurrentStep(int position) {
    for (int i = 0 ; i < mStepsView.size(); i++) {
      if (i < position) {
        mStepsView.get(i).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue));
      } else if (i == position) {
        mStepsView.get(i).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.current_step_color));
      } else {
        mStepsView.get(i).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.future_step_color));
      }
    }
  }
}
