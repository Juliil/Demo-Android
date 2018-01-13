package com.itm.mobile.dados.ormlite.controller;

import android.content.Context;

import com.itm.mobile.dados.ormlite.DatabaseHelper;
import com.itm.mobile.dados.ormlite.DatabaseManager;
import com.itm.mobile.dados.ormlite.model.Usuario;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by WLFULTRABOOK on 21/03/2016.
 */
public class UsuarioRepo implements Crud{

    private DatabaseHelper helper;

    public UsuarioRepo(Context context) {
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) {
        int index = -1;

        Usuario object = (Usuario) item;
        try {
            index = helper.getUsuarioDao().create(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int update(Object item) {
        int index = -1;

        Usuario object = (Usuario) item;

        try {
            helper.getUsuarioDao().update(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int delete(Object item) {
        int index = -1;

        Usuario object = (Usuario) item;

        try {
            helper.getUsuarioDao().delete(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public Object findById(UUID id) {
        Usuario usuario  = null;
        try {
            usuario = helper.getUsuarioDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<?> findAll() {
        List<Usuario> items = null;

        try {
            items = helper.getUsuarioDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    // Exclusive Declarations

    public Usuario findByLogin(String login) {
        Usuario usuario  = null;
        try {
            QueryBuilder<Usuario, UUID> queryBuilder = helper.getUsuarioDao().queryBuilder();
            queryBuilder.where().ge("login", login);
            //usuario = helper.getUsuarioDao().queryRaw(queryBuilder.prepareStatementString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
