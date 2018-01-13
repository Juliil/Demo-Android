package com.itm.mobile.dados.ormlite.controller;

import android.content.Context;

import com.itm.mobile.dados.ormlite.DatabaseHelper;
import com.itm.mobile.dados.ormlite.DatabaseManager;
import com.itm.mobile.dados.ormlite.model.ProdutosCategoria;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ProdutosCategoriaRepo implements Crud{

    private DatabaseHelper helper;

    public ProdutosCategoriaRepo(Context context) {
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) {
        int index = -1;

        ProdutosCategoria object = (ProdutosCategoria) item;
        try {
            index = helper.getProdutosCategoriaDao().create(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int update(Object item) {
        int index = -1;

        ProdutosCategoria object = (ProdutosCategoria) item;

        try {
            helper.getProdutosCategoriaDao().update(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int delete(Object item) {
        int index = -1;

        ProdutosCategoria object = (ProdutosCategoria) item;

        try {
            helper.getProdutosCategoriaDao().delete(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public Object findById(UUID id) {
        ProdutosCategoria ProdutosCategoria  = null;
        try {
            ProdutosCategoria = helper.getProdutosCategoriaDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ProdutosCategoria;
    }

    @Override
    public List<?> findAll() {
        List<ProdutosCategoria> items = null;

        try {
            items = helper.getProdutosCategoriaDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public List<?> findByEmail(String email) {
        List<ProdutosCategoria> items = null;
        try {
            QueryBuilder<ProdutosCategoria, UUID> queryBuilder = helper.getProdutosCategoriaDao().queryBuilder();
            queryBuilder.where().eq("email", email);
            items = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

}
