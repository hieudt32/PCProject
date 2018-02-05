package app.positiveculture.com.agent.screen.location;

import android.widget.Toast;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.ArrayList;
import java.util.List;

import app.positiveculture.com.agent.screen.locationaddress.LocationAddressFragment;
import app.positiveculture.com.agent.screen.locationaddress.LocationAddressPresenter;
import app.positiveculture.com.agent.screen.searchlocation.SearchLocationPresenter;
import app.positiveculture.com.agent.utils.GoogleMapApiKey;
import app.positiveculture.com.data.GoogleServiceBuilder;
import app.positiveculture.com.data.response.dto.CreatePropertyDTO;
import app.positiveculture.com.data.response.dto.GoogleMapSuggestionDTO;
import app.positiveculture.com.data.response.dto.Location;
import app.positiveculture.com.data.response.dto.PredictionPlaces;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The Location Presenter
 */
public class LocationPresenter extends Presenter<LocationContract.View, LocationContract.Interactor>
        implements LocationContract.Presenter, LocationAddressFragment.OnBackLocationListener, SearchAdapter.OnItemLocationClickListener {

  private CreatePropertyDTO createPropertyDTO;
  private SearchAdapter searchAdapter;

  public LocationPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public LocationContract.View onCreateView() {
    return LocationFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public void goBack() {
    mContainerView.back();
  }

  @Override
  public void searchLocation(String location) {
    GoogleServiceBuilder.getService().getLocationSuggestion(location, "country:sg", GoogleMapApiKey.LOCATION_KEY)
            .enqueue(new Callback<PredictionPlaces>() {
              @Override
              public void onResponse(Call<PredictionPlaces> call, Response<PredictionPlaces> response) {
                PredictionPlaces predictionPlaces = response.body();
                if (predictionPlaces != null && predictionPlaces.getListSuggestedPlace().size() > 0) {
                  searchAdapter = new SearchAdapter(getViewContext(), predictionPlaces.getListSuggestedPlace());
                  mView.bindListLocation(searchAdapter);
                  searchAdapter.setOnItemLocationClickListener(LocationPresenter.this);
                }
              }

              @Override
              public void onFailure(Call<PredictionPlaces> call, Throwable t) {

              }
            });
  }

  @Override
  public LocationContract.Interactor onCreateInteractor() {
    return new LocationInteractor(this);
  }

  @Override
  public void showSearchScreen() {
    new SearchLocationPresenter(mContainerView).pushView();
  }

  @Override
  public void showLocationResult() {
    new SearchLocationPresenter(mContainerView).pushView();
  }

  @Override
  public void showScreenAddr() {
    LocationAddressPresenter locationAddressPresenter = new LocationAddressPresenter(mContainerView);
    ((LocationAddressFragment) locationAddressPresenter.getFragment()).setOnBackLocationListener(this);
    locationAddressPresenter.setCreatePropertyDTO(createPropertyDTO);
    locationAddressPresenter.pushView();
  }

  public LocationPresenter setCreatePropertyDTO(CreatePropertyDTO createPropertyDTO) {
    this.createPropertyDTO = createPropertyDTO;
    return this;
  }

  @Override
  public void onBackLocation() {
    if (searchAdapter != null) searchAdapter.setOnItemLocationClickListener(this);
    mView.enableAddBt();
  }

  @Override
  public void onItemClick(String placeId) {
    LocationAddressPresenter locationAddressPresenter = new LocationAddressPresenter(mContainerView);
    locationAddressPresenter.setPlaceId(placeId);
    locationAddressPresenter.setCreatePropertyDTO(createPropertyDTO);
    ((LocationAddressFragment) locationAddressPresenter.getFragment()).setOnBackLocationListener(this);
    locationAddressPresenter.pushView();
    searchAdapter.setOnItemLocationClickListener(null);
  }
}
