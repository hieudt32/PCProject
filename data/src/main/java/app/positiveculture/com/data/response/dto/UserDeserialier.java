package app.positiveculture.com.data.response.dto;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**UserDeserialier
 *
 * Created by hungdn on 8/31/2017.
 */

public class UserDeserialier implements JsonDeserializer<User> {
  @Override
  public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    User user = null;
    JsonObject jsonObject = json.getAsJsonObject();

    String type = jsonObject.get("type").getAsString();
    if (type.equalsIgnoreCase("member")) {
      user = new MemberDTO();
      user = new Gson().fromJson(json, MemberDTO.class);
    } else if (type.equalsIgnoreCase("agent")) {
      user = new AgentDTO();
      user = new Gson().fromJson(json, AgentDTO.class);
    }

    return user;
  }
}
