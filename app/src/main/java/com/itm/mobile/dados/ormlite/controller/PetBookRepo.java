package com.itm.mobile.dados.ormlite.controller;

import android.content.Context;

import com.itm.mobile.dados.ormlite.DatabaseHelper;
import com.itm.mobile.dados.ormlite.DatabaseManager;
import com.itm.mobile.dados.ormlite.model.PetBook;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class PetBookRepo implements Crud{

    private DatabaseHelper helper;

    public PetBookRepo(Context context) {
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) {
        int index = -1;

        PetBook object = (PetBook) item;
        try {
            index = helper.getPetBookDao().create(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int update(Object item) {
        int index = -1;

        PetBook object = (PetBook) item;

        try {
            helper.getPetBookDao().update(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int delete(Object item) {
        int index = -1;

        PetBook object = (PetBook) item;

        try {
            helper.getPetBookDao().delete(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public Object findById(UUID id) {
        PetBook PetBook  = null;
        try {
            PetBook = helper.getPetBookDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PetBook;
    }

    @Override
    public List<?> findAll() {
        List<PetBook> items = null;

        try {
            items = helper.getPetBookDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public List<?> findByEmail(String email) {
        List<PetBook> items = null;
        try {
            QueryBuilder<PetBook, UUID> queryBuilder = helper.getPetBookDao().queryBuilder();
            queryBuilder.where().eq("email", email);
            items = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

}
