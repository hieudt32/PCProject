package app.positiveculture.com.agent.screen.properties.detail;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.TwoTextViewCustom;
import app.positiveculture.com.agent.screen.locationaddress.map.MapManager;
import app.positiveculture.com.agent.utils.NumberUtils;
import app.positiveculture.com.agent.utils.PropertyUtils;
import app.positiveculture.com.agent.utils.ViewUtils;
import app.positiveculture.com.data.enumdata.OTPStatus;
import app.positiveculture.com.data.response.dto.OtpDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;
import app.positiveculture.com.data.response.dto.PropertyDisplayDTO;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * The Detail Fragment
 */
public class DetailFragment extends ViewFragment<DetailContract.Presenter> implements DetailContract.View {

  private MapManager mapManager;
  private MapFragment mapFragment;

  @BindView(R2.id.detail_scroll_view)
  ScrollView mScrollView;
  @BindView(R2.id.thumb_detail_iv)
  SimpleDraweeView mThumbProperty;
  @BindView(R2.id.name_detail_tv)
  TextView mName;
  @BindView(R2.id.date_property_tv)
  TextView mTime;
  @BindView(R2.id.price_property_tv)
  TextView mPriceBig;
  @BindView(R2.id.number_bedroom_tv)
  TextView mBedroom;
  @BindView(R2.id.number_bathroom_tv)
  TextView mBathroom;
  @BindView(R2.id.built_up_custom_tt)
  TwoTextViewCustom mFloorSizeBuiltUp;
  @BindView(R2.id.floor_size_custom_tt)
  TwoTextViewCustom mFloorSize;
  @BindView(R2.id.description_detail_rl)
  RelativeLayout mDescriptionLayout;
  @BindView(R2.id.description_detail_title_tv)
  TextView mDescriptionTitleTv;
  @BindView(R2.id.description_property_tv)
  TextView mDescription;
  @BindView(R2.id.red_more_detail_tv)
  TextView mRedMoreTv;
  @BindView(R2.id.price_custom_tt)
  TwoTextViewCustom mPrice;
  @BindView(R2.id.valuation_custom_tt)
  TwoTextViewCustom mValuation;
  @BindView(R2.id.location_custom_tt)
  TwoTextViewCustom mLocation;
  @BindView(R2.id.document_detail_rl)
  RelativeLayout mDocumentLayout;
  @BindView(R2.id.item_owner_buyer)
  LinearLayout mAgentLayout;
  @BindView(R2.id.user_avatar_iv)
  SimpleDraweeView mAgentAvatar;
  @BindView(R2.id.user_name_tv)
  TextView mAgentNameTv;
  @BindView(R2.id.user_type_tv)
  TextView mAgentCeaTv;
  @BindView(R2.id.document_custom_tt)
  TwoTextViewCustom mDocument;
  @BindView(R2.id.create_otp_bt)
  Button mCreateOTP;
  @BindView(R2.id.detail_transparent_iv)
  ImageView mTransparentIv;
  boolean expand = false;
  int defaultHeight;


  @OnClick(R2.id.back_detail_iv)
  void doBack() {
    mPresenter.back();
  }

  @OnClick(R2.id.thumb_detail_iv)
  void goToPhotoFullViewScreen() {
    mPresenter.goToPhotoFullView();
  }

  @OnClick(R2.id.red_more_detail_tv)
  void doReadMoreClick() {
    if (!expand) {
      defaultHeight = mDescriptionLayout.getHeight();
      int height = mDescription.getHeight() / 4;
      height = defaultHeight + height * (mDescription.getLineCount() - 4);
      LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mDescriptionLayout.getLayoutParams();
      params.height = height;
      mDescriptionLayout.setLayoutParams(params);
      mDescription.setMaxLines(mDescription.getLineCount());
      expand = true;
    } else {
      LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mDescriptionLayout.getLayoutParams();
      params.height = defaultHeight;
      mDescriptionLayout.setLayoutParams(params);
      mDescription.setMaxLines(4);
      expand = false;
    }
  }

  @OnClick(R2.id.item_owner_buyer)
  void doAgentClick() {
    mPresenter.goToAgentProfileScreen();
  }


  public static DetailFragment getInstance() {
    return new DetailFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_detail_agent;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mDocument.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.goToDocumentsScreen();
      }
    });
    initMap();
    enableSmoothSlideAndZoomGoogleMap();
  }

  private void enableSmoothSlideAndZoomGoogleMap() {
    mTransparentIv.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
          case MotionEvent.ACTION_DOWN:
            // Disallow ScrollView to intercept touch events.
            mScrollView.requestDisallowInterceptTouchEvent(true);
            // Disable touch on transparent view
            return false;

          case MotionEvent.ACTION_UP:
            // Allow ScrollView to intercept touch events.
            mScrollView.requestDisallowInterceptTouchEvent(false);
            return true;

          case MotionEvent.ACTION_MOVE:
            mScrollView.requestDisallowInterceptTouchEvent(true);
            return false;

          default:
            return true;
        }
      }
    });
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    MapFragment f = (MapFragment) getViewContext().getFragmentManager()
            .findFragmentById(R.id.detail_map);
    if (f != null)
      getViewContext().getFragmentManager().beginTransaction().remove(f).commit();
    mPresenter.setComeBack();
  }

  @Override
  public void onPause() {
    super.onPause();
//    MapFragment f = (MapFragment) getViewContext().getFragmentManager()
//            .findFragmentById(R.id.detail_map);
//    if (f != null)
//      getViewContext().getFragmentManager().beginTransaction().remove(f).commit();
  }

  private void initMap() {
    mapFragment = (MapFragment) getViewContext().getFragmentManager().findFragmentById(R.id.detail_map);
    mapFragment.getMapAsync(new OnMapReadyCallback() {
      @Override
      public void onMapReady(GoogleMap googleMap) {
        mapManager = new MapManager(getViewContext(), googleMap);
      }
    });
  }

  @Override
  public void bindData(PropertyDTO propertyDTO) {
    ViewUtils.loadAvatarWithFresco(mThumbProperty, propertyDTO.getmFeatrureImage().getImgMedium());
    if (!StringUtils.isEmpty(propertyDTO.getmDisplayOne())) {
      mName.setText(propertyDTO.getmDisplayOne());
    } else if (!StringUtils.isEmpty(propertyDTO.getmDisplayTwo())) {
      mName.setText(propertyDTO.getmDisplayTwo());
    } else if (!StringUtils.isEmpty(propertyDTO.getmDisplayThree())) {
      mName.setText(propertyDTO.getmDisplayThree());
    } else if (!StringUtils.isEmpty(propertyDTO.getmDisplayFour())) {
      mName.setText(propertyDTO.getmDisplayFour());
    } else {
      mName.setText(getString(R.string.no_data));
    }
    String time = PropertyUtils.countTimeCreate(propertyDTO.getmCreateAt(), getViewContext());
    if (time != null) {
      mTime.setText(time);
    } else {
      mTime.setText(" ");
    }
    if (propertyDTO.getmFloorSizeBuilt() > 0) {
      mFloorSizeBuiltUp.setContent(NumberUtils.formatNumber(propertyDTO.getmFloorSizeBuilt()) + " " + propertyDTO.getmFloorSizeUnit());
    } else mFloorSizeBuiltUp.setContent("0");
    if (propertyDTO.getmFloorSizeLanded() != 0) {
      mFloorSize.setContent(NumberUtils.formatNumber(propertyDTO.getmFloorSizeLanded()) + " " + propertyDTO.getmFloorSizeUnit());
    } else {
      mFloorSize.setContent("0");
    }
    mPriceBig.setText(NumberUtils.formatNumberTwo(propertyDTO.getmPrice()));
    String bedroom = "";
    if (propertyDTO.getmRooms() != 0) {
      bedroom += propertyDTO.getmRooms();
    } else {
      bedroom += getString(R.string.studio);
    }
    if (propertyDTO.getmPlusRooms() != 0) {
      bedroom += " + " + propertyDTO.getmPlusRooms() + " " + getString(R.string.rooms);
      mBedroom.setText(bedroom);
    } else {
      bedroom += " " + getString(R.string.rooms);
      mBedroom.setText(bedroom);
    }
    String bathroom = propertyDTO.getmBathrooms() + " " + getString(R.string.bathrooms);
    mBathroom.setText(bathroom);
    mDescription.setText(propertyDTO.getmDescription());
    mPrice.setContent(NumberUtils.formatNumberTwo(propertyDTO.getmPrice()));
    mValuation.setContent(NumberUtils.formatNumberTwo(propertyDTO.getmValuation()));
    mLocation.setContent(propertyDTO.getmStreetLineOne());
    final LatLng mLatLng = new LatLng(propertyDTO.getmLatitude(), propertyDTO.getmLongitude());
    if (mapManager != null) {
      mapManager.addMarker(mLatLng, getString(R.string.location));
    } else {
      mapFragment = (MapFragment) getViewContext().getFragmentManager().findFragmentById(R.id.detail_map);
      mapFragment.getMapAsync(new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
          mapManager = new MapManager(getViewContext(), googleMap);
          mapManager.addMarker(mLatLng, "location");
        }
      });
    }

    mDescription.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {
        mDescription.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        if (mDescription.getLineCount() > 4) {
          mRedMoreTv.setVisibility(View.VISIBLE);
        } else {
          mRedMoreTv.setVisibility(View.GONE);
        }
      }
    });

    mCreateOTP.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.gotoCreateOtp();
      }
    });
  }

  @Override
  public void hideButtonCreateOTP() {
    mCreateOTP.setVisibility(View.GONE);
    mDocumentLayout.setVisibility(View.GONE);
  }

  @Override
  public void bindDataUserView(PropertyDTO property) {
    mDocumentLayout.setVisibility(View.GONE);
    mCreateOTP.setVisibility(View.GONE);
    mAgentLayout.setVisibility(View.VISIBLE);
    mAgentLayout.setBackgroundColor(getResources().getColor(R.color.transparent));

    if (property.getmAgent() != null) {
      if (property.getmAgent().getAvatar() != null && property.getmAgent().getAvatar().getImgSmall() != null) {
        ViewUtils.loadAvatarWithFresco(mAgentAvatar, property.getmAgent().getAvatar().getImgSmall());
      }

      if (property.getmAgent().getUsers() != null && property.getmAgent().getUsers().getFullName() != null) {
        mAgentNameTv.setText(property.getmAgent().getUsers().getFullName());
      }

      if (property.getmAgent().getCEA() != null && property.getmAgent().getCEA().getCEANumber() != null) {
        mAgentCeaTv.setText(property.getmAgent().getCEA().getCEANumber());
      }
    }
  }

  @Override
  public void showReviewOTP(OtpDTO mOTP) {
    OTPStatus otpStatus = mOTP.getStatus();
    if (otpStatus == OTPStatus.owners_reject) {
      mCreateOTP.setText(getString(R.string.edit_otp));
      mCreateOTP.setVisibility(View.VISIBLE);
      mCreateOTP.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          mPresenter.gotoCreateOtp();
        }
      });
    } else {
      mCreateOTP.setVisibility(View.GONE);
    }
  }

  @Override
  public void displayNameProperty(PropertyDisplayDTO data) {
    if (!StringUtils.isEmpty(data.getDisplayLineOne())) {
      mName.setText(data.getDisplayLineOne());
    } else if (!StringUtils.isEmpty(data.getDisplayLineTwo())) {
      mName.setText(data.getDisplayLineTwo());
    } else if (!StringUtils.isEmpty(data.getDisplayLineThree())) {
      mName.setText(data.getDisplayLineThree());
    } else if (!StringUtils.isEmpty(data.getDisplayLineFour())) {
      mName.setText(data.getDisplayLineFour());
    } else {
      mName.setText(getString(R.string.no_data));
    }
  }

}
