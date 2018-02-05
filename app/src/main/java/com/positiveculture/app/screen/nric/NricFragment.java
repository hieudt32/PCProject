package com.positiveculture.app.screen.nric;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.UserIdTypeSpinner;
import app.positiveculture.com.data.enumdata.TypeID;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The Nric Fragment
 */
public class NricFragment extends ViewFragment<NricContract.Presenter> implements NricContract.View {

  @BindView(R2.id.nric_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.nric_number_et)
  EditText mNricNumberEt;
  @BindView(R2.id.nric_user_from)
  LinearLayout mNricUserForm;
  @BindView(R2.id.nric_agent_from)
  TableRow mNricAgentForm;
  @BindView(R2.id.nric_id_type_sp)
  UserIdTypeSpinner mIdType;
  @BindView(R2.id.nric_user_id_et)
  EditText mUserId;

  @OnTouch(R2.id.nric_app_ll)
  boolean doTouchLayout() {
    getBaseActivity().hideKeyboard();
    return false;
  }

  @Override
  public void initLayout() {
    super.initLayout();

    mNricNumberEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
          getBaseActivity().hideKeyboard(mNricNumberEt);
        }
      }
    });


    mHeaderView.getRightTextTv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (mNricAgentForm.isShown()) {
          String mNricNumber = mNricNumberEt.getText().toString();
          if (!StringUtils.isEmpty(mNricNumber)) {
            mPresenter.updateNric(mNricNumber);
          } else {
            showDialog(getString(R.string.error), getString(R.string.nric_empty));
          }
        } else if (mNricUserForm.isShown()) {
          String userid = mUserId.getText().toString();
          if (!StringUtils.isEmpty(userid)) {
            mPresenter.updateUserId(mIdType.getSelectedType(), userid);
          } else {
            showDialog(getString(R.string.error), getString(R.string.nric_empty));
          }
        }
      }
    });

    mHeaderView.getLeftTextTv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.nextStep();
      }
    });
  }

  private void showDialog(String title, String message) {
    DialogUtils.showDialog(getViewContext(), title, message, new CustomDialog.OnConfirmSelected() {
      @Override
      public void onConfirmSelected() {

      }
    });
  }

  @Override
  public void showUserFrom(TypeID typeID, String idNumber) {
    mNricAgentForm.setVisibility(View.GONE);
    mNricUserForm.setVisibility(View.VISIBLE);
    if (typeID != null && typeID == TypeID.co_reg_no) {
      mIdType.getSpinner().setSelection(1);
    }
    if (idNumber != null) mUserId.setText(idNumber);
  }

  public static NricFragment getInstance() {
    return new NricFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_nric;
  }

}
