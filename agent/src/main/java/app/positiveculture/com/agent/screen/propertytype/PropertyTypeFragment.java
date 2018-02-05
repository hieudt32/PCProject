package app.positiveculture.com.agent.screen.propertytype;

import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.StepIndicatorHorizontal;
import app.positiveculture.com.data.enumdata.TypeProperty;
import butterknife.BindView;
import butterknife.OnClick;
import customview.CustomButton;
import customview.CustomHeaderView;

/**
 * The PropertyType Fragment
 */
public class PropertyTypeFragment extends ViewFragment<PropertyTypeContract.Presenter> implements PropertyTypeContract.View, CustomHeaderView.OnHeaderEventListener {

  @BindView(R2.id.property_type_hdb)
  TextView mTypeHDB;
  @BindView(R2.id.property_type_condo)
  TextView mTypeCondo;
  @BindView(R2.id.property_type_landed)
  TextView mTypeLander;
  @BindView(R2.id.next_property_type_cb)
  CustomButton mNext;
  @BindView(R2.id.property_type_step)
  StepIndicatorHorizontal mStep;
  @BindView(R2.id.property_type_hd)
  CustomHeaderView mHeader;
  private TypeProperty mSelected;

  @OnClick(R2.id.next_property_type_cb)
  void doNextClick() {
    if (mNext.isEnabled()) {
      mPresenter.goToLocation(mSelected);
    }
  }

  @OnClick(R2.id.property_type_hdb)
  void doTypeHDBClick() {
    setUI(mTypeHDB, mTypeCondo, mTypeLander);
    mSelected = TypeProperty.hdb;
  }

  @OnClick(R2.id.property_type_condo)
  void doTypeCondoClick() {
    setUI(mTypeCondo, mTypeHDB, mTypeLander);
    mSelected = TypeProperty.condo;
  }

  @OnClick(R2.id.property_type_landed)
  void doTypeLanderClick() {
    setUI(mTypeLander, mTypeHDB, mTypeCondo);
    mSelected = TypeProperty.landed;
  }

  public static PropertyTypeFragment getInstance() {
    return new PropertyTypeFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_property_type;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mNext.statusDisabledButton();
    mStep.setCurrentStep(1);
    mHeader.setOnHeaderEventListener(this);
  }

  private void setUI(TextView selected, TextView unSelected1, TextView unSelected2) {
    if (selected.isSelected()) {
      selected.setSelected(false);
      unSelected1.setSelected(false);
      unSelected2.setSelected(false);
      mNext.statusDisabledButton();
    } else {
      selected.setSelected(true);
      unSelected1.setSelected(false);
      unSelected2.setSelected(false);
      mNext.statusEnableButton();
    }
  }

  @Override
  public void onLeftIconClick() {
    mPresenter.goBack();
  }

  @Override
  public void onRightIconClick() {

  }
}
