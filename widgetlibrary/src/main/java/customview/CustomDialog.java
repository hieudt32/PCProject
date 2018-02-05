package customview;


import android.app.Dialog;
import android.content.Context;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import font.app.gem.widgetlibrary.R;
import font.app.gem.widgetlibrary.R2;

/**
 * Created by Chinh on 8/15/2017.
 */

public class CustomDialog extends Dialog {

    @BindView(R2.id.title_dialog_tv)
    TextView mTitle;
    @BindView(R2.id.message_tv)
    TextView mMessage;
    @BindView(R2.id.dialog_btn)
    Button mTextLeftClick;
    @BindView(R2.id.dialog_click_btn)
    Button mTextRightClick;

    private OnDialogEventListener mOnDialogEventListener;

    public CustomDialog(@Nullable Context context, String title, String message , String button, String buttonRight) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        ButterKnife.bind(this);

        inits(title,message,button,buttonRight);

    }

    public void setOnDialogEventListener(OnDialogEventListener onDialogEventListener) {
        this.mOnDialogEventListener = onDialogEventListener;
    }

    void inits(String title, String message , String button, String buttonRight){
        mTitle.setText(title);
        mMessage.setText(message);
        mTextLeftClick.setText(button);
        mTextRightClick.setText(buttonRight);
        if (buttonRight==null){
            mTextRightClick.setVisibility(View.GONE);
        }
    }

    @OnClick(R2.id.dialog_btn)
    public void onClickLeftButton() {
        if (mOnDialogEventListener != null) {
            mOnDialogEventListener.onButtonLeftClick();
        }
    }

    @OnClick(R2.id.dialog_click_btn)
    void onClickRightButton(){
        if (mOnDialogEventListener != null) {
            mOnDialogEventListener.onButtonRightClick();
        }
    }

    public interface OnDialogEventListener {
        void onButtonLeftClick();
        void onButtonRightClick();
    }

}
