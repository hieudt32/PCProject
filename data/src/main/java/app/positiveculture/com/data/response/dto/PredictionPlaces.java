package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by HaiLS on 10/11/2017.
 */

public class PredictionPlaces {
  @SerializedName("predictions")
  private ArrayList<GoogleMapSuggestionDTO> mListSuggestedPlace;
  @SerializedName("status")
  private String mStatus;

  public PredictionPlaces() {
    mListSuggestedPlace = new ArrayList<>();
  }

  public ArrayList<GoogleMapSuggestionDTO> getListSuggestedPlace() {
    return mListSuggestedPlace;
  }

  public void setListSuggestedPlace(ArrayList<GoogleMapSuggestionDTO> listSuggestedPlace) {
    this.mListSuggestedPlace = listSuggestedPlace;
  }

  public String getStatus() {
    return mStatus;
  }

  public void setStatus(String mStatus) {
    this.mStatus = mStatus;
  }
}
