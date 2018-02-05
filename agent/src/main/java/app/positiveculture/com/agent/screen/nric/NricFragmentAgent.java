package app.positiveculture.com.agent.screen.nric;

import android.view.View;
import android.widget.EditText;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.BindView;
import butterknife.OnTouch;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The Nric Fragment
 */
public class NricFragmentAgent extends ViewFragment<NricContractAgent.Presenter> implements NricContractAgent.View {

  @BindView(R2.id.nric_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.nric_number_et)
  EditText mNricNumberEt;

  @Override
  public void initLayout() {
    super.initLayout();
    mHeaderView.getLeftTextTv().setVisibility(View.GONE);
    mHeaderView.getLeftIconIv().setVisibility(View.VISIBLE);
    mHeaderView.getLeftIconIv().setImageResource(R.drawable.ic_back);
    mNricNumberEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
          getBaseActivity().hideKeyboard(mNricNumberEt);
        }
      }
    });
    mHeaderView.getLeftIconIv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.back();
      }
    });
    mHeaderView.getRightTextTv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String nric = mNricNumberEt.getText().toString();
        if (!StringUtils.isEmpty(nric)) {
          mPresenter.saveEditedNricNumber(mNricNumberEt.getText().toString());
        } else {
          DialogUtils.showDialog(getViewContext(), getString(R.string.error), getString(R.string.nric_empty), new CustomDialog.OnConfirmSelected() {
            @Override
            public void onConfirmSelected() {

            }
          });
        }
      }
    });
  }

  public static NricFragmentAgent getInstance() {
    return new NricFragmentAgent();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_nric;
  }

  @Override
  public void setupDataForEditProfile(String nricNumber) {
    if (nricNumber != null)
      mNricNumberEt.setText(nricNumber);
  }
}
