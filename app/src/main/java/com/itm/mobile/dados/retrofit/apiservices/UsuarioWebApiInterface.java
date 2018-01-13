package com.itm.mobile.dados.retrofit.apiservices;

import com.itm.mobile.dados.retrofit.models.LoginWeb;
import com.itm.mobile.dados.retrofit.models.UsuarioWeb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface UsuarioWebApiInterface {
    @POST("/api/user")
    Call<UsuarioWeb> createUser(@Body UsuarioWeb user);

    @GET("/api/user")
    Call<List<UsuarioWeb>> users();

    @GET("/api/v1/users/get/{id}")
    Call<UsuarioWeb> getUser(@Path("id") String id);

    @GET("/api/v1/users/getuser")
    Call<UsuarioWeb> getLogin();
}
