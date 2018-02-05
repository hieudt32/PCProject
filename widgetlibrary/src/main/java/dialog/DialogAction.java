package dialog;

/**
 * Created by luong on 24/08/2017.
 */

public class DialogAction {

    private String mActionName;
    private OnActionSelectedListener mListener;
    public DialogAction(String action, OnActionSelectedListener listener) {
        mActionName = action;
        mListener = listener;
    }

    public String getActionName() {
        return mActionName;
    }

    public OnActionSelectedListener getOnActionClickListener() {
        return mListener;
    }

    public interface OnActionSelectedListener {
        void onActionSelected();
    }

}
