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


@DatabaseTable(tableName="TB_PERDIDOS")
public class Perdidos implements Serializable {

    public final static String PET_ID = "pet_id";
    public final static String DT_REG = "dt_reg";
    public final static String DT_FATO = "dt_fato";
    public final static String LOCAL = "local";
    public final static String LOCAL_LAT = "local_lat";
    public final static String LOCAL_LONG = "local_long";
    public final static String SITUACAO = "situacao"; // 0 - Pendente, 1 - Encontrado

    @SerializedName("id")
    @DatabaseField(generatedId = true)
    private UUID id;
    @SerializedName("dt_reg")
    @DatabaseField(canBeNull = false,  columnName = "DT_REG", dataType = DataType.DATE_STRING,
            format = "yyyy-MM-dd HH:mm:ss")
    Date dt_reg;
    @SerializedName("dt_fato")
    @DatabaseField(canBeNull = false,  columnName = "DT_FATO", dataType = DataType.DATE_STRING,
            format = "yyyy-MM-dd HH:mm:ss")
    Date dt_fato;
    @SerializedName("local")
    @DatabaseField(canBeNull = true)
    private String local;
    @SerializedName("local_lat")
    @DatabaseField(canBeNull = true)
    private Double local_lat;
    @SerializedName("local_long")
    @DatabaseField(canBeNull = true)
    private Double local_long;
    @SerializedName("situacao")
    @DatabaseField(canBeNull = true)
    private int situacao;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, columnName = PET_ID)
    public Pet pet;

    public Perdidos() {
    }

    public Perdidos(Date dt_reg, Date dt_fato, String local, Double local_lat, Double local_long, int situacao) {
        this.dt_reg = dt_reg;
        this.dt_fato = dt_fato;
        this.local = local;
        this.local_lat = local_lat;
        this.local_long = local_long;
        this.situacao = situacao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDt_reg() {
        return dt_reg;
    }

    public void setDt_reg(Date dt_reg) {
        this.dt_reg = dt_reg;
    }

    public Date getDt_fato() {
        return dt_fato;
    }

    public void setDt_fato(Date dt_fato) {
        this.dt_fato = dt_fato;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Double getLocal_lat() {
        return local_lat;
    }

    public void setLocal_lat(Double local_lat) {
        this.local_lat = local_lat;
    }

    public Double getLocal_long() {
        return local_long;
    }

    public void setLocal_long(Double local_long) {
        this.local_long = local_long;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    public int getSituacaoDescr() {
        int ret;
        if (situacao == 0){
            ret = R.string.achados_situacao_pendente;
        } else {
            ret = R.string.achados_situacao_resgatado;
        }
        return ret;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public List<?> populateCateg(){
        List<Perdidos> list  = new ArrayList<>();
        //(Date dt_reg, Date dt_fato, String local, Double local_lat, Double local_long, int situacao)
        list.add(new Perdidos(new Date(), new Date(), "Proximo a Av das Torres", 0.0,0.0,0));
        list.add(new Perdidos(new Date(), new Date(), "Proximo a Av do Turismo", 0.0,0.0,0));
        list.add(new Perdidos(new Date(), new Date(), "Proximo ao Centro da Cidade", 0.0,0.0,0));
        list.add(new Perdidos(new Date(), new Date(), "Proximo a Igreja Dos Remédios", 0.0,0.0,0));
        list.add(new Perdidos(new Date(), new Date(), "Proximo a ao Porto", 0.0,0.0,0));
        return list;
    }
}
