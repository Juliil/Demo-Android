package com.itm.mobile.dados.retrofit.controllers;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Response;

public class ErrorResponse {

    public static String message(Response response){

        JSONObject jsonObject = null;
        String message = null;
        try {
            String error = response.errorBody().string();
            jsonObject = new JSONObject(error);

            message = jsonObject.getString("error_description");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return message;
    }

}