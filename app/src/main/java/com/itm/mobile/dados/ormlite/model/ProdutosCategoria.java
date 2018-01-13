package com.itm.mobile.dados.ormlite.model;

import com.google.gson.annotations.SerializedName;
import com.itm.mobile.petlover.R;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@DatabaseTable(tableName="TB_PRODUTOSCATEGORIA")
public class ProdutosCategoria implements Serializable {

    public final static String CATERORIA_ID = "id";
    public final static String IMAGEM = "imagem";
    public final static String DESCRICAO = "descricao";
    public final static String TEXTO = "texto";

    @SerializedName("id")
    @DatabaseField(generatedId = true)
    private UUID id;
    @SerializedName("imagem")
    @DatabaseField(canBeNull = false)
    String imagem;
    @SerializedName("descricao")
    @DatabaseField(canBeNull = true)
    String descricao;
    @SerializedName("texto")
    @DatabaseField(canBeNull = true)
    String texto;

    public ProdutosCategoria() {
    }

    public ProdutosCategoria(String imagem, String descricao, String texto) {
        this.imagem = imagem;
        this.descricao = descricao;
        this.texto = texto;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<?> populateCateg(){
        List<ProdutosCategoria> list  = new ArrayList<>();
        //ProdutosCategoria(int imagem, String descricao, String texto)
        list.add(new ProdutosCategoria("ic_mn_pet_64dp.png", "", ""));
        return list;
    }
}
