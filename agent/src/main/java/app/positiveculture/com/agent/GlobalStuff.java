package app.positiveculture.com.agent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Global Stuffs
 * Created by NEO on 11/28/2016.
 */

public class GlobalStuff {
  private static final AtomicInteger seed = new AtomicInteger();

  public static int getFreshInt() {
    return seed.incrementAndGet();
  }
}
