package com.positiveculture.app.screen.confirmprofile;

import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.data.response.dto.CEAProfileDTO;
import app.positiveculture.com.data.response.dto.CeaSmsDTO;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The ConfirmProfile Fragment
 */
public class ConfirmProfileFragment extends ViewFragment<ConfirmProfileContract.Presenter> implements ConfirmProfileContract.View, CustomHeaderView.OnHeaderEventListener {

  @BindView(R2.id.custom_header_view)
  CustomHeaderView mHeader;
  @BindView(R2.id.agent_name_tv)
  TextView mAgentNameTv;
  @BindView(R2.id.agent_cea_number_tv)
  TextView mAgentCEANumberTv;
  @BindView(R2.id.agent_agency_tv)
  TextView mAgentAgencyTv;
  @BindView(R2.id.agent_licence_tv)
  TextView mAgentLicenceTv;

  public static ConfirmProfileFragment getInstance() {
    return new ConfirmProfileFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_confirm_profile;
  }

  @OnClick(R2.id.confirm_profile_not_you_tv)
  void showNotYouDialog() {

    DialogUtils.showDialog(getViewContext(), getString(R.string.not_you), getString(R.string.not_you_des), new CustomDialog.OnConfirmSelected() {
      @Override
      public void onConfirmSelected() {
        mPresenter.goBack();
      }
    });
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mHeader.setOnHeaderEventListener(this);
  }

  @OnClick(R2.id.next_tv)
  void gotoVerifyMobileNumber() {
    mPresenter.sendSMSAgent();
  }

  @Override
  public void onLeftIconClick() {
    mPresenter.goBack();
  }

  @Override
  public void onRightIconClick() {

  }

  @Override
  public void onSendSMSAgentSuccess() {
    mPresenter.gotoVerifyMobileNumber();
  }

  @Override
  public void bindCEAProfile(CEAProfileDTO ceaProfileDTO) {
    mAgentNameTv.setText(ceaProfileDTO.getFullName());
    mAgentCEANumberTv.setText(ceaProfileDTO.getCEANumber());
    mAgentAgencyTv.setText(ceaProfileDTO.getCompany());
    mAgentLicenceTv.setText(ceaProfileDTO.getAgencyLicense());
  }

  @Override
  public void onSendSMSError() {
  }
}
