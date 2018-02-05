package customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import font.app.gem.widgetlibrary.R;
import font.app.gem.widgetlibrary.R2;

/**
 * Created by Chinh on 8/17/2017.
 */

public class CustomOTPItem extends LinearLayout {

    @BindView(R2.id.otp_item_status_view)
    View mStatusView;
    @BindView(R2.id.otp_item_name_tv)
    TextView mNameTv;
    @BindView(R2.id.otp_item_type_tv)
    TextView mTypeTv;

    public CustomOTPItem(Context context) {
        super(context);
        init(context);
    }

    public CustomOTPItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomOTPItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.custom_otp_item, this, true);
        ButterKnife.bind(this);
    }

    public void setName(String name) {
        mNameTv.setText(name);
    }

    public void setType(String type) {
        mTypeTv.setText(type);
    }

    public void setStatusGrey() {
        mStatusView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.grey_circle_bg));
        mNameTv.setTextColor(ContextCompat.getColor(getContext(), R.color.tc_black38));
    }

    public void setStatusYellow() {
        mStatusView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.yellow_circle_bg));
        mNameTv.setTextColor(ContextCompat.getColor(getContext(), R.color.tc_black87));
    }

    public void setStatusBlue() {
        mStatusView.setBackground(getResources().getDrawable(R.drawable.blue_circle_bg));
    }

}
