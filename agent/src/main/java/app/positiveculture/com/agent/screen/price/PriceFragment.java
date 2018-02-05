package app.positiveculture.com.agent.screen.price;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.gemvietnam.base.BaseActivity;
import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.StepIndicatorHorizontal;
import app.positiveculture.com.agent.utils.NumberUtils;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;
import customview.CustomButton;
import customview.CustomHeaderView;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The Price Fragment
 */
public class PriceFragment extends ViewFragment<PriceContract.Presenter> implements PriceContract.View {

  @BindView(R2.id.price_hd)
  CustomHeaderView mHeader;
  @BindView(R2.id.price_step)
  StepIndicatorHorizontal mStep;
  @BindView(R2.id.next_price_cb)
  CustomButton mNext;
  @BindView(R2.id.price_edit_et)
  EditText mPrice;
  @BindView(R2.id.valuation_edit_et)
  EditText mValuation;

  @OnTouch(R2.id.price_ll)
  boolean doTouchLayout() {
    getBaseActivity().hideKeyboard();
    return false;
  }

  @OnClick(R2.id.next_price_cb)
  void doNextClick() {
    if (mNext.isEnabled()) {
      double price = getValueFromEditText(mPrice);
      double valuation = getValueFromEditText(mValuation);
      if (price == 0) {
        showDialogError();
      } else {
        mPresenter.goToDescriptionScreen(price, valuation);
      }
    }
  }

  @OnClick(R2.id.right_text_tv)
  void doSaveClick() {
    double price = getValueFromEditText(mPrice);
    double valuation = getValueFromEditText(mValuation);
    if (price == 0) {
      showDialogError();
    } else {
      mPresenter.goBackToSummary(price, valuation);
    }

  }

  public static PriceFragment getInstance() {
    return new PriceFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_price;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    outKeyWork(mValuation);
    mHeader.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.goBack();
      }

      @Override
      public void onRightIconClick() {

      }
    });
    mStep.setCurrentStep(4);
    mNext.statusDisabledButton();
    addTextChange(mValuation);
    addTextChange(mPrice);
  }

  private void addTextChange(final EditText editText) {
    editText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!StringUtils.isEmpty(mPrice.getText())) {
          mNext.statusEnableButton();
        } else {
          mNext.statusDisabledButton();
        }
      }

      @Override
      public void afterTextChanged(Editable s) {
        editText.removeTextChangedListener(this);
        NumberUtils.formatDoubleEditTextRunTime(editText);
        editText.addTextChangedListener(this);
      }
    });
  }

  @Override
  public void setPriceFromSummary(double price, double valuation) {
    mPrice.setText(String.valueOf(NumberUtils.formatNumber(price)));
    mValuation.setText(String.valueOf(NumberUtils.formatNumber(valuation)));
    mHeader.setRightText(getString(R.string.save));
    mNext.setVisibility(View.INVISIBLE);
    mStep.setVisibility(View.INVISIBLE);
  }

  private double getValueFromEditText(EditText editText) {
    String value = editText.getText().toString();
    if (value.compareTo("") != 0) {
      if (value.contains(",")) {
        return Double.parseDouble(value.replace(",", ""));
      } else {
        return Double.parseDouble(value);
      }
    } else return 0;
  }

  private void showDialogError() {
    DialogUtils.showDialog(getViewContext(), getString(R.string.error), getString(R.string.price)
                    + "," + getString(R.string.error_messenger3),
            new CustomDialog.OnConfirmSelected() {
              @Override
              public void onConfirmSelected() {

              }
            });
  }

  private void outKeyWork(final EditText editText) {
    editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
          InputMethodManager imm =
                  (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
          imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
          ((BaseActivity) getContext()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }
        return true;
      }
    });
  }
}
