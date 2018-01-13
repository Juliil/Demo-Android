package com.itm.mobile.dados.ormlite.model;

import com.google.gson.annotations.SerializedName;
import com.itm.mobile.petlover.R;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@DatabaseTable(tableName="TB_USUARIO")
public class Usuario implements Serializable {

    public final static String ENDERECO_ID = "endereco_id";
    public final static String EMAIL = "email";
    public final static String LOGIN = "login";
    public final static String SENHA = "senha";
    public final static String CATEG = "categ";
    public final static String DT_REG = "dt_reg";
    public final static String NOME = "name";
    public final static String CPF_CNPJ = "cpf_cnpj";
    public final static String DOC_NO = "doc_no";
    public final static String DOC_TIPO = "doc_tipo";
    public final static String DOC_ORG_EXP = "doc_org_exp";
    public final static String TELEFONE = "telefone";
    public final static String CELULAR = "celular";
    public final static String DT_NASC = "dt_nasc";
    public final static String AVATAR = "avatar";
    public final static String SEXO = "sexo";

    public final static int CATEG_MORADOR = 0;
    public final static int CATEG_ADMIN = 1;
    public final static int CATEG_SEGURANCA = 2;
    public final static int CATEG_SINDICO = 3;
    public final static int CATEG_PROPRIETARIO = 4;

    @DatabaseField(generatedId = true)
    private UUID id;
    @SerializedName("id")
    @DatabaseField(canBeNull = true)
    String webid;
    @SerializedName("login")
    @DatabaseField(canBeNull = false, unique = true)
    String login;
    @SerializedName("senha")
    @DatabaseField(canBeNull = true)
    String senha;
    @SerializedName("categ")
    @DatabaseField(canBeNull = true)
    Integer categ;
    @SerializedName("dt_reg")
    @DatabaseField(canBeNull = false,  columnName = "DT_REG", dataType = DataType.DATE_STRING,
            format = "yyyy-MM-dd HH:mm:ss")
    Date dt_reg;
    @SerializedName("name")
    @DatabaseField(canBeNull = false)
    String name;
    @SerializedName("cpf_cnpj")
    @DatabaseField(canBeNull = true)
    String cpf_cnpj;
    @SerializedName("doc_no")
    @DatabaseField(canBeNull = true)
    String doc_no;
    @SerializedName("doc_tipo")
    @DatabaseField(canBeNull = true)
    String doc_tipo;
    @SerializedName("doc_org_exp")
    @DatabaseField(canBeNull = true)
    String doc_org_exp;
    @SerializedName("email")
    @DatabaseField(canBeNull = true)
    String email;
    @SerializedName("telefone")
    @DatabaseField(canBeNull = true)
    String telefone;
    @SerializedName("celular")
    @DatabaseField(canBeNull = true)
    String celular;
    @SerializedName("dt_nasc")
    @DatabaseField(canBeNull = true)
    Date dt_nasc;
    @SerializedName("avatar")
    @DatabaseField(canBeNull = true)
    String avatar;
    @SerializedName("sexo")
    @DatabaseField(canBeNull = true)
    String sexo;

    public Usuario(){
    }

    public Usuario(String login, String senha, Integer categ, Date dt_reg, String name, String email, String avatar, String sexo) {
        this.login = login;
        this.senha = senha;
        this.categ = categ;
        this.dt_reg = dt_reg;
        this.name = name;
        this.email = email;
        this.avatar = avatar;
        this.sexo = sexo;
    }

    public Usuario(UUID id, String login, String senha, Integer categ, Date dt_reg, String name, String cpf_cnpj,
                   String doc_no, String doc_tipo, String doc_org_exp, String email, String telefone,
                   String celular, Date dt_nasc, String avatar, String sexo) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.categ = categ;
        this.dt_reg = dt_reg;
        this.name = name;
        this.cpf_cnpj = cpf_cnpj;
        this.doc_no = doc_no;
        this.doc_tipo = doc_tipo;
        this.doc_org_exp = doc_org_exp;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.dt_nasc = dt_nasc;
        this.avatar = avatar;
        this.sexo = sexo;
    }

    public Usuario(String login, String senha, Integer categ, Date dt_reg, String name, String email, String sexo) {
        this.login = login;
        this.senha = senha;
        this.categ = categ;
        this.dt_reg = dt_reg;
        this.name = name;
        this.email = email;
        this.sexo = sexo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getCateg() {
        return categ;
    }

    public void setCateg(Integer categ) {
        this.categ = categ;
    }

    public Date getDt_reg() {
        return dt_reg;
    }

    public void setDt_reg(Date dt_reg) {
        this.dt_reg = dt_reg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public String getDoc_tipo() {
        return doc_tipo;
    }

    public void setDoc_tipo(String doc_tipo) {
        this.doc_tipo = doc_tipo;
    }

    public String getDoc_org_exp() {
        return doc_org_exp;
    }

    public void setDoc_org_exp(String doc_org_exp) {
        this.doc_org_exp = doc_org_exp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getDt_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(Date dt_nasc) {
        this.dt_nasc = dt_nasc;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<?> populateCateg(){
        List<Usuario> list  = new ArrayList<>();
        //Usuario(String login, String senha, Integer categ, Date dt_reg, String name, String email, int avatar, String sexo)
        list.add(new Usuario("administracao@petshow.com", "", Usuario.CATEG_ADMIN, new Date(), "Marley Fonseca de Figueiredo", "Administração@petshow.com.br", "img_marley_male_avatar_128dp.png", "M"));
        list.add(new Usuario("wlfigueiredo@gmail.com", "", Usuario.CATEG_ADMIN, new Date(), "Wilkens de Lima Figueiredo", "wlfigueiredo@gmail.com", "img_empty_man_avatar_128dp.png", "M"));
        return list;
    }
}
