package app.positiveculture.com.user.screen.signotp;

import android.graphics.Bitmap;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import java.io.File;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.data.response.dto.FileDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.user.customview.CanvasView;

/**
 * The SignOTP Contract
 */
interface SignOTPContract {

  interface Interactor extends IInteractor<Presenter> {
    void uploadSignImage(String filePath, CommonCallback<FileDTO> commonCallback);

    void signOTP(long idSignImage, long idOTP, CommonCallback<OtpDTO> commonCallback);
  }

  interface View extends PresentView<Presenter> {
    void showDialogSign(TypeProcess mTypeProcess);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void uploadSignImage(CanvasView mDraw);

    void signOTP();

    void goBack();
  }
}



