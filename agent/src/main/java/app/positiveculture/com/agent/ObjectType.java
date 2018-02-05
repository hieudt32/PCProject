package app.positiveculture.com.agent;

/**
 * Created by HaiLS on 14/09/2017.
 */

public enum ObjectType {
  AGENT_REGISTER(0),
  AGENT_EDIT_PROFILE(1);

  private int key;

  private ObjectType(int key) {
    this.key = key;
  }
}
