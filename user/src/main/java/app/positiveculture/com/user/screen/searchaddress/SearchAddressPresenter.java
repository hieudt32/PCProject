package app.positiveculture.com.user.screen.searchaddress;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.StringUtils;

import app.positiveculture.com.agent.screen.location.SearchAdapter;
import app.positiveculture.com.agent.utils.GoogleMapApiKey;
import app.positiveculture.com.data.GoogleServiceBuilder;
import app.positiveculture.com.data.response.dto.Address;
import app.positiveculture.com.data.response.dto.AddressComponent;
import app.positiveculture.com.data.response.dto.GoogleMapDetailDTO;
import app.positiveculture.com.data.response.dto.PredictionPlaces;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The Search_Address Presenter
 */
public class SearchAddressPresenter extends Presenter<SearchAddressContract.View, SearchAddressContract.Interactor>
        implements SearchAddressContract.Presenter, SearchAdapter.OnItemLocationClickListener {

  private SearchAdapter searchAdapter;
  private OnClickItemLocationListener mOnClickItemLocationListener;
  private GoogleMapDetailDTO mGoogleMapDetailDTO;
  private String type;

  public void setOnClickItemLocationListener(OnClickItemLocationListener onClickItemLocationListener) {
    this.mOnClickItemLocationListener = onClickItemLocationListener;
  }

  public SearchAddressPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SearchAddressContract.View onCreateView() {
    return SearchAddressFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public SearchAddressContract.Interactor onCreateInteractor() {
    return new SearchAddressInteractor(this);
  }

  @Override
  public void searchLocation(String query) {
    GoogleServiceBuilder.getService().getLocationSuggestion(query, "country:sg", GoogleMapApiKey.LOCATION_KEY).enqueue(new Callback<PredictionPlaces>() {
      @Override
      public void onResponse(Call<PredictionPlaces> call, Response<PredictionPlaces> response) {
        PredictionPlaces predictionPlaces = response.body();
        if (predictionPlaces != null && predictionPlaces.getListSuggestedPlace().size() > 0) {
          searchAdapter = new SearchAdapter(getViewContext(), predictionPlaces.getListSuggestedPlace());
          mView.bindListLocation(searchAdapter);
          searchAdapter.setOnItemLocationClickListener(SearchAddressPresenter.this);
        } else {
          back();
        }
      }

      @Override
      public void onFailure(Call<PredictionPlaces> call, Throwable t) {

      }
    });
  }

  @Override
  public void onItemClick(String placeId) {
    mView.showProgress();
    GoogleServiceBuilder.getService().getLocationDetail(placeId, GoogleMapApiKey.INFO_LOCATION_KEY, "en_US").enqueue(new Callback<GoogleMapDetailDTO>() {
      @Override
      public void onResponse(Call<GoogleMapDetailDTO> call, Response<GoogleMapDetailDTO> response) {
        if (response.isSuccessful()) {
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
            mOnClickItemLocationListener.onLocationItemClick(type, address);
            back();
          }
          mView.hideProgress();
        }
      }

      @Override
      public void onFailure(Call<GoogleMapDetailDTO> call, Throwable t) {

      }
    });
  }

  public SearchAddressPresenter setType(String type) {
    this.type = type;
    return this;
  }

  public interface OnClickItemLocationListener {
    void onLocationItemClick(String type, Address address);
  }
}
