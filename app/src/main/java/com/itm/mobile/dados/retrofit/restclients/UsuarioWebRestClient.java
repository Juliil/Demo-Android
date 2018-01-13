package com.itm.mobile.dados.retrofit.restclients;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itm.mobile.config.Constants;
import com.itm.mobile.dados.retrofit.apiservices.UsuarioWebApiInterface;
import com.itm.mobile.dados.retrofit.models.LoginWeb;
import com.itm.mobile.dados.retrofit.models.UsuarioWeb;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsuarioWebRestClient {

    private static UsuarioWebRestClient instance = null;

    private ResultReadyCallback callback;
    private ResultGetCallback getCallback;

    private UsuarioWebApiInterface service;
    List<UsuarioWeb> users = new ArrayList<>();
    boolean success = false;
    UsuarioWeb returnObj = null;
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public UsuarioWebRestClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Gson gson = new GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        service = retrofit.create(UsuarioWebApiInterface.class);
    }

    public void setCallback(ResultReadyCallback callback) {
        this.callback = callback;
    }

    public void setCallback(ResultGetCallback callback) {
        this.getCallback = callback;
    }

    public static UsuarioWebRestClient getInstance() {
        if(instance == null) {
            instance = new UsuarioWebRestClient();
        }
        return instance;
    }

    public interface ResultReadyCallback {
        public void resultReady(List<UsuarioWeb> users);
    }

    public interface ResultGetCallback {
        public void resultReady(UsuarioWeb user);
    }

    public List<UsuarioWeb> getUsers() {
        Call<List<UsuarioWeb>> userlist = service.users();
        userlist.enqueue(new Callback<List<UsuarioWeb>>() {
            @Override
            public void onResponse(Call<List<UsuarioWeb>> call, Response<List<UsuarioWeb>> response) {
                if (response.isSuccessful()) {
                    users = response.body();
                    callback.resultReady(users);
                }
            }

            @Override
            public void onFailure(Call<List<UsuarioWeb>> call, Throwable t) { Log.e("REST", t.getMessage()); }

        });
        return users;
    }

    public UsuarioWeb getUser(String Id) {
        Call<UsuarioWeb> userlist = service.getUser(Id);
        userlist.enqueue(new Callback<UsuarioWeb>() {
            @Override
            public void onResponse(Call<UsuarioWeb> call, Response<UsuarioWeb> response) {
                if (response.isSuccessful()) {
                    returnObj = response.body();
                }
                getCallback.resultReady(returnObj);
            }

            @Override
            public void onFailure(Call<UsuarioWeb> call, Throwable t) { Log.e("REST", t.getMessage()); getCallback.resultReady(returnObj);}

        });
        return returnObj;
    }

    public UsuarioWeb login(String email, String passwd) {
        Call<UsuarioWeb> userlist = service.getLogin();
        userlist.enqueue(new Callback<UsuarioWeb>() {
            @Override
            public void onResponse(Call<UsuarioWeb> call, Response<UsuarioWeb> response) {
                if (response.isSuccessful()) {
                    returnObj = response.body();
                }
                getCallback.resultReady(returnObj);
            }

            @Override
            public void onFailure(Call<UsuarioWeb> call, Throwable t) { Log.e("REST", t.toString()); getCallback.resultReady(returnObj);}

        });
        return returnObj;
    }

    public boolean createUser(final Context ctx, UsuarioWeb user) {
        Call<UsuarioWeb> u = service.createUser(user);
        u.enqueue(new Callback<UsuarioWeb>() {
            @Override
            public void onResponse(Call<UsuarioWeb> call, Response<UsuarioWeb> response) {
                success = response.isSuccessful();
                if(success) {
                    Toast.makeText(ctx, "User Created", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ctx, "Couldn't create user", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UsuarioWeb> call, Throwable t) {
                Log.w("REST", t.getMessage());
                Toast.makeText(ctx, "Couldn't create user", Toast.LENGTH_SHORT).show();
            }
        });
        return success;
    }
}
