package com.itm.mobile.dados.ormlite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.UUID;

@DatabaseTable(tableName="TB_TABELA")
public class Tabelas {

    public final static String TABELA_ID = "tabela_id";
    public final static String TABELA_NOME = "tabela_nome";
    public final static String TABELA_SEQ = "tabela_seq";

    @DatabaseField(generatedId = true)
    private UUID id;
    @DatabaseField(canBeNull = false)
    String nome;
    @DatabaseField(canBeNull = false)
    int sequencia;

    public Tabelas(){

    }

    public Tabelas(UUID id, String nome, int sequencia) {
        this.id = id;
        this.nome = nome;
        this.sequencia = sequencia;
    }

    public Tabelas(String nome, int sequencia) {
        this.nome = nome;
        this.sequencia = sequencia;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSequencia() {
        return sequencia;
    }

    public void setSequencia(int sequencia) {
        this.sequencia = sequencia;
    }
}
