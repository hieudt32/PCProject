package com.positiveculture.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.positiveculture.app.utils.TypefaceUtil;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.pref.PrefWrapper;


/**
 * Application context
 * Created by neo on 8/1/17.
 */

public class App extends Application {
  private static App sInstance;

  @Override
  public void onCreate() {
    super.onCreate();
    sInstance = this;

    Fresco.initialize(getApplicationContext());
    TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/HelveticaNeue.ttf"); // font from assets: "assets/fonts/HelveticaNeue.ttf

    PrefWrapper.init(this);
    ServiceBuilder.init(BuildConfig.END_POINT);
  }

  public static App getInstance() {
    return sInstance;
  }
}
