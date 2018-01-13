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


@DatabaseTable(tableName="TB_PRODUTOSPROMOCAO")
public class ProdutosPromocao implements Serializable {

    public final static String PRODUTO_ID = "produto_id";
    public final static String IMAGEM = "imagem";
    public final static String DT_INICIO = "dt_inicio";
    public final static String DT_TERMINO = "dt_termino";
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
    @SerializedName("dt_inicio")
    @DatabaseField(canBeNull = false,  columnName = "DT_INICIO", dataType = DataType.DATE_STRING,
            format = "yyyy-MM-dd HH:mm:ss")
    Date dt_inicio;
    @SerializedName("dt_termino")
    @DatabaseField(canBeNull = false,  columnName = "DT_TERMINO", dataType = DataType.DATE_STRING,
            format = "yyyy-MM-dd HH:mm:ss")
    Date dt_termino;
    @SerializedName("dt_reg")
    @DatabaseField(canBeNull = false,  columnName = "DT_REG", dataType = DataType.DATE_STRING,
            format = "yyyy-MM-dd HH:mm:ss")
    Date dt_reg;
    @SerializedName("preco")
    @DatabaseField(canBeNull = true)
    double preco;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, columnName = PRODUTO_ID)
    public Produtos produtos;


    public ProdutosPromocao(){
    }

    public ProdutosPromocao(String imagem, String titulo, String texto, Date dt_inicio, Date dt_termino, Date dt_reg, double preco) {
        this.imagem = imagem;
        this.titulo = titulo;
        this.texto = texto;
        this.dt_inicio = dt_inicio;
        this.dt_termino = dt_termino;
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

    public Date getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(Date dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public Date getDt_termino() {
        return dt_termino;
    }

    public void setDt_termino(Date dt_termino) {
        this.dt_termino = dt_termino;
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

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    public List<?> populateCateg(){
        List<ProdutosPromocao> list  = new ArrayList<>();
        //Produtos(int imagem, String titulo, String texto, Date dt_reg, double preco)
        //list.add();
        return list;
    }
}
