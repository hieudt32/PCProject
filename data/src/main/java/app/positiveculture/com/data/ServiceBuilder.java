package app.positiveculture.com.data;

import android.util.TimeUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import app.positiveculture.com.data.pref.PrefWrapper;
import app.positiveculture.com.data.response.dto.User;
import app.positiveculture.com.data.response.dto.UserDeserialier;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Web services builder
 * Created by hungdn on 23/8/2017.
 */
public class ServiceBuilder {
  private static ServiceBuilder sInstance;
  private Retrofit mRetrofit;
  private ApiService mApiService;
  private String mBaseUrl;

  public ServiceBuilder(String endPoint) {
    mBaseUrl = endPoint;
  }

  public static ServiceBuilder getInstance() {
    return sInstance;
  }

  public static void init(String endPoint) {
    sInstance = new ServiceBuilder(endPoint);
  }

  private Retrofit getRetrofit(String endPoint) {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(new Interceptor() {
              // User agent default
              @Override
              public Response intercept(Chain chain) throws IOException {
                // Set original User agent
                Request original = chain.request();

                User user = PrefWrapper.getInstance().getUser();

                // User token
                String token = user == null ? "" : user.getToken();
                String userToken = "";
                if (token != null && token.compareTo("") != 0) {
                  userToken = "Token " + token;
                }
                // User type
                String userType = user == null ? "" : user.getType();

                // Build request with headers
                Request request = original.newBuilder()
                        .header("DEVICE-TYPE", "android")
                        .header("DEVICE-TOKEN", "")
                        .header("Authorization", userToken)
                        .header("USER-TYPE", userType)
                        .method(original.method(), original.body())
                        .build();


                return chain.proceed(request);
              }
            })
            .build();

    if (mRetrofit == null) {

      Gson gson = new GsonBuilder()
              .registerTypeAdapter(User.class, new UserDeserialier())
              .create();

      mRetrofit = new Retrofit.Builder()
              .baseUrl(endPoint)
              .client(client)
              .addConverterFactory(GsonConverterFactory.create(gson))
              .build();
    }

    return mRetrofit;
  }

  public ApiService getService() {
    if (mApiService == null) {
      mApiService = getRetrofit(mBaseUrl).create(ApiService.class);
    }

    return mApiService;
  }

  public ApiService getService(String endPoint) {
    if (mApiService == null) {
      mApiService = getRetrofit(endPoint).create(ApiService.class);
    }

    return mApiService;
  }
}
