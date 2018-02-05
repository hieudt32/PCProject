package app.positiveculture.com.agent.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 8/21/2017.
 */

public class TwoTextViewCustom extends LinearLayout {

    @BindView(R2.id.title_item_tv)
    TextView mTitle;
    @BindView(R2.id.content_item_tv)
    TextView mContent;

    public TwoTextViewCustom(Context context) {
        super(context);
        init(null, 0);
    }

    public TwoTextViewCustom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TwoTextViewCustom(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.property_detail_custom, this, true);
        ButterKnife.bind(this);
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomItemProperty, defStyle, 0);
        String title = a.getString(R.styleable.CustomItemProperty_title_item);
        String content = a.getString(R.styleable.CustomItemProperty_content_item);
        if (!StringUtils.isEmpty(title)) {
            mTitle.setText(title);
        }
        if (!StringUtils.isEmpty(content)) {
            mContent.setText(content);
        }
        a.recycle();
    }

    public void setTitleItem(String titleItem) {
        mTitle.setText(titleItem);
    }

    public void setContent(String contenItem) {
        mContent.setText(contenItem);
    }

}
