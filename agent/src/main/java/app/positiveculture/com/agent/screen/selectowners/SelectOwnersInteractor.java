package app.positiveculture.com.agent.screen.selectowners;

import com.gemvietnam.base.viper.Interactor;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.MemberDTO;
import app.positiveculture.com.data.response.dto.OtpDTO;

/**
 * The SelectOwners interactor
 */
class SelectOwnersInteractor extends Interactor<SelectOwnersContract.Presenter>
        implements SelectOwnersContract.Interactor {

  SelectOwnersInteractor(SelectOwnersContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getListOwners(int limit, int offSet, String nameSearch, CommonCallback<List<MemberDTO>> commonCallback) {
    ServiceBuilder.getInstance().getService().getListOwner(limit, offSet, nameSearch).enqueue(commonCallback);
  }

  @Override
  public void assignClient(long idOTP, List<MemberDTO> mListSelected, CommonCallback<OtpDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().buyerAgentAssignBuyer(String.valueOf(idOTP), createObject(mListSelected)).enqueue(commonCallback);
  }

  @Override
  public void reassignedClient(long idOTP, List<MemberDTO> mListSelected, CommonCallback<OtpDTO> commonCallback) {
    ServiceBuilder.getInstance().getService().buyerAgentReassignBuyer(String.valueOf(idOTP), createObject(mListSelected)).enqueue(commonCallback);
  }

  private JsonObject createObject(List<MemberDTO> list) {
    JsonArray jsonArray = new JsonArray();
    if (list != null && !list.isEmpty()) {
      for (MemberDTO memberDTO : list) {
        jsonArray.add(memberDTO.getId());
      }
      JsonObject object = new JsonObject();
      object.add("property_buyer", jsonArray);
      return object;
    } else {
      return null;
    }
  }
}
