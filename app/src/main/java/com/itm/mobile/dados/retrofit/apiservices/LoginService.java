package com.itm.mobile.dados.retrofit.apiservices;

import com.itm.mobile.dados.retrofit.models.Token;
import com.itm.mobile.dados.retrofit.models.UsuarioWeb;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by WLFULTRABOOK on 02/02/2016.
 */
public interface LoginService {

    @FormUrlEncoded
    @POST("token")
    Call<Token> requestToken(@Field("grant_type") String grant_type,
                             @Field("username") String email,
                             @Field("password") String password);

    @POST("Account/Register")
    Call<ResponseBody> createUser(@Body UsuarioWeb user);
}
