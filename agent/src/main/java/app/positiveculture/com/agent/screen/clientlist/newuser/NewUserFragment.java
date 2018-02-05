package app.positiveculture.com.agent.screen.clientlist.newuser;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;

import java.util.List;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.UserIdTypeSpinner;
import app.positiveculture.com.agent.utils.CountryCode;
import app.positiveculture.com.data.enumdata.TypeID;
import app.positiveculture.com.data.response.dto.CreateMemberDTO;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;
import customview.CustomHeaderView;
import dialog.CustomDialog;

/**
 * The NewUser Fragment
 */
public class NewUserFragment extends ViewFragment<NewUserContract.Presenter> implements NewUserContract.View, CustomDialog.OnConfirmSelected {

  @BindView(R2.id.new_user_header_view)
  CustomHeaderView mHeaderView;
  @BindView(R2.id.new_user_id_type_spinner)
  UserIdTypeSpinner mIdTypeSpinner;
  @BindView(R2.id.new_user_mobile_spinner)
  Spinner mMobileSpinner;
  @BindView(R2.id.new_user_nric_fin_et)
  EditText mIdNumberEt;
  @BindView(R2.id.new_user_name_et)
  EditText mNameEt;
  @BindView(R2.id.new_user_email_et)
  EditText mEmailEt;
  @BindView(R2.id.new_user_mobile_et)
  EditText mMobileEt;
  @BindView(R2.id.new_user_send_invitation_btn)
  CustomButton mSendInvitationBtn;

  private ArrayAdapter<String> mMobileAdapter;
  private CustomDialog mDialog;

  public static NewUserFragment getInstance() {
    return new NewUserFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_new_user;
  }

  @Override
  public void initLayout() {
    super.initLayout();

    setupMobileSpinner();

    addListeners();
  }

  private void setupMobileSpinner() {
    List<String> listCode = CountryCode.getCode();
    mMobileAdapter = new ArrayAdapter<String>(
            getViewContext(), android.R.layout.simple_spinner_dropdown_item, listCode);
    mMobileSpinner.setAdapter(mMobileAdapter);
  }

  private void addListeners() {
    mHeaderView.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.back();
      }

      @Override
      public void onRightIconClick() {

      }
    });

    mIdNumberEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (formIsValid()) {
          mSendInvitationBtn.statusEnableButton();
        } else {
          mSendInvitationBtn.statusDisabledButton();
        }
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });
    mIdNumberEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_NEXT) {
          mNameEt.requestFocus();
        }
        return true;
      }
    });

    mNameEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (formIsValid()) {
          mSendInvitationBtn.statusEnableButton();
        } else {
          mSendInvitationBtn.statusDisabledButton();
        }
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });
    mNameEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_NEXT) {
          mEmailEt.requestFocus();
        }
        return true;
      }
    });

    mEmailEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (formIsValid()) {
          mSendInvitationBtn.statusEnableButton();
        } else {
          mSendInvitationBtn.statusDisabledButton();
        }
      }

      @Override
      public void afterTextChanged(Editable s) {
      }
    });
    mEmailEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_NEXT) {
          mMobileEt.requestFocus();
        }
        return true;
      }
    });

    mMobileEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (formIsValid()) {
          mSendInvitationBtn.statusEnableButton();
        } else {
          mSendInvitationBtn.statusDisabledButton();
        }
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });
    mMobileEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
          getBaseActivity().hideKeyboard(mMobileEt);
        }
        return true;
      }
    });
  }

  private boolean formIsValid() {
    if (StringUtils.isEmpty(mIdNumberEt.getText().toString().trim())) {
      return false;
    }
    if (StringUtils.isEmpty(mNameEt.getText().toString().trim())) {
      return false;
    }
    if (!StringUtils.isEmailValid(mEmailEt.getText().toString().trim())) {
      return false;
    }
    if (StringUtils.isEmpty(mMobileEt.getText().toString().trim())) {
      return false;
    }
    return true;
  }

  @OnClick(R2.id.new_user_send_invitation_btn)
  public void btnSendInvitationOnClick() {
    CreateMemberDTO createMemberDTO = new CreateMemberDTO();
    if (mIdTypeSpinner.getSelectedType().equals("nric")) {
      createMemberDTO.setIdType(TypeID.nric);
    } else {
      createMemberDTO.setIdType(TypeID.co_reg_no);
    }
    createMemberDTO.setIdNumber(mIdNumberEt.getText().toString());
    createMemberDTO.setFullName(mNameEt.getText().toString());
    createMemberDTO.setEmail(mEmailEt.getText().toString());
    createMemberDTO.setPhoneCountryCode(mMobileSpinner.getSelectedItem().toString());
    createMemberDTO.setPhoneNumber(mMobileEt.getText().toString());

    mPresenter.createNewClient(createMemberDTO);
  }

  @Override
  public void onConfirmSelected() {
    mDialog.dismiss();
    mPresenter.goToClientList();
  }

  @Override
  public void setupData(String idType, String idNumber) {
    if (idType.equals(TypeID.nric.toString())) {
      mIdTypeSpinner.getSpinner().setSelection(0);
    } else {
      mIdTypeSpinner.getSpinner().setSelection(1);
    }
    mIdNumberEt.setText(idNumber);
  }

  @Override
  public void showCreateSuccessDialog() {
    mDialog = new CustomDialog(
            getViewContext(),
            getString(R.string.bt_ok), null,
            getString(R.string.invitation_sent_title), getString(R.string.invitation_sent_desc),
            this
    );
    mDialog.show();
  }

}
