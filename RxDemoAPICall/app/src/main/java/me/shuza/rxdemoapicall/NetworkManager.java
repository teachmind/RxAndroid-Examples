package me.shuza.rxdemoapicall;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * :=  created by:  Shuza
 * :=  create date:  11/5/2017
 * :=  (C) CopyRight Shuza
 * :=  www.shuza.me
 * :=  shuza.sa@gmail.com
 * :=  Fun  :  Coffee  :  Code
 **/

public class NetworkManager {
    private static OkHttpClient client;
    public static String BASE_URL = "http://shuza.me/tmp_file/";

    private NetworkManager() {
    }

    public static OkHttpClient getClient() {
        if (client == null) {
            client = createOkHttpClient();
        }
        return client;
    }

    public static Retrofit createRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl originalUrl = originalRequest.url();

                HttpUrl url = originalUrl.newBuilder()
                        //.addQueryParameter("username", "demo")
                        .build();
                Request.Builder requestBuilder = originalRequest.newBuilder().url(url);

                LogUtil.printLogMessage("original url", originalUrl.toString());
                LogUtil.printLogMessage("new url", url.toString());

                return chain.proceed(requestBuilder.build());
            }
        });
        return builder.build();
    }
}
