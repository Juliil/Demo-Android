package com.itm.mobile.dados.ormlite.controller;

import android.content.Context;

import com.itm.mobile.dados.ormlite.DatabaseHelper;
import com.itm.mobile.dados.ormlite.DatabaseManager;
import com.itm.mobile.dados.ormlite.model.Pet;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class PetRepo implements Crud{

    private DatabaseHelper helper;

    public PetRepo(Context context) {
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) {
        int index = -1;

        Pet object = (Pet) item;
        try {
            index = helper.getPetDao().create(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int update(Object item) {
        int index = -1;

        Pet object = (Pet) item;

        try {
            helper.getPetDao().update(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int delete(Object item) {
        int index = -1;

        Pet object = (Pet) item;

        try {
            helper.getPetDao().delete(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public Object findById(UUID id) {
        Pet Pet  = null;
        try {
            Pet = helper.getPetDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Pet;
    }

    @Override
    public List<?> findAll() {
        List<Pet> items = null;

        try {
            items = helper.getPetDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public List<?> findByEmail(String email) {
        List<Pet> items = null;
        try {
            QueryBuilder<Pet, UUID> queryBuilder = helper.getPetDao().queryBuilder();
            queryBuilder.where().eq("email", email);
            items = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

}
