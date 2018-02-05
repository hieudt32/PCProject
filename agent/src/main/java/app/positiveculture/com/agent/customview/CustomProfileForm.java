package app.positiveculture.com.agent.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HaiLS on 14/09/2017.
 */

public class CustomProfileForm extends LinearLayout {

    @BindView(R2.id.form_title_tv)
    TextView mTitleTv;
    @BindView(R2.id.form_content_tv)
    TextView mContentTv;
    @BindView(R2.id.form_content_et)
    EditText mContentEt;
    @BindView(R2.id.form_action_iv)
    ImageView mActionIv;

    public CustomProfileForm(Context context) {
        super(context);
        init(null, 0);
    }

    public CustomProfileForm(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomProfileForm(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_profile_form, this, true);

        ButterKnife.bind(this);

        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomProfileForm, defStyleAttr, 0);

        String title = a.getString(R.styleable.CustomProfileForm_form_title);
        if (title != null) {
            mTitleTv.setText(title);
        }
        if (a.hasValue(R.styleable.CustomProfileForm_title_text_color)) {
            mTitleTv.setTextColor(a.getInteger(R.styleable.CustomProfileForm_title_text_color, 0));
        }
        if (a.hasValue(R.styleable.CustomProfileForm_content_text_color)) {
            mContentEt.setTextColor(a.getInteger(R.styleable.CustomProfileForm_content_text_color, 0));
        }
        boolean isEditable = a.getBoolean(R.styleable.CustomProfileForm_is_editable, false);
        if (isEditable) {
            mContentTv.setVisibility(GONE);
            mContentEt.setVisibility(VISIBLE);
        }
        if (a.hasValue(R.styleable.CustomProfileForm_action_image)) {
            mActionIv.setVisibility(VISIBLE);
            mActionIv.setImageDrawable(a.getDrawable(R.styleable.CustomProfileForm_action_image));
        }

        a.recycle();
    }

    public TextView getTitleTv() {
        return mTitleTv;
    }

    public void setTitle(String title) {
        mTitleTv.setText(title);
    }

    public TextView getContentTv() {
        return mContentTv;
    }

    public void setContentTv(String content) {
        mContentTv.setText(content);
    }

    public EditText getContentEt() {
        return mContentEt;
    }

    public void setContentEt(String content) {
        mContentEt.setText(content);
    }

}
