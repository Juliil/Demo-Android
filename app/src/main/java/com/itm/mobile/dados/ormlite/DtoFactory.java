package com.itm.mobile.dados.ormlite;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;

import com.itm.mobile.dados.ormlite.model.Usuario;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;


public class DtoFactory extends Application {

    private SharedPreferences preferences;
    private DatabaseHelper databaseHelper = null;

    private Dao<Usuario, Integer> usuarioDao = null;

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    //public File getVideosDir() {return videos_dir;}
    public SharedPreferences getPreferences() {return preferences;}

    /**
     * Returns the Database Access Object (DAO) for our Questionario class. It will create it or just give the cached
     * value.
     */
    public Dao<Usuario, Integer> getUsuarioDao() throws SQLException {
        if (usuarioDao == null) {
            try {
                usuarioDao = databaseHelper.getDao(Usuario.class);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return usuarioDao;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

}
