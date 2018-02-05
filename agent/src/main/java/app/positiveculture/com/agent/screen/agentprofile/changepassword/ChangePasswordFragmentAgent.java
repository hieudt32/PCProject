package app.positiveculture.com.agent.screen.agentprofile.changepassword;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import butterknife.BindView;
import customview.CustomHeaderView;

/**
 * The ChangePassword Fragment
 */
public class ChangePasswordFragmentAgent extends ViewFragment<ChangePasswordContractAgent.Presenter> implements ChangePasswordContractAgent.View {

  @BindView(R2.id.agent_change_password_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.agent_change_password_et)
  EditText mPasswordEt;
  @BindView(R2.id.agent_verify_password_et)
  EditText mVerifyEt;

  public static ChangePasswordFragmentAgent getInstance() {
    return new ChangePasswordFragmentAgent();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_agent_change_password;
  }

  @Override
  public void initLayout() {
    super.initLayout();

    mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
    mHeaderView.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.back();
      }

      @Override
      public void onRightIconClick() {

      }
    });
    mHeaderView.getRightTextTv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.saveNewPassword(mPasswordEt.getText().toString());
      }
    });

    mPasswordEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (s.toString().length() >7 && s.toString().equals(mVerifyEt.getText().toString())) {
          mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
        }else{
          mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
        }
      }
    });

    mVerifyEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (s.toString().length() >7 && s.toString().equals(mPasswordEt.getText().toString())) {
          mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
        }else{
          mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
        }
      }
    });
  }
}
