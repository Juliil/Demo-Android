package com.itm.mobile.dados.retrofit.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginWeb implements Serializable {

    @SerializedName("id")
    public String id;
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;
    @SerializedName("tipo")
    public String tipo;
}
