package app.positiveculture.com.agent.screen.seller.verifysigning;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.utils.PropertyUtils;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The VerifySigning Fragment
 */
public class VerifySigningFragment extends ViewFragment<VerifySigningContract.Presenter> implements VerifySigningContract.View {

  @BindView(R2.id.verify_signing_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.verify_signing_check_iv)
  ImageView mCheckIv;
  @BindView(R2.id.verify_btn)
  CustomButton mVerifyBtn;
  @BindView(R2.id.verify_signing_property_name_tv)
  TextView mLineOneTv;
  @BindView(R2.id.verify_signing_property_address_tv)
  TextView mLineTwoTv;
  @BindView(R2.id.verify_signing_des)
  TextView mDes;
  @BindView(R2.id.verify_signing_message)
  TextView mMessage;

  public static VerifySigningFragment getInstance() {
    return new VerifySigningFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_verify_signing;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mVerifyBtn.statusDisabledButton();
    mHeaderView.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.back();
      }

      @Override
      public void onRightIconClick() {

      }
    });
  }

  @OnClick(R2.id.verify_signing_check_iv)
  public void onCheckClick() {
    mCheckIv.setSelected(!mCheckIv.isSelected());
    if (mCheckIv.isSelected()) {
      mCheckIv.setImageResource(R.drawable.ic_radio_selected);
      mVerifyBtn.statusEnableButton();
    } else {
      mCheckIv.setImageResource(R.drawable.ic_radio_default);
      mVerifyBtn.statusDisabledButton();
    }
  }

  @OnClick(R2.id.verify_btn)
  public void onVerifyClick() {
    String mTitle = getResources().getString(R.string.verify_signing);
    String mMessage = getResources().getString(R.string.dialog_verify_signing_desc);
    String mCancelBtn = getResources().getString(R.string.send_out_otp_cancel);
    String mOkBtn = getResources().getString(R.string.send_out_otp_ok);
    final dialog.CustomDialog dialog = new dialog.CustomDialog(getViewContext(), mOkBtn, mCancelBtn, mTitle, mMessage,
            new dialog.CustomDialog.OnConfirmSelected() {
              @Override
              public void onConfirmSelected() {
                mPresenter.verifyOfflineSigning();
              }
            }, new dialog.CustomDialog.OnCancelSelected() {
      @Override
      public void onCancelSelected() {

      }
    });
    dialog.show();
  }

  @Override
  public void bindView(PropertyDTO property) {
    PropertyUtils.displayProperty(property, mLineOneTv, mLineTwoTv);
  }

  @Override
  public void bindViewForVerifyOfflineSigning() {
    String text = getString(R.string.verify);
    mVerifyBtn.setTextButton(text);
    mHeaderView.setTitle(text);
    mVerifyBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DialogUtils.showDialog(getViewContext(), getString(R.string.verify_signing), getString(R.string.dialog_verify_signing_desc),
                new CustomDialog.OnConfirmSelected() {
                  @Override
                  public void onConfirmSelected() {
                    mPresenter.verifyOfflineSigning();
                  }
                }, new CustomDialog.OnCancelSelected() {
                  @Override
                  public void onCancelSelected() {

                  }
                });
      }
    });
  }

  @Override
  public void bindViewForCompleteOffline() {
    String text = getString(R.string.completed);
    mHeaderView.setTitle(text);
    mVerifyBtn.setTextButton(text);
    mMessage.setText(getString(R.string.complete_offline_desc));
    mDes.setText(getString(R.string.check_before_complete_offline));
    mVerifyBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DialogUtils.showDialog(getViewContext(), getString(R.string.complete_offline), getString(R.string.to_complete_signing_offline_the_original),
                new CustomDialog.OnConfirmSelected() {
                  @Override
                  public void onConfirmSelected() {
                    mPresenter.verifyOfflineSigning();
                  }
                }, new CustomDialog.OnCancelSelected() {
                  @Override
                  public void onCancelSelected() {

                  }
                });
      }
    });
  }
}
