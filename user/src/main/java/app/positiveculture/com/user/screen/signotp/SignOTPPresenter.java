package app.positiveculture.com.user.screen.signotp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.google.gson.Gson;

import app.positiveculture.com.agent.utils.BitmapUtils;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.ErrorDTO;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.user.Constant;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.customview.CanvasView;

/**
 * The SignOTP Presenter
 */
public class SignOTPPresenter extends Presenter<SignOTPContract.View, SignOTPContract.Interactor>
        implements SignOTPContract.Presenter {

  private FileDTO mSignImage;
  private OtpDTO mOTP;
  private TypeProcess mTypeProcess;
  private final static String KEY_OTP_OBJECT = Constant.KEY_OTP_OBJECT;
  private final static String KEY_TYPE_PROCESS = Constant.KEY_TYPE_PROCESS;

  public SignOTPPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SignOTPContract.View onCreateView() {
    return SignOTPFragment.getInstance();
  }

  @Override
  public void start() {
    if (mView.getViewContext().getIntent().getExtras() == null || mView.getViewContext().getIntent().getExtras().get(KEY_OTP_OBJECT) == null)
      return;
    String otpObject = mView.getViewContext().getIntent().getExtras().getString(KEY_OTP_OBJECT);
    this.mOTP = new Gson().fromJson(otpObject, OtpDTO.class);
    this.mTypeProcess = TypeProcess.valueOf(mView.getViewContext().getIntent().getExtras().getString(KEY_TYPE_PROCESS));
  }

  @Override
  public void uploadSignImage(CanvasView mDraw) {
    new SaveImageSign().execute(mDraw);
  }

  @Override
  public SignOTPContract.Interactor onCreateInteractor() {
    return new SignOTPInteractor(this);
  }

  @SuppressLint("StaticFieldLeak")
  private class SaveImageSign extends AsyncTask<View, Void, String> {

    @Override
    protected String doInBackground(View... params) {
      return BitmapUtils.saveBitmap(getViewContext(), BitmapUtils.getBitmapFromView(params[0]));
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      mView.showProgress();
    }

    @Override
    protected void onPostExecute(String filePath) {
      super.onPostExecute(filePath);
      mInteractor.uploadSignImage(filePath, new CommonCallback<FileDTO>(getViewContext()) {
        @Override
        public void onSuccess(FileDTO data) {
          super.onSuccess(data);
          mSignImage = data;
          mView.showDialogSign(mTypeProcess);
        }

        @Override
        public void onError(String errorCode, ErrorDTO error) {
          super.onError(errorCode, error);
          mView.hideProgress();
        }
      });

    }
  }

  @Override
  public void signOTP() {
    mView.showProgress();
    mInteractor.signOTP(mSignImage.getId(), mOTP.getId(), new CommonCallback<OtpDTO>(getViewContext()) {
      @Override
      public void onSuccess(OtpDTO data) {
        super.onSuccess(data);
        mView.hideProgress();
        Toast.makeText(getViewContext(), mView.getViewContext().getString(R.string.otp_signed), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra(KEY_OTP_OBJECT, new Gson().toJson(data));
        mView.getViewContext().setResult(Activity.RESULT_OK, intent);
        back();
      }

      @Override
      public void onError(String errorCode, ErrorDTO error) {
        super.onError(errorCode, error);
      }
    });
  }

  @Override
  public void goBack() {
    mView.getViewContext().setResult(Activity.RESULT_CANCELED);
    back();
  }
}
