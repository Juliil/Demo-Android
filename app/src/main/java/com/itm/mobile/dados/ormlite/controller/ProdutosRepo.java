package com.itm.mobile.dados.ormlite.controller;

import android.content.Context;

import com.itm.mobile.dados.ormlite.DatabaseHelper;
import com.itm.mobile.dados.ormlite.DatabaseManager;
import com.itm.mobile.dados.ormlite.model.Produtos;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ProdutosRepo implements Crud{

    private DatabaseHelper helper;

    public ProdutosRepo(Context context) {
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) {
        int index = -1;

        Produtos object = (Produtos) item;
        try {
            index = helper.getProdutosDao().create(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int update(Object item) {
        int index = -1;

        Produtos object = (Produtos) item;

        try {
            helper.getProdutosDao().update(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int delete(Object item) {
        int index = -1;

        Produtos object = (Produtos) item;

        try {
            helper.getProdutosDao().delete(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public Object findById(UUID id) {
        Produtos Produtos  = null;
        try {
            Produtos = helper.getProdutosDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Produtos;
    }

    @Override
    public List<?> findAll() {
        List<Produtos> items = null;

        try {
            items = helper.getProdutosDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public List<?> findByEmail(String email) {
        List<Produtos> items = null;
        try {
            QueryBuilder<Produtos, UUID> queryBuilder = helper.getProdutosDao().queryBuilder();
            queryBuilder.where().eq("email", email);
            items = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

}
