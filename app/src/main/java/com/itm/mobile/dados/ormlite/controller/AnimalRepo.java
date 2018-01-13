package com.itm.mobile.dados.ormlite.controller;

import android.content.Context;

import com.itm.mobile.dados.ormlite.DatabaseHelper;
import com.itm.mobile.dados.ormlite.DatabaseManager;
import com.itm.mobile.dados.ormlite.model.Animal;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class AnimalRepo implements Crud{

    private DatabaseHelper helper;

    public AnimalRepo(Context context) {
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) {
        int index = -1;

        Animal object = (Animal) item;
        try {
            index = helper.getAnimalDao().create(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int update(Object item) {
        int index = -1;

        Animal object = (Animal) item;

        try {
            helper.getAnimalDao().update(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int delete(Object item) {
        int index = -1;

        Animal object = (Animal) item;

        try {
            helper.getAnimalDao().delete(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public Object findById(UUID id) {
        Animal Animal  = null;
        try {
            Animal = helper.getAnimalDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Animal;
    }

    @Override
    public List<?> findAll() {
        List<Animal> items = null;

        try {
            items = helper.getAnimalDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public List<?> listVendas() {
        List<Animal> items = null;
        try {
            QueryBuilder<Animal, UUID> queryBuilder = helper.getAnimalDao().queryBuilder();
            queryBuilder.where().eq(Animal.CATEGORIA, 0);
            items = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public List<?> listDoacoes() {
        List<Animal> items = null;
        try {
            QueryBuilder<Animal, UUID> queryBuilder = helper.getAnimalDao().queryBuilder();
            queryBuilder.where().eq(Animal.CATEGORIA, 1);
            items = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }


    public List<?> findByEmail(String email) {
        List<Animal> items = null;
        try {
            QueryBuilder<Animal, UUID> queryBuilder = helper.getAnimalDao().queryBuilder();
            queryBuilder.where().eq("email", email);
            items = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

}
