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


@DatabaseTable(tableName="TB_PET")
public class Pet implements Serializable {

    public final static String PET_ID = "id";
    public final static String RACA = "raca";
    public final static String ESPECIE = "especie";
    public final static String PORTE = "porte";
    public final static String COR = "cor";
    public final static String NOME = "nome";
    public final static String APELIDO = "apelido";
    public final static String PAI = "pai";
    public final static String MAE = "mae";
    public final static String TAGID = "tagid";
    public final static String RESPONSAVEL = "responsavel";
    public final static String CONTATO = "contato";
    public final static String DT_NASC = "dt_nasc";
    public final static String DT_REG = "dt_reg";
    public final static String CARACTERISTICAS = "caracteristicas";
    public final static String FOTO = "foto";
    public final static String SEXO = "sexo";


    @SerializedName("id")
    @DatabaseField(generatedId = true)
    private UUID id;
    @SerializedName("raca")
    @DatabaseField(canBeNull = true)
    String raca;
    @SerializedName("especie")
    @DatabaseField(canBeNull = true)
    String especie;
    @SerializedName("porte")
    @DatabaseField(canBeNull = true)
    String porte;
    @SerializedName("cor")
    @DatabaseField(canBeNull = true)
    String cor;
    @SerializedName("nome")
    @DatabaseField(canBeNull = true)
    String nome;
    @SerializedName("pai")
    @DatabaseField(canBeNull = true)
    String pai;
    @SerializedName("mae")
    @DatabaseField(canBeNull = true)
    String mae;
    @SerializedName("tagid")
    @DatabaseField(canBeNull = true)
    String tagid;
    @SerializedName("responsavel")
    @DatabaseField(canBeNull = true)
    String responsavel;
    @SerializedName("contato")
    @DatabaseField(canBeNull = true)
    String contato;
    @SerializedName("dt_nasc")
    @DatabaseField(canBeNull = false,  columnName = "DT_NASC", dataType = DataType.DATE_STRING,
            format = "yyyy-MM-dd HH:mm:ss")
    Date dt_nasc;
    @SerializedName("dt_reg")
    @DatabaseField(canBeNull = false,  columnName = "DT_REG", dataType = DataType.DATE_STRING,
            format = "yyyy-MM-dd HH:mm:ss")
    Date dt_reg;
    @SerializedName("caracteristicas")
    @DatabaseField(canBeNull = true)
    String caracteristicas;
    @SerializedName("foto")
    @DatabaseField(canBeNull = true)
    String foto;
    @SerializedName("sexo")
    @DatabaseField(canBeNull = true)
    String sexo;

    public Pet(){
    }

    public Pet(String raca, String especie, String porte, String cor, String nome, String pai, String mae,
               String tagid, String responsavel, String contato, Date dt_nasc, Date dt_reg, String caracteristicas,
               String foto, String sexo) {
        this.raca = raca;
        this.especie = especie;
        this.porte = porte;
        this.cor = cor;
        this.nome = nome;
        this.pai = pai;
        this.mae = mae;
        this.tagid = tagid;
        this.responsavel = responsavel;
        this.contato = contato;
        this.dt_nasc = dt_nasc;
        this.dt_reg = dt_reg;
        this.caracteristicas = caracteristicas;
        this.foto = foto;
        this.sexo = sexo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Date getDt_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(Date dt_nasc) {
        this.dt_nasc = dt_nasc;
    }

    public Date getDt_reg() {
        return dt_reg;
    }

    public void setDt_reg(Date dt_reg) {
        this.dt_reg = dt_reg;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<?> populateCateg(){
        List<Pet> list  = new ArrayList<>();

        list.add(new Pet("SRD", "CANINA", "MEDIO", "MARRON E BRANCO", "NOME DO PET A", "PAI DO PET", "MAE DO PET",
                "XXX-XXX-XXX", "RESPONSAVEL PELO PET", "(92)9 98159-7235", new Date(), new Date(), "MALHADO COM MASCARA NA FACE",
                "img_pet_foto_200_170_7.jpg", "M"));
        list.add(new Pet("SRD", "CANINA", "MEDIO", "MARRON E BRANCO", "NOME DO PET B", "PAI DO PET", "MAE DO PET",
                "XXX-XXX-XXX", "RESPONSAVEL PELO PET", "(92)9 98159-7235", new Date(), new Date(), "MALHADO COM MASCARA NA FACE",
                "img_pet_foto_200_170_1.png", "M"));
        list.add(new Pet("SRD", "CANINA", "MEDIO", "MARRON E BRANCO", "NOME DO PET C", "PAI DO PET", "MAE DO PET",
                "XXX-XXX-XXX", "RESPONSAVEL PELO PET", "(92)9 98159-7235", new Date(), new Date(), "MALHADO COM MASCARA NA FACE",
                "img_pet_foto_200_170_2.jpg", "M"));
        list.add(new Pet("SRD", "CANINA", "MEDIO", "MARRON E BRANCO", "NOME DO PET D", "PAI DO PET", "MAE DO PET",
                "XXX-XXX-XXX", "RESPONSAVEL PELO PET", "(92)9 98159-7235", new Date(), new Date(), "MALHADO COM MASCARA NA FACE",
                "img_pet_foto_200_170_3.jpg", "M"));
        list.add(new Pet("SRD", "CANINA", "MEDIO", "MARRON E BRANCO", "NOME DO PET E", "PAI DO PET", "MAE DO PET",
                "XXX-XXX-XXX", "RESPONSAVEL PELO PET", "(92)9 98159-7235", new Date(), new Date(), "MALHADO COM MASCARA NA FACE",
                "img_pet_foto_200_170_4.jpg", "M"));
        list.add(new Pet("SRD", "CANINA", "MEDIO", "MARRON E BRANCO", "NOME DO PET F", "PAI DO PET", "MAE DO PET",
                "XXX-XXX-XXX", "RESPONSAVEL PELO PET", "(92)9 98159-7235", new Date(), new Date(), "MALHADO COM MASCARA NA FACE",
                "img_pet_foto_200_170_5.jpg", "M"));
        return list;
    }
}
