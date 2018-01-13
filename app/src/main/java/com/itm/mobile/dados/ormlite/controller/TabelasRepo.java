package com.itm.mobile.dados.ormlite.controller;

import android.content.Context;

import com.itm.mobile.dados.ormlite.DatabaseHelper;
import com.itm.mobile.dados.ormlite.DatabaseManager;
import com.itm.mobile.dados.ormlite.model.Tabelas;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class TabelasRepo implements Crud{

    private DatabaseHelper helper;

    public TabelasRepo(Context context) {
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) {
        int index = -1;

        Tabelas object = (Tabelas) item;
        try {
            index = helper.getTabelasDao().create(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int update(Object item) {
        int index = -1;

        Tabelas object = (Tabelas) item;

        try {
            helper.getTabelasDao().update(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int delete(Object item) {
        int index = -1;

        Tabelas object = (Tabelas) item;

        try {
            helper.getTabelasDao().delete(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public Object findById(UUID id) {
        Tabelas Tabelas  = null;
        try {
            Tabelas = helper.getTabelasDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Tabelas;
    }

    @Override
    public List<?> findAll() {
        List<Tabelas> items = null;

        try {
            items = helper.getTabelasDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public List<?> findByEmail(String email) {
        List<Tabelas> items = null;
        try {
            QueryBuilder<Tabelas, UUID> queryBuilder = helper.getTabelasDao().queryBuilder();
            queryBuilder.where().eq("email", email);
            items = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

}
