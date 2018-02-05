package app.positiveculture.com.data.callback;

import android.app.Activity;
import android.content.Context;

import com.gemvietnam.utils.ActivityUtils;
import com.gemvietnam.utils.DialogUtils;
import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.data.R;
import app.positiveculture.com.data.enumdata.ErrorMessage;
import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import dialog.CustomDialog;

/**
 * Common callback for APIs
 * Created by NEO on 11/7/2016.
 */

public class CommonCallback<T> extends BaseCallback<T> {
  private Context mContext;

  public CommonCallback(Context context) {
    mContext = context;
  }

  @Override
  public void onSuccess(T data) {
    DialogUtils.dismissProgressDialog();
  }

  @Override
  public void onError(String errorCode, String errorMessage) {
    DialogUtils.dismissProgressDialog();

    ErrorMessage[] errorMessageList = ErrorMessage.values();
    String message = "";
    for (ErrorMessage msg : errorMessageList) {
      if (msg.toString().equals(errorMessage)) {
        message = mContext.getString(msg.getErrorMessageResId());
        break;
      }
    }

    if (StringUtils.isEmpty(message)) {
      message = mContext.getString(R.string.msg_server_error);
    }

    utils.DialogUtils.showDialog(mContext, mContext.getString(R.string.title_error), message, new CustomDialog.OnConfirmSelected() {
      @Override
      public void onConfirmSelected() {

      }
    });
  }

  @Override
  public void onError(final String errorCode, final ErrorDTO error) {
    DialogUtils.dismissProgressDialog();

    if (errorCode.equals("ERROR_INVALID_TOKEN")) {
      utils.DialogUtils.showDialog(mContext, mContext.getString(R.string.positive_culture_title), mContext.getString(R.string.invalid_pc_msg), new CustomDialog.OnConfirmSelected() {
        @Override
        public void onConfirmSelected() {
          PrefWrapper.remove(PrefWrapper.KEY_USER);
          PrefWrapper.remove(PrefWrapper.KEY_AGENT);
          PrefWrapper.remove(PrefWrapper.KEY_MEMBER);
          ActivityUtils.restart((Activity) mContext);
        }
      });
      return;
    }

    utils.DialogUtils.showDialog(mContext, error.getTitle(), error.getErrorMessage(), new CustomDialog.OnConfirmSelected() {
      @Override
      public void onConfirmSelected() {

      }
    });
  }

  @Override
  public String getServerMsg() {
    return mContext.getString(R.string.msg_server_error);
  }
}
