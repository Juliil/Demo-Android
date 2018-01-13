package com.itm.mobile.dados.ormlite.controller;

import android.content.Context;

import com.itm.mobile.dados.ormlite.DatabaseHelper;
import com.itm.mobile.dados.ormlite.DatabaseManager;
import com.itm.mobile.dados.ormlite.model.Achados;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AchadosRepo implements Crud{

    private DatabaseHelper helper;

    public AchadosRepo(Context context) {
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) {
        int index = -1;

        Achados object = (Achados) item;
        try {
            index = helper.getAchadosDao().create(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int update(Object item) {
        int index = -1;

        Achados object = (Achados) item;

        try {
            helper.getAchadosDao().update(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int delete(Object item) {
        int index = -1;

        Achados object = (Achados) item;

        try {
            helper.getAchadosDao().delete(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public Object findById(UUID id) {
        Achados Achados  = null;
        try {
            Achados = helper.getAchadosDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Achados;
    }

    @Override
    public List<?> findAll() {
        List<Achados> items = null;

        try {
            items = helper.getAchadosDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public List<?> findAchados(String data, String local, String raca, String porte, String especie, String sexo) {
        List<Achados> items = null;
        try {
            QueryBuilder<Achados, UUID> queryBuilder = helper.getAchadosDao().queryBuilder();
            queryBuilder.where()
                    //.gt(Achados.DT_FATO, Date(data))
                    //.and()
                    .like(Achados.LOCAL, local);
                    /*.and()
                    .like("raca", raca)
                    .and()
                    .like("porte", porte)
                    .and()
                    .like("especie", especie)
                    .and()
                    .like("sexo", sexo);*/
            items = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

}
