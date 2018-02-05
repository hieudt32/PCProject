package app.positiveculture.com.agent.screen.locationaddress;

import android.widget.Toast;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.StringUtils;
import com.google.android.gms.maps.model.LatLng;

import app.positiveculture.com.agent.screen.adjustpinmap.AdjustPinMapFragment;
import app.positiveculture.com.agent.screen.adjustpinmap.AdjustPinMapPresenter;
import app.positiveculture.com.agent.screen.propertydetails.PropertyDetailsPresenter;
import app.positiveculture.com.agent.utils.GoogleMapApiKey;
import app.positiveculture.com.data.GoogleServiceBuilder;
import app.positiveculture.com.data.response.dto.Address;
import app.positiveculture.com.data.response.dto.AddressComponent;
import app.positiveculture.com.data.response.dto.CreatePropertyDTO;
import app.positiveculture.com.data.response.dto.GoogleMapDetailDTO;
import app.positiveculture.com.data.response.dto.GoogleMapSearchDTO;
import app.positiveculture.com.data.response.dto.LocationSearchResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The Location_Adress Presenter
 */
public class LocationAddressPresenter extends Presenter<LocationAddressContract.View, LocationAddressContract.Interactor>
        implements LocationAddressContract.Presenter, AdjustPinMapPresenter.OnDragMarkerEndListener, AdjustPinMapFragment.OnBackLocationAddrListener {

  private String mPlaceId;
  private LatLng latLng;
  private CreatePropertyDTO createPropertyDTO;
  private OnGetLocationPropertyListener mOnGetLocationPropertyListener;
  private GoogleMapDetailDTO mGoogleMapDetailDTO;

  public LocationAddressPresenter setmOnGetLocationPropertyListener(OnGetLocationPropertyListener mOnGetLocationPropertyListener) {
    this.mOnGetLocationPropertyListener = mOnGetLocationPropertyListener;
    return this;
  }

  public LocationAddressPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public LatLng getLatLng() {
    return latLng;
  }

  @Override
  public LocationAddressContract.View onCreateView() {
    return LocationAddressFragment.getInstance();
  }

  @Override
  public void start() {
    if (mOnGetLocationPropertyListener != null) {
      mView.bindDataFromSummary(createPropertyDTO);
      latLng = new LatLng(createPropertyDTO.getmLatitude(), createPropertyDTO.getmLongitude());
    }

    if (!StringUtils.isEmpty(mPlaceId)) {
      mView.showProgress();
      searchLocationDetail(mPlaceId);
    }
  }

  private void searchLocationDetail(String placeId) {
    GoogleServiceBuilder.getService().getLocationDetail(placeId, GoogleMapApiKey.INFO_LOCATION_KEY, "en_US").enqueue(new Callback<GoogleMapDetailDTO>() {
      @Override
      public void onResponse(Call<GoogleMapDetailDTO> call, Response<GoogleMapDetailDTO> response) {
        mGoogleMapDetailDTO = response.body();
        if (mGoogleMapDetailDTO != null) {
          Address address = new Address();
          for (AddressComponent addressComponent : mGoogleMapDetailDTO.getResult().getAddressComponentList()) {
            switch (addressComponent.getType()) {
              case "premise":
                address.setBuilding(addressComponent.getLongName());
                break;
              case "street_number":
                address.setStreet(addressComponent.getLongName());
                break;
              case "route":
                if (!StringUtils.isEmpty(address.getStreet())) {
                  address.setStreet(address.getStreet() + ", " + addressComponent.getLongName());
                } else {
                  address.setStreet(addressComponent.getLongName());
                }
                break;
              case "country":
                address.setCountry(addressComponent.getLongName());
                break;
              case "postal_code":
                address.setPostalCode(addressComponent.getLongName());
                break;
            }
          }

          latLng = new LatLng(mGoogleMapDetailDTO.getResult().getGeometry().getLocation().getLat(),
                  mGoogleMapDetailDTO.getResult().getGeometry().getLocation().getLng());
          mView.bindAddr(address);
          mView.addLocation(latLng, mGoogleMapDetailDTO.getResult().getFormattedAddress());
        }
        mView.hideProgress();
      }

      @Override
      public void onFailure(Call<GoogleMapDetailDTO> call, Throwable t) {
        mView.hideProgress();
      }
    });
  }

  @Override
  public LocationAddressContract.Interactor onCreateInteractor() {
    return new LocationAddressInteractor(this);
  }

  @Override
  public void refreshMap(String location) {
    if (StringUtils.isEmpty(location)) {
      return;
    }
    mView.showProgress();
    GoogleServiceBuilder.getService().getLocationSearch(location, GoogleMapApiKey.LOCATION_KEY).enqueue(new Callback<GoogleMapSearchDTO>() {
      @Override
      public void onResponse(Call<GoogleMapSearchDTO> call, Response<GoogleMapSearchDTO> response) {
        GoogleMapSearchDTO googleMapSearchDTO = response.body();
        if (googleMapSearchDTO != null && googleMapSearchDTO.getListSearchResult().size() > 0) {
          LocationSearchResult locationSearchResult = googleMapSearchDTO.getListSearchResult().get(0);
          double lat = locationSearchResult.getGeometry().getLocation().getLat();
          double lng = locationSearchResult.getGeometry().getLocation().getLng();
          String title = locationSearchResult.getFormattedAddress();
          latLng = new LatLng(lat, lng);
          mView.addLocation(latLng, title);
          mView.hideProgress();
        } else {
          mView.hideProgress();
          Toast.makeText(getViewContext(), "Cannot find place", Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<GoogleMapSearchDTO> call, Throwable t) {
        mView.hideProgress();
      }
    });
  }

  @Override
  public void showFullMap(Address address) {
    if (latLng == null) {
      return;
    }
    AdjustPinMapPresenter adjustPinMapPresenter = new AdjustPinMapPresenter(mContainerView);
    adjustPinMapPresenter.setOnDragMarkerEndListener(this);
    ((AdjustPinMapFragment) adjustPinMapPresenter.getFragment()).setOnBackLocationAddrListener(this);
    adjustPinMapPresenter.setLatlng(latLng);
    adjustPinMapPresenter.setAddress(address);
    if (mOnGetLocationPropertyListener != null) {
      adjustPinMapPresenter.setFromSummary(true).pushView();
    } else {
      adjustPinMapPresenter.pushView();
    }
  }

  @Override
  public void goBack() {
    back();
  }

  @Override
  public void goToPropertyDetail(Address address) {
    if (address == null || createPropertyDTO == null) return;
    createPropertyDTO.setmCountry(address.getCountry());
    createPropertyDTO.setmStreetLineOne(address.getStreet());
    createPropertyDTO.setmStreetLineTwo(address.getStreet2());
    createPropertyDTO.setmPostalCode(address.getPostalCode());
    createPropertyDTO.setmBuilding(address.getBuilding());
    createPropertyDTO.setmFloor(address.getFloor());
    createPropertyDTO.setmUnit(address.getUnit());
    if (latLng != null) {
      createPropertyDTO.setmLatitude(latLng.latitude);
      createPropertyDTO.setmLongitude(latLng.longitude);
    }
    new PropertyDetailsPresenter(mContainerView)
            .setCreatePropertyDTO(createPropertyDTO)
            .pushView();
  }

  @Override
  public void goBackToSummary(Address address) {
    String floorUnit[];
    if (address.getFloor() != null && address.getFloor().contains("/")) {
      floorUnit = address.getFloor().split("/");
      createPropertyDTO.setmFloor(floorUnit[0]);
      createPropertyDTO.setmUnit(floorUnit[0]);
    }
    createPropertyDTO.setmBuilding(address.getBuilding());
    createPropertyDTO.setmStreetLineOne(address.getStreet());
    createPropertyDTO.setmStreetLineTwo(address.getStreet2());
    createPropertyDTO.setmCountry(address.getCountry());
    createPropertyDTO.setmPostalCode(address.getPostalCode());
    createPropertyDTO.setmLatitude(latLng.latitude);
    createPropertyDTO.setmLongitude(latLng.longitude);
    mOnGetLocationPropertyListener.onGetLocationPropertySuccessful(createPropertyDTO);
    mContainerView.back();
  }

  @Override
  public void onDragEnd(LatLng latLng) {
    mView.addLocation(latLng, " ");
    this.latLng = latLng;
  }

  public LocationAddressPresenter setCreatePropertyDTO(CreatePropertyDTO createPropertyDTO) {
    this.createPropertyDTO = createPropertyDTO;
    return this;
  }

  public LocationAddressPresenter setPlaceId(String placeId) {
    mPlaceId = placeId;
    return this;
  }

  @Override
  public void onBackAddr() {

  }

  public interface OnGetLocationPropertyListener {
    void onGetLocationPropertySuccessful(CreatePropertyDTO createPropertyDTO);
  }
}
