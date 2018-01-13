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


@DatabaseTable(tableName="TB_PETBOOK")
public class PetBook implements Serializable {

    public final static String USUARIO_ID = "usuario_id";
    public final static String PETBBOK_ID = "id";
    public final static String IMAGEM = "imagem";
    public final static String TITULO = "titulo";
    public final static String TEXTO = "texto";
    public final static String DT_REG = "dt_reg";
    public final static String LIKES = "likes";
    public final static String VIEWS = "views";

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
    @SerializedName("likes")
    @DatabaseField(canBeNull = true)
    int likes;
    @SerializedName("views")
    @DatabaseField(canBeNull = true)
    int views;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, columnName = USUARIO_ID)
    public Usuario usuario;


    public PetBook(){
    }

    public PetBook(String imagem, String titulo, String texto, Date dt_reg) {
        this.imagem = imagem;
        this.titulo = titulo;
        this.texto = texto;
        this.dt_reg = dt_reg;

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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<?> populateCateg(){
        List<PetBook> list  = new ArrayList<>();
        //PetBook(int imagem, String titulo, String texto, Date dt_reg, int likes, int views)
        list.add(new PetBook("ic_petlover_logo.png", "Imagem de divulgação do PetShow",
                "Texto para divulgação de anuncio teste ashdoash doaisd oais doais hdoasih " +
                        "doasihd oasid oaisd oais doasi doasihfFSHSdfhsiduhfd fd sdifj osidf " +
                        "osidf osdif osdifh osdfh sod.", new Date()));
        list.add(new PetBook("ic_pethotel_300dp.png", "Imagem de divulgação PetHotel",
                "Texto para divulgação de anuncio teste ashdoash doaisd oais doais hdoasih " +
                        "doasihd oasid oaisd oais doasi doasihfFSHSdfhsiduhfd fd sdifj osidf " +
                        "osidf osdif osdifh osdfh sod.", new Date()));
        list.add(new PetBook("ic_logo_achadoseperdidos.png", "Imagem de divulgação",
                "Texto para divulgação de anuncio teste ashdoash doaisd oais doais hdoasih " +
                        "doasihd oasid oaisd oais doasi doasihfFSHSdfhsiduhfd fd sdifj osidf " +
                        "osidf osdif osdifh osdfh sod.", new Date()));
        list.add(new PetBook("img_logo_splash_512dp.png", "Imagem de ",
                "Texto para divulgação de anuncio teste ashdoash doaisd oais doais hdoasih " +
                        "doasihd oasid oaisd oais doasi doasihfFSHSdfhsiduhfd fd sdifj osidf " +
                        "osidf osdif osdifh osdfh sod.", new Date()));
        return list;
    }
}
