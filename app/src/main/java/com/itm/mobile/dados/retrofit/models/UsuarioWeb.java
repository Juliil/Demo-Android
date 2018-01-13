package com.itm.mobile.dados.retrofit.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UsuarioWeb implements Serializable {

    @SerializedName("id")
    String Id;
    @SerializedName("nome")
    String nome;
    @SerializedName("cpf")
    String cpf;
    @SerializedName("email")
    String email;
    @SerializedName("senha")
    String password;
    @SerializedName("confirmPassword")
    String confirm_password;
    @SerializedName("useR_CATEG")
    String user_categ;
    @SerializedName("sexo")
    String sexo;
    @SerializedName("imagem")
    String imagem;
    @SerializedName("token")
    String token;

    public UsuarioWeb() {
    }

    public UsuarioWeb(String email, String password, String confirm_password) {
        this.email = email;
        this.password = password;
        this.confirm_password = confirm_password;
    }

    public UsuarioWeb(String email, String nome, String password, String confirm_password) {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.confirm_password = confirm_password;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getUser_categ() {
        return user_categ;
    }

    public void setUser_categ(String user_categ) {
        this.user_categ = user_categ;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
