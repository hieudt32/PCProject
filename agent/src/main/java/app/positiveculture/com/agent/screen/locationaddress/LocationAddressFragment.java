package app.positiveculture.com.agent.screen.locationaddress;

import android.support.v4.widget.NestedScrollView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.StringUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import app.positiveculture.com.agent.R;
import app.positiveculture.com.agent.R2;
import app.positiveculture.com.agent.customview.StepIndicatorHorizontal;
import app.positiveculture.com.agent.screen.locationaddress.map.MapManager;
import app.positiveculture.com.data.response.dto.Address;
import app.positiveculture.com.data.response.dto.CreatePropertyDTO;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;
import customview.CustomButton;
import customview.CustomHeaderView;

/**
 * The Location_Adress Fragment
 */
public class LocationAddressFragment extends ViewFragment<LocationAddressContract.Presenter> implements LocationAddressContract.View {

  @BindView(R2.id.location_address_body)
  NestedScrollView mNestedSrollView;
  @BindView(R2.id.floor_edt)
  EditText mFloorEdt;
  @BindView(R2.id.unit_edt)
  EditText mUnitEdt;
  @BindView(R2.id.building_edt)
  EditText mBuildingEdt;
  @BindView(R2.id.street_edt)
  EditText mStreetEdt;
  @BindView(R2.id.street2_edt)
  EditText mStreet2Edt;
  @BindView(R2.id.country_edt)
  EditText mCountryEdt;
  @BindView(R2.id.postal_code_edt)
  EditText mPostalCodeEdt;
  @BindView(R2.id.next_location_address_tv)
  CustomButton mNext;
  @BindView(R2.id.location_address_hd)
  CustomHeaderView mHeader;
  @BindView(R2.id.location_address_step)
  StepIndicatorHorizontal mStep;
  @BindView(R2.id.transparent_iv)
  ImageView mTransparentIv;

  private MapManager mapManager;
  private MapFragment mapFragment;
  private Address address = new Address();

  private OnBackLocationListener onBackLocationListener;

  @OnTouch(R2.id.location_address_ll)
  boolean doTouchLayout() {
    getBaseActivity().hideKeyboard();
    return false;
  }

  @OnTouch(R2.id.location_address_body)
  boolean doTouchBody() {
    getBaseActivity().hideKeyboard();
    return false;
  }

  @OnClick(R2.id.left_icon_iv)
  public void onBack() {
    mPresenter.back();
  }

  @OnClick(R2.id.right_text_tv)
  void doSaveClick() {
    getDataFromEditText();
    mPresenter.goBackToSummary(address);
  }

  @OnClick(R2.id.refresh_map_bt)
  public void refreshMap() {
    getDataFromEditText();
    String location = address.getBuilding() + " " + address.getStreet() + " Singapore " + address.getPostalCode();
    mPresenter.refreshMap(location);
  }

  public void setOnBackLocationListener(OnBackLocationListener onBackLocationListener) {
    this.onBackLocationListener = onBackLocationListener;
  }

  @OnClick(R2.id.next_location_address_tv)
  void doNextClick() {
    if (!StringUtils.isEmpty(mStreetEdt.getText())) {
      MapFragment f = (MapFragment) getViewContext().getFragmentManager()
              .findFragmentById(R.id.location_address_map);
      if (f != null) {
        getViewContext().getFragmentManager().beginTransaction().remove(f).commit();
      }
      getDataFromEditText();
      mPresenter.goToPropertyDetail(address);
    }
  }

  public static LocationAddressFragment getInstance() {
    return new LocationAddressFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_location_address;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mNext.statusDisabledButton();
    getBaseActivity().hideKeyboard();
    mStep.setCurrentStep(2);
    initMap();
    mStreetEdt.addTextChangedListener(createTextWatcher());
    mCountryEdt.addTextChangedListener(createTextWatcher());
    mPostalCodeEdt.addTextChangedListener(createTextWatcher());
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
            mNestedSrollView.requestDisallowInterceptTouchEvent(true);
            // Disable touch on transparent view
            return false;

          case MotionEvent.ACTION_UP:
            // Allow ScrollView to intercept touch events.
            mNestedSrollView.requestDisallowInterceptTouchEvent(false);
            return true;

          case MotionEvent.ACTION_MOVE:
            mNestedSrollView.requestDisallowInterceptTouchEvent(true);
            return false;

          default:
            return true;
        }
      }
    });
  }

  private TextWatcher createTextWatcher() {
    return new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (!StringUtils.isEmpty(mStreetEdt.getText().toString().trim()) &&
                !StringUtils.isEmpty(mCountryEdt.getText().toString().trim()) &&
                !StringUtils.isEmpty(mPostalCodeEdt.getText().toString().trim())) {
          mNext.statusEnableButton();
        } else {
          mNext.statusDisabledButton();
        }
      }
    };
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    MapFragment f = (MapFragment) getViewContext().getFragmentManager()
            .findFragmentById(R.id.location_address_map);
    if (f != null) {
      getViewContext().getFragmentManager().beginTransaction().remove(f).commit();
    }
    if (onBackLocationListener != null) {
      onBackLocationListener.onBackLocation();
    }
  }

  private void initMap() {
    mapFragment = (MapFragment) getViewContext().getFragmentManager().findFragmentById(R.id.location_address_map);
    mapFragment.getMapAsync(new OnMapReadyCallback() {
      @Override
      public void onMapReady(GoogleMap googleMap) {
        mapManager = new MapManager(getViewContext(), googleMap);

        if (mPresenter.getLatLng() != null) {
          mapManager.addMarker(mPresenter.getLatLng(), "");
          googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
              mPresenter.showFullMap(address);
            }
          });
        }
      }
    });
  }

  @Override
  public void onDisplay() {
    super.onDisplay();
    if (mapManager == null) {
      mapFragment = (MapFragment) getViewContext().getFragmentManager().findFragmentById(R.id.location_address_map);
      mapFragment.getMapAsync(new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
          mapManager = new MapManager(getViewContext(), googleMap);
          if (mPresenter.getLatLng() != null) mapManager.addMarker(mPresenter.getLatLng(), "");
        }
      });

    } else {
      if (mPresenter.getLatLng() != null) {
        mapManager.addMarker(mPresenter.getLatLng(), "");
      }
    }
  }

  @Override
  public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
  }

  @Override
  public void addLocation(LatLng latLng, String title) {
    mapManager.addMarker(latLng, title);
    mapManager.getGoogleMap().setOnMapClickListener(new GoogleMap.OnMapClickListener() {
      @Override
      public void onMapClick(LatLng latLng) {
        mPresenter.showFullMap(address);
      }
    });
  }

  @Override
  public void bindAddr(Address address) {
    mFloorEdt.setText(address.getFloor());
    mUnitEdt.setText(address.getUnit());
    mBuildingEdt.setText(address.getBuilding());
    mStreetEdt.setText(address.getStreet());
    mCountryEdt.setText(address.getCountry());
    mStreet2Edt.setText(address.getStreet2());
    mPostalCodeEdt.setText(address.getPostalCode());
  }

  @Override
  public void bindDataFromSummary(CreatePropertyDTO createPropertyDTO) {
    mStep.setVisibility(View.GONE);
    mNext.setVisibility(View.GONE);
    mHeader.setRightText(getString(R.string.save));
    address.setFloor(createPropertyDTO.getmFloor());
    address.setUnit(createPropertyDTO.getmUnit());
    address.setStreet2(createPropertyDTO.getmStreetLineTwo());
    address.setStreet(createPropertyDTO.getmStreetLineOne());
    address.setPostalCode(createPropertyDTO.getmPostalCode());
    address.setBuilding(createPropertyDTO.getmBuilding());
    address.setCountry(createPropertyDTO.getmCountry());
    bindAddr(address);
    final LatLng latLng = new LatLng(createPropertyDTO.getmLatitude(), createPropertyDTO.getmLongitude());

    if (mapManager == null) {
      mapFragment = (MapFragment) getViewContext().getFragmentManager().findFragmentById(R.id.location_address_map);
      mapFragment.getMapAsync(new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
          mapManager = new MapManager(getViewContext(), googleMap);
          mapManager.addMarker(latLng, "");
        }
      });
    } else {
      mapManager.addMarker(latLng, "");
    }

  }

  private void getDataFromEditText() {
    String floor = mFloorEdt.getText().toString();
    String unit = mUnitEdt.getText().toString();
    String building = mBuildingEdt.getText().toString();
    String street = mStreetEdt.getText().toString();
    String street2 = mStreet2Edt.getText().toString();
    String country = mCountryEdt.getText().toString();
    String postalCode = mPostalCodeEdt.getText().toString();
    address.setFloor(floor);
    address.setUnit(unit);
    address.setCountry(country);
    address.setStreet(street);
    address.setStreet2(street2);
    address.setBuilding(building);
    address.setPostalCode(postalCode);
  }

  public interface OnBackLocationListener {
    void onBackLocation();
  }

}
