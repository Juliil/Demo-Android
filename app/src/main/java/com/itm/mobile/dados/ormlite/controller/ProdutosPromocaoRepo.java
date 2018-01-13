package com.itm.mobile.dados.ormlite.controller;

import android.content.Context;

import com.itm.mobile.dados.ormlite.DatabaseHelper;
import com.itm.mobile.dados.ormlite.DatabaseManager;
import com.itm.mobile.dados.ormlite.model.ProdutosPromocao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ProdutosPromocaoRepo implements Crud{

    private DatabaseHelper helper;

    public ProdutosPromocaoRepo(Context context) {
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) {
        int index = -1;

        ProdutosPromocao object = (ProdutosPromocao) item;
        try {
            index = helper.getProdutosPromocaoDao().create(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int update(Object item) {
        int index = -1;

        ProdutosPromocao object = (ProdutosPromocao) item;

        try {
            helper.getProdutosPromocaoDao().update(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int delete(Object item) {
        int index = -1;

        ProdutosPromocao object = (ProdutosPromocao) item;

        try {
            helper.getProdutosPromocaoDao().delete(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public Object findById(UUID id) {
        ProdutosPromocao ProdutosPromocao  = null;
        try {
            ProdutosPromocao = helper.getProdutosPromocaoDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ProdutosPromocao;
    }

    @Override
    public List<?> findAll() {
        List<ProdutosPromocao> items = null;

        try {
            items = helper.getProdutosPromocaoDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public List<?> findByEmail(String email) {
        List<ProdutosPromocao> items = null;
        try {
            QueryBuilder<ProdutosPromocao, UUID> queryBuilder = helper.getProdutosPromocaoDao().queryBuilder();
            queryBuilder.where().eq("email", email);
            items = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

}
