package font.app.gem.widget.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gemvietnam.utils.StringUtils;

import font.app.gem.widgetlibrary.R;

/**
 * CustomHeaderView
 * Created by hungdn on 8/7/2017.
 */

public class CustomHeaderView extends LinearLayout {

    TextView mHeaderTitleTv;

    ImageView mRightIconIv;

    ImageView mLeftIconIv;

    private OnHeaderEventListener mOnHeaderEventListener;

    public CustomHeaderView(Context context) {
        super(context);
        init(null, 0);
    }

    public CustomHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomHeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public void setOnHeaderEventListener(OnHeaderEventListener onHeaderEventListener) {
        mOnHeaderEventListener = onHeaderEventListener;
    }

    private void init(AttributeSet attrs, int defStyle) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_header, this, true);

        mHeaderTitleTv = (TextView) findViewById(R.id.header_title_tv);
        mLeftIconIv = (ImageView) findViewById(R.id.left_icon_iv);
        mRightIconIv = (ImageView) findViewById(R.id.right_icon_iv);

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CustomHeaderView, defStyle, 0);
        String text = a.getString(R.styleable.CustomHeaderView_custom_header_title);
        if (!StringUtils.isEmpty(text)) {
            mHeaderTitleTv.setVisibility(VISIBLE);
            mHeaderTitleTv.setText(text);
        } else {
            mHeaderTitleTv.setVisibility(GONE);
        }


        if (a.hasValue(R.styleable.CustomHeaderView_custom_header_left_action_ic_src)) {
            mLeftIconIv.setImageDrawable(a.getDrawable(
                    R.styleable.CustomHeaderView_custom_header_left_action_ic_src));
//            int padding_in_dp = 10;
//            final float scale = getResources().getDisplayMetrics().density;
//            int padding_in_px = (int) (padding_in_dp * scale + 0.5f);
//            mLeftIconIv.setPadding(padding_in_px, padding_in_px, padding_in_px, padding_in_px);
        }

        if (a.hasValue(R.styleable.CustomHeaderView_custom_header_action_ic_src)) {
            mRightIconIv.setImageDrawable(a.getDrawable(
                    R.styleable.CustomHeaderView_custom_header_action_ic_src));

        } else {
            mRightIconIv.setVisibility(GONE);
        }

        mLeftIconIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLeftIcon();
            }
        });

        mRightIconIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRightIcon();
            }
        });
        a.recycle();
    }

    void onClickLeftIcon() {
        if (mOnHeaderEventListener != null) {
            mOnHeaderEventListener.onLeftIconClick();
        }

    }

    void onClickRightIcon() {
        if (mOnHeaderEventListener != null) {
            mOnHeaderEventListener.onRightIconClick();
        }
    }

    public void setTitle(String title) {
        mHeaderTitleTv.setText(title);
    }

    public interface OnHeaderEventListener {
        void onLeftIconClick();
        void onRightIconClick();
    }


}
