package com.itm.mobile.dados.ormlite.controller;

import android.content.Context;

import com.itm.mobile.dados.ormlite.DatabaseHelper;
import com.itm.mobile.dados.ormlite.DatabaseManager;
import com.itm.mobile.dados.ormlite.model.Perdidos;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class PerdidosRepo implements Crud{

    private DatabaseHelper helper;

    public PerdidosRepo(Context context) {
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) {
        int index = -1;

        Perdidos object = (Perdidos) item;
        try {
            index = helper.getPerdidosDao().create(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int update(Object item) {
        int index = -1;

        Perdidos object = (Perdidos) item;

        try {
            helper.getPerdidosDao().update(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int delete(Object item) {
        int index = -1;

        Perdidos object = (Perdidos) item;

        try {
            helper.getPerdidosDao().delete(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public Object findById(UUID id) {
        Perdidos Perdidos  = null;
        try {
            Perdidos = helper.getPerdidosDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Perdidos;
    }

    @Override
    public List<?> findAll() {
        List<Perdidos> items = null;

        try {
            items = helper.getPerdidosDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public List<?> findByEmail(String email) {
        List<Perdidos> items = null;
        try {
            QueryBuilder<Perdidos, UUID> queryBuilder = helper.getPerdidosDao().queryBuilder();
            queryBuilder.where().eq("email", email);
            items = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

}
