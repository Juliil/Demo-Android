package com.itm.mobile.dados.ormlite.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@DatabaseTable(tableName="TB_PRODUTOS")
public class Produtos implements Serializable {

    public final static String PRODUTO_ID = "id";
    public final static String CATEGORIA_ID = "categoria_id";
    public final static String IMAGEM = "imagem";
    public final static String TITULO = "titulo";
    public final static String TEXTO = "texto";
    public final static String DT_REG = "dt_reg";
    public final static String PRECO = "preco";

    @SerializedName("id")
    @DatabaseField(generatedId = true)
    private UUID id;
    @SerializedName("imagem")
    @DatabaseField(canBeNull = true)
    String imagem;
    @SerializedName("titulo")
    @DatabaseField(canBeNull = true)
    String titulo;
    @SerializedName("texto")
    @DatabaseField(canBeNull = true)
    String texto;
    @SerializedName("dt_reg")
    @DatabaseField(canBeNull = false,  columnName = "DT_REG", dataType = DataType.DATE_STRING,
            format = "yyyy-MM-dd HH:mm:ss")
    Date dt_reg;
    @SerializedName("preco")
    @DatabaseField(canBeNull = true)
    double preco;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, columnName = CATEGORIA_ID)
    public ProdutosCategoria produtos_categoria;


    public Produtos(){
    }

    public Produtos(String imagem, String titulo, String texto, Date dt_reg, double preco) {
        this.imagem = imagem;
        this.titulo = titulo;
        this.texto = texto;
        this.dt_reg = dt_reg;
        this.preco = preco;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getDt_reg() {
        return dt_reg;
    }

    public void setDt_reg(Date dt_reg) {
        this.dt_reg = dt_reg;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public ProdutosCategoria getProdutos_categoria() {
        return produtos_categoria;
    }

    public void setProdutos_categoria(ProdutosCategoria produtos_categoria) {
        this.produtos_categoria = produtos_categoria;
    }

    public List<?> populateCateg(){
        List<Produtos> list  = new ArrayList<>();
        //Produtos(int imagem, String titulo, String texto, Date dt_reg, double preco)
        //list.add();
        return list;
    }
}
