package com.itm.mobile.dados.retrofit.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BackupServiceGenerator {

    private Context mContext;

    public BackupServiceGenerator(Context context) {
        mContext = context;
    }

    //public static final String API_BASE_URL = "http://192.168.1.150/api/";

    private static Retrofit retrofit;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public <S> S createService(Class<S> serviceClass, String username, String password) {
        if (username != null && password != null) {
            String credentials = username + ":" + password;
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
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


        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(mContext);
        String mUrl_Base = SP.getString("Host", null);

        OkHttpClient client = httpClient.addInterceptor(logging).build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(mUrl_Base)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

    public static Retrofit retrofit() {
        return retrofit;
    }
}

