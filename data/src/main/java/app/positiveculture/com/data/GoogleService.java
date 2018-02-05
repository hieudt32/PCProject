package app.positiveculture.com.data;

import java.util.List;

import app.positiveculture.com.data.response.dto.GoogleMapDetailDTO;
import app.positiveculture.com.data.response.dto.GoogleMapSearchDTO;
import app.positiveculture.com.data.response.dto.GoogleMapSuggestionDTO;
import app.positiveculture.com.data.response.dto.PredictionPlaces;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by BB on 8/29/2017.
 */

public interface GoogleService {
	@GET("api/place/autocomplete/json")
	Call<PredictionPlaces> getLocationSuggestion(@Query("input") String input,
																							 @Query("components") String components,
																							 @Query("key") String key);

	@GET("api/place/textsearch/json")
	Call<GoogleMapSearchDTO> getLocationSearch(@Query("query") String query,
																						 @Query("key") String key);


	@GET("api/place/details/json")
	Call<GoogleMapDetailDTO> getLocationDetail(@Query("placeid") String placeId,
																						 @Query("key") String detailKey,
																						 @Query("language") String lan);
}
