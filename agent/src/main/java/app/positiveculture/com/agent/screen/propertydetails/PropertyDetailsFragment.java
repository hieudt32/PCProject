package app.positiveculture.com.agent.screen.propertydetails;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.StepIndicatorHorizontal;
import app.positiveculture.com.agent.utils.NumberUtils;
import app.positiveculture.com.data.response.dto.CreatePropertyDTO;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;
import customview.CustomButton;
import customview.CustomHeaderView;
import customview.CustomPropertyDetails;
import app.positiveculture.com.agent.customview.CustomSmallInput;
import dialog.CustomDialog;
import utils.DialogUtils;

/**
 * The Property_details Fragment
 */
public class PropertyDetailsFragment extends ViewFragment<PropertyDetailsContract.Presenter> implements
        PropertyDetailsContract.View {
  @BindView(R2.id.rooms_cpd)
  CustomPropertyDetails mRoom;
  @BindView(R2.id.bathrooms_cpd)
  CustomPropertyDetails mBathroom;
  @BindView(R2.id.plus_room_cpd)
  CustomPropertyDetails mPlusRoom;
  @BindView(R2.id.property_detail_hd)
  CustomHeaderView mHeader;
  @BindView(R2.id.property_detail_step)
  StepIndicatorHorizontal mStep;
  @BindView(R2.id.next_property_detail_cb)
  CustomButton mNext;
  @BindView(R2.id.custom_small_input)
  CustomSmallInput mFloorSizeLayout;

  @OnTouch(R2.id.property_detail_ll)
  boolean doTouchLayout() {
    getBaseActivity().hideKeyboard();
    return false;
  }

  @OnTouch(R2.id.property_detail_content)
  boolean doTouchBody() {
    getBaseActivity().hideKeyboard();
    return false;
  }

  @OnClick(R2.id.next_property_detail_cb)
  void doNextClick() {
    int room = mRoom.getValue();
    int bathRoom = mBathroom.getValue();
    int plusRoom = mPlusRoom.getValue();
    float floorSizeBuildUp = mFloorSizeLayout.getValueFloorSize();
    float floorSizeLanded = 0;
    String floorSizeUnit = getString(R.string.sqft);
    if (mFloorSizeLayout.getmSqm().isChecked()) {
      floorSizeUnit = getString(R.string.sqm);
    }
    if (mFloorSizeLayout.getmFloorSizeLandedLayout().isShown()) {
      floorSizeLanded = mFloorSizeLayout.getValueFloorSizeLanded();
      if (floorSizeLanded == 0 || floorSizeBuildUp == 0) {
        showDialog();
      } else
        mPresenter.goToPriceScreen(room, bathRoom, plusRoom, floorSizeBuildUp, floorSizeLanded, floorSizeUnit);
    } else {
      if (floorSizeBuildUp == 0) {
        showDialog();
      } else
        mPresenter.goToPriceScreen(room, bathRoom, plusRoom, floorSizeBuildUp, floorSizeLanded, floorSizeUnit);
    }
  }

  @OnClick(R2.id.right_text_tv)
  void doSaveClick() {
    int room = mRoom.getValue();
    int bathRoom = mBathroom.getValue();
    int plusRoom = mPlusRoom.getValue();
    float floorSizeBuildUp = mFloorSizeLayout.getValueFloorSize();
    float floorSizeLanded = 0;
    String floorSizeUnit = getString(R.string.sqft);
    if (mFloorSizeLayout.getmSqm().isChecked()) {
      floorSizeUnit = getString(R.string.sqm);
    }
    if (mFloorSizeLayout.getmFloorSizeLandedLayout().isShown()) {
      floorSizeLanded = mFloorSizeLayout.getValueFloorSizeLanded();
      if (floorSizeLanded == 0 || floorSizeBuildUp == 0) {
        showDialog();
      } else
        mPresenter.goBackToSummary(room, bathRoom, plusRoom, floorSizeBuildUp, floorSizeLanded, floorSizeUnit);
    } else {
      if (floorSizeBuildUp == 0) {
        showDialog();
      } else
        mPresenter.goBackToSummary(room, bathRoom, plusRoom, floorSizeBuildUp, floorSizeLanded, floorSizeUnit);
    }
  }

  public static PropertyDetailsFragment getInstance() {
    return new PropertyDetailsFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_property_details;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mRoom.setRoom(true);
    mStep.setCurrentStep(3);
    mNext.statusDisabledButton();
    mHeader.setOnHeaderEventListener(new CustomHeaderView.OnHeaderEventListener() {
      @Override
      public void onLeftIconClick() {
        mPresenter.goBack();
      }

      @Override
      public void onRightIconClick() {

      }
    });

    mFloorSizeLayout.getmFloorSize().addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (mFloorSizeLayout.getmFloorSizeLandedLayout().isShown()) {
          if (mFloorSizeLayout.getValueFloorSize() > 0 && mFloorSizeLayout.getValueFloorSizeLanded() > 0) {
            mNext.statusEnableButton();
          } else {
            mNext.statusDisabledButton();
          }
        } else {
          if (mFloorSizeLayout.getValueFloorSize() > 0) {
            mNext.statusEnableButton();
          } else {
            mNext.statusDisabledButton();
          }
        }
      }
    });

    mFloorSizeLayout.getmFloorSizeLanded().addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (mFloorSizeLayout.getmFloorSizeLandedLayout().isShown()) {
          if (mFloorSizeLayout.getValueFloorSize() > 0 && mFloorSizeLayout.getValueFloorSizeLanded() > 0) {
            mNext.statusEnableButton();
          } else {
            mNext.statusDisabledButton();
          }
        } else {
          if (mFloorSizeLayout.getValueFloorSize() > 0) {
            mNext.statusEnableButton();
          } else {
            mNext.statusDisabledButton();
          }
        }
      }
    });
  }

  @Override
  public void hideFloorSizeLanded() {
    mFloorSizeLayout.getmFloorSizeLandedLayout().setVisibility(View.INVISIBLE);
  }

  @Override
  public void setDetailFromSummary(CreatePropertyDTO createPropertyDTO) {
    mHeader.setRightText(getString(R.string.save));
    mStep.setVisibility(View.INVISIBLE);
    mNext.setVisibility(View.INVISIBLE);
    mRoom.setValue(createPropertyDTO.getmRooms());
    mPlusRoom.setValue(createPropertyDTO.getmPlusRooms());
    mBathroom.setValue(createPropertyDTO.getmBathrooms());
    mFloorSizeLayout.getmFloorSize().setText(NumberUtils.formatNumber(createPropertyDTO.getmFloorSizeBuilt()));
    if (mFloorSizeLayout.getmFloorSizeLandedLayout().isShown()) {
      mFloorSizeLayout.getmFloorSizeLanded().setText(NumberUtils.formatNumber(createPropertyDTO.getmFloorSizeLanded()));
    }
    if (createPropertyDTO.getmFloorSizeUnit().compareTo(getString(R.string.sqft)) == 0) {
      mFloorSizeLayout.getmSqft().setChecked(true);
      mFloorSizeLayout.getmSqm().setChecked(false);
    } else {
      mFloorSizeLayout.getmSqm().setChecked(true);
      mFloorSizeLayout.getmSqft().setChecked(false);
    }
  }

  private void showDialog() {
    DialogUtils.showDialog(getViewContext(), getString(R.string.error), getString(R.string.error_messenger),
            new CustomDialog.OnConfirmSelected() {
              @Override
              public void onConfirmSelected() {
              }
            });
  }
}
