package app.positiveculture.com.agent.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * UserIdTypeSpinner
 * Created by HaiLS on 26/09/2017.
 */

public class UserIdTypeSpinner extends RelativeLayout {

  private static final String[] types = {
          "NRIC/FIN",
          "CO.REG.NO"
  };

  @BindView(R2.id.user_id_type_spinner)
  Spinner mSpinner;

  public UserIdTypeSpinner(Context context) {
    super(context);
    init(null, 0);
  }

  public UserIdTypeSpinner(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs, 0);
  }

  public UserIdTypeSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(attrs, defStyleAttr);
  }

  private void init(AttributeSet attrs, int defStyleAttr) {
    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.user_id_type_spinner_layout, this, true);
    ButterKnife.bind(this);

    ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getContext(), R.layout.item_spinner, types);
    mSpinner.setAdapter(mAdapter);
  }

  public String getSelectedType() {
    if (mSpinner.getSelectedItemPosition() == 0) {
      return "nric";
    } else {
      return "co_reg_no";
    }
  }

  public Spinner getSpinner() {
    return mSpinner;
  }

}
