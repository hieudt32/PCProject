package app.positiveculture.com.agent.screen.summary;

import com.gemvietnam.base.viper.Interactor;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.CreatePropertyDTO;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.PropertyDTO;

/**
 * The Summary interactor
 */
class SummaryInteractor extends Interactor<SummaryContract.Presenter>
        implements SummaryContract.Interactor {

  SummaryInteractor(SummaryContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void createProperty(CreatePropertyDTO createPropertyDTO, CommonCallback<PropertyDTO> commonCallback) {
    JsonArray listGallery = new JsonArray();
    JsonArray listSeller = new JsonArray();
    if (createPropertyDTO.getmGallery() != null && !createPropertyDTO.getmGallery().isEmpty()) {
      if (createPropertyDTO.getmGallery().size() <= 1) {
        createPropertyDTO.setmFeatureImage(createPropertyDTO.getmGallery().get(0).getId());
      } else {
        for (int i = 0; i < createPropertyDTO.getmGallery().size(); i++) {
          if (i == 0) {
            createPropertyDTO.setmFeatureImage(createPropertyDTO.getmGallery().get(i).getId());
          } else {
            listGallery.add(createPropertyDTO.getmGallery().get(i).getId());
          }
        }
      }
      createPropertyDTO.setmListGallery(listGallery);
    }

    if (createPropertyDTO.getmListOwner() != null && !createPropertyDTO.getmListOwner().isEmpty()) {
      for (MemberDTO memberDTO : createPropertyDTO.getmListOwner()) {
        listSeller.add(memberDTO.getId());
      }
      createPropertyDTO.setmPropertySeller(listSeller);
    }

    ServiceBuilder.getInstance().getService().createProperty(createPropertyDTO).enqueue(commonCallback);
  }
}
