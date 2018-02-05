package app.positiveculture.com.user.screen.userchangepassword;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.user.R;
import app.positiveculture.com.user.R2;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomHeaderView;

/**
 * The UserChangePassword Fragment
 */
public class UserChangePasswordFragment extends ViewFragment<UserChangePasswordContract.Presenter> implements UserChangePasswordContract.View {

  @BindView(R2.id.agent_change_password_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.agent_change_password_et)
  EditText mPasswordEt;
  @BindView(R2.id.agent_verify_password_et)
  EditText mVerifyPasswordEt;

  public static UserChangePasswordFragment getInstance() {
    return new UserChangePasswordFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_agent_change_password;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
    mPasswordEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (s.toString().length() > 7 && s.toString().equals(mVerifyPasswordEt.getText().toString())) {
          mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
        } else {
          mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
        }
      }
    });

    mVerifyPasswordEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (s.toString().length() > 7 && s.toString().equals(mPasswordEt.getText().toString())) {
          mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
        } else {
          mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
        }
      }
    });

    mHeaderView.getRightTextTv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.changePassword(mPasswordEt.getText().toString());
      }
    });
  }

  @OnClick(R2.id.left_icon_iv)
  public void back() {
    mPresenter.back();
  }

  @Override
  public void showToastChangePasswordStatus(boolean status) {
    if (status) {
      Toast.makeText(getViewContext(), getString(R.string.successfully), Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(getViewContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
    }
  }
}
