package com.itm.mobile.dados.ormlite.model;

import com.google.gson.annotations.SerializedName;
import com.itm.mobile.petlover.R;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.types.DoubleObjectType;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@DatabaseTable(tableName="TB_ANIMAL")
public class Animal implements Serializable {

    public final static String PET_ID = "pet_id";
    public final static String PRECO = "preco";
    public final static String DT_REG = "dt_reg";
    public final static String DESCRICAO = "descricao";
    public final static String CATEGORIA = "categoria";
    public final static String CONDICOES = "condicoes";
    public final static String DETALHES = "detalhes";



    @SerializedName("id")
    @DatabaseField(generatedId = true)
    private UUID id;
    @SerializedName("dt_reg")
    @DatabaseField(canBeNull = false,  columnName = "DT_REG", dataType = DataType.DATE_STRING,
            format = "yyyy-MM-dd HH:mm:ss")
    Date dt_reg;
    @SerializedName("preco")
    @DatabaseField(canBeNull = true)
    Double preco;
    @SerializedName("categoria")
    @DatabaseField(canBeNull = false)
    int categoria; // 0 - venda; 1 - doacao
    @SerializedName("descricao")
    @DatabaseField(canBeNull = true)
    String descricao;
    @SerializedName("condicoes")
    @DatabaseField(canBeNull = true)
    String condicoes;
    @SerializedName("detalhes")
    @DatabaseField(canBeNull = true)
    String detalhes;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, columnName = PET_ID)
    public Pet pet;

    public Animal(){
    }

    public Animal(Date dt_reg, Double preco, String descricao, int categoria, String condicoes, String detalhes) {
        this.dt_reg = dt_reg;
        this.preco = preco;
        this.descricao = descricao;
        this.categoria = categoria;
        this.condicoes = condicoes;
        this.detalhes = detalhes;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getCondicoes() {
        return condicoes;
    }

    public void setCondicoes(String condicoes) {
        this.condicoes = condicoes;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public List<?> populateCateg(){
        List<Animal> list  = new ArrayList<>();
        //(Date dt_reg, Double preco, int categoria, String condicoes, String detalhes)
        list.add(new Animal(new Date(), 1000.00, "Macho Raça Pitbull, 3 Meses", 0, "A Vista, Cartão, 12X", "Data do Nascimento, Vermifugado, Vacinado, Motivo"));
        list.add(new Animal(new Date(), 500.00, "SEXO RAÇA IDADE ORIGEM A", 0, "A Vista, Cartão, 12X", "Data do Nascimento, Vermifugado, Vacinado, Motivo"));
        list.add(new Animal(new Date(), 1500.00, "SEXO RAÇA IDADE ORIGEM B", 0, "A Vista, Cartão, 12X", "Data do Nascimento, Vermifugado, Vacinado, Motivo"));
        list.add(new Animal(new Date(), 0.00, "RAÇA SEXO IDADE ORIGEM C", 1, "Animal Resgatado", "Data do Resgate, Vermifugado, Vacinado, Motivo"));
        list.add(new Animal(new Date(), 0.00, "RAÇA SEXO IDADE ORIGEM D", 1, "Ninhada própria - 10 Filhotes", "Data do Nascimento, Vermifugado, Vacinado, Motivo"));
        list.add(new Animal(new Date(), 0.00, "RAÇA SEXO IDADE ORIGEM E", 1, "Ninhada Adotada - 3 Filhotes", "Data do Nascimento, Vermifugado, Vacinado, Motivo"));

        return list;
    }
}
