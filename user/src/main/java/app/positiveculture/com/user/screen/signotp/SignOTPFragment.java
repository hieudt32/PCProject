package app.positiveculture.com.user.screen.signotp;

import android.view.View;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.data.enumdata.TypeProcess;
import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import app.positiveculture.com.user.customview.CanvasView;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The SignOTP Fragment
 */
public class SignOTPFragment extends ViewFragment<SignOTPContract.Presenter> implements SignOTPContract.View, CanvasView.OnSignListener {

  @BindView(R2.id.sign_otp_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.sign_otp_bt)
  CustomButton mSignBt;
  @BindView(R2.id.sign_otp_drawing_canvas_view)
  CanvasView mDrawingCanvasView;

  @OnClick(R2.id.sign_otp_bt)
  void signOTPClick() {
    mPresenter.uploadSignImage(mDrawingCanvasView);
  }


  public static SignOTPFragment getInstance() {
    return new SignOTPFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_sign_ot;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mSignBt.setTextButton(getString(R.string.sign_otp));
    mSignBt.statusEnableButton();
    mHeaderView.getRightTextTv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mDrawingCanvasView.clearCanvas();
      }
    });
    mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
    mDrawingCanvasView.setOnSignListener(this);
  }

  @OnClick(R2.id.left_icon_iv)
  public void back() {
    mPresenter.goBack();
  }

  @Override
  public void showDialogSign(TypeProcess mTypeProcess) {
    if (mTypeProcess == null) return;
    if (mTypeProcess == TypeProcess.Selling) {
      showDialog(getString(R.string.otp_will_be_assigned_to));
    } else if (mTypeProcess == TypeProcess.Buying) {
      showDialog(getString(R.string.otp_will_be_exercised_when));
    }
  }

  private void showDialog(String message) {
    DialogUtils.showDialog(getViewContext(), getString(R.string.sign_otp), message,
            new CustomDialog.OnConfirmSelected() {
              @Override
              public void onConfirmSelected() {
                mPresenter.signOTP();
              }
            }, new CustomDialog.OnCancelSelected() {
              @Override
              public void onCancelSelected() {

              }
            });
  }

  @Override
  public void onSigned(boolean isEmpty) {
    if(isEmpty){
      mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
    }else{
      mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
    }
  }
}
