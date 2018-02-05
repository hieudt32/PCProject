package app.positiveculture.com.agent.screen.clientlist.createnewclient;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.DialogUtils;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.UserIdTypeSpinner;
import butterknife.BindView;
import customview.CustomHeaderView;

/**
 * The CreateNewClient Fragment
 */
public class CreateNewClientFragment extends ViewFragment<CreateNewClientContract.Presenter> implements CreateNewClientContract.View {

  @BindView(R2.id.create_new_client_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.create_new_client_type_spinner)
  UserIdTypeSpinner mIdTypeSpinner;
  @BindView(R2.id.create_new_client_id_number_et)
  EditText mIdNumberEt;

  public static CreateNewClientFragment getInstance() {
    return new CreateNewClientFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_create_new_client;
  }

  @Override
  public void initLayout() {
    super.initLayout();

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
        DialogUtils.showProgressDialog(getViewContext());
        mPresenter.checkClient(mIdTypeSpinner.getSelectedType(), mIdNumberEt.getText().toString());
      }
    });

    mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);

    mIdNumberEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() == 0) {
          mHeaderView.getRightTextTv().setVisibility(View.INVISIBLE);
        } else {
          mHeaderView.getRightTextTv().setVisibility(View.VISIBLE);
        }
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });
  }
}
