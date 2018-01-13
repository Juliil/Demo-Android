package com.itm.mobile.dados.retrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.itm.mobile.dados.retrofit.controllers.ErrorResponse;

public class Token extends ErrorResponse {

    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("expires_in")
    @Expose
    private Integer expiresIn;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName(".issued")
    @Expose
    private String Issued;
    @SerializedName(".expires")
    @Expose
    private String Expires;

    public Token() {
        accessToken = "";
        tokenType = "";
    }

    /**
     *
     * @return
     * The accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     *
     * @param accessToken
     * The access_token
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     *
     * @return
     * The tokenType
     */
    public String getToken_type() {
        return tokenType;
    }

    /**
     *
     * @param token_type
     * The token_type
     */
    public void setToken_type(String token_type) {
        this.tokenType = token_type;
    }

    /**
     *
     * @return
     * The expiresIn
     */
    public Integer getExpiresIn() {
        return expiresIn;
    }

    /**
     *
     * @param expiresIn
     * The expires_in
     */
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     *
     * @return
     * The userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     * The userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     * The Issued
     */
    public String getIssued() {
        return Issued;
    }

    /**
     *
     * @param Issued
     * The .issued
     */
    public void setIssued(String Issued) {
        this.Issued = Issued;
    }

    /**
     *
     * @return
     * The Expires
     */
    public String getExpires() {
        return Expires;
    }

    /**
     *
     * @param Expires
     * The .expires
     */
    public void setExpires(String Expires) {
        this.Expires = Expires;
    }

}