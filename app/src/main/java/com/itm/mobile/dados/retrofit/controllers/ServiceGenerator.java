package com.itm.mobile.dados.retrofit.controllers;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static String mBase_URL;

    public ServiceGenerator(String mURL) {
        mBase_URL = mURL;
    }

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass, String mURL) {
        mBase_URL = mURL;
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String username, String password) {
        if (username != null && password != null) {
            String credentials = username + ":" + password;
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", basic)
                            .header("Accept", "applicaton/json")
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(mBase_URL)
                        .addConverterFactory(GsonConverterFactory.create());
        OkHttpClient client = httpClient.addInterceptor(logging).build();
        //OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}

