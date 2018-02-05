package app.positiveculture.com.agent.screen.description;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.StepIndicatorHorizontal;
import app.positiveculture.com.data.response.dto.CreatePropertyDTO;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The Description Fragment
 */
public class DescriptionFragment extends ViewFragment<DescriptionContract.Presenter> implements DescriptionContract.View {
  @BindView(R2.id.description_hd)
  CustomHeaderView mHeader;
  @BindView(R2.id.description_step)
  StepIndicatorHorizontal mStep;
  @BindView(R2.id.next_description_cb)
  CustomButton mNext;

  @BindView(R2.id.description_et)
  EditText mDescription;
  @BindView(R2.id.length_tv)
  TextView mLength;

  @OnClick(R2.id.next_description_cb)
  void doNextClick() {
    if (mNext.isEnabled()) {
      getBaseActivity().hideKeyboard();
      mPresenter.goToPhotoSreen(mDescription.getText().toString());
    }
  }

  @OnClick(R2.id.right_text_tv)
  void doSaveClick() {
    String description = mDescription.getText().toString();
    if (description.compareTo("") != 0) {
      mPresenter.goBackToSummary(description);
    } else {
      DialogUtils.showDialog(getViewContext(), getString(R.string.error), getString(R.string.error_messenger),
              new CustomDialog.OnConfirmSelected() {
                @Override
                public void onConfirmSelected() {
                }
              });
    }
  }

  public static DescriptionFragment getInstance() {
    return new DescriptionFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_description;
  }

  @Override
  public void initLayout() {
    super.initLayout();

    this.getViewContext().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    mStep.setCurrentStep(5);
    mHeader.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.goBack();
      }

      @Override
      public void onRightIconClick() {
      }
    });
    mNext.statusDisabledButton();
    mDescription.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!StringUtils.isEmpty(s)) {
          mNext.statusEnableButton();
          int length = 999 - s.length();
          mLength.setText(String.valueOf(length));
        } else {
          mNext.statusDisabledButton();
        }
      }

      @Override
      public void afterTextChanged(Editable s) {
      }
    });

  }

  @Override
  public void onDisplay() {
    super.onDisplay();
    getBaseActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    getBaseActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
  }

  @Override
  public void onStop() {
    getBaseActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    getBaseActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    super.onStop();
  }

  @Override
  public void onPause() {
    getBaseActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    getBaseActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    super.onPause();
  }

  @Override
  public void onDestroy() {
    getBaseActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    getBaseActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    super.onDestroy();
  }

  @Override
  public void bindDataFromSummary(CreatePropertyDTO createPropertyDTO) {
    String description = createPropertyDTO.getmDescription();
    mDescription.setText(description);
    int length = 999 - description.length() - 1;
    mLength.setText(String.valueOf(length));
    mStep.setVisibility(View.GONE);
    mNext.setVisibility(View.GONE);
    mHeader.setRightText(getString(R.string.save));
  }
}
