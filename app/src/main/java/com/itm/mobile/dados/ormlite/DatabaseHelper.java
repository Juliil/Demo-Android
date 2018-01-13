package com.itm.mobile.dados.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.itm.mobile.dados.ormlite.model.Achados;
import com.itm.mobile.dados.ormlite.model.Animal;
import com.itm.mobile.dados.ormlite.model.Perdidos;
import com.itm.mobile.dados.ormlite.model.Pet;
import com.itm.mobile.dados.ormlite.model.PetBook;
import com.itm.mobile.dados.ormlite.model.Produtos;
import com.itm.mobile.dados.ormlite.model.ProdutosCategoria;
import com.itm.mobile.dados.ormlite.model.ProdutosPromocao;
import com.itm.mobile.dados.ormlite.model.Tabelas;
import com.itm.mobile.dados.ormlite.model.Usuario;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.UUID;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	private static final String databaseName = "petlover";
	private static final int databaseVersion = 270413014;
	
	public DatabaseHelper(Context context) {
		super(context, databaseName, null, databaseVersion);
	}
	
	@Override
	public void onCreate(SQLiteDatabase sd, ConnectionSource cs) {
		try {
			TableUtils.createTable(cs, Achados.class);
			TableUtils.createTable(cs, Animal.class);
			TableUtils.createTable(cs, Perdidos.class);
			TableUtils.createTable(cs, Pet.class);
			TableUtils.createTable(cs, PetBook.class);
			TableUtils.createTable(cs, Produtos.class);
			TableUtils.createTable(cs, ProdutosCategoria.class);
			TableUtils.createTable(cs, ProdutosPromocao.class);
			TableUtils.createTable(cs, Tabelas.class);
			TableUtils.createTable(cs, Usuario.class);

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onUpgrade(SQLiteDatabase sd, ConnectionSource cs, int oldVersion, int newVersion) {
		try {

			/*List<String> allSql = new ArrayList<String>();
			switch(oldVersion)
			{
				case 1:
					//allSql.add("altere AdData add column `new_col` VARCHAR");
					//allSql.add("altere AdData add column `new_col2` VARCHAR");
			}
			for (String sql : allSql) {
				db.execSQL(sql);
			}*/

			TableUtils.dropTable(cs, Achados.class, true);
			TableUtils.dropTable(cs, Animal.class, true);
			TableUtils.dropTable(cs, Perdidos.class, true);
			TableUtils.dropTable(cs, Pet.class, true);
			TableUtils.dropTable(cs, PetBook.class, true);
			TableUtils.dropTable(cs, Produtos.class, true);
			TableUtils.dropTable(cs, ProdutosCategoria.class, true);
			TableUtils.dropTable(cs, ProdutosPromocao.class, true);
			TableUtils.dropTable(cs, Usuario.class, true);
			TableUtils.dropTable(cs, Tabelas.class, true);

			onCreate(sd, cs);
		}
		catch(SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "exception during onUpgrade", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close(){
		super.close();
	}


	private Dao<Achados, UUID> achadosDao = null;
	private Dao<Animal, UUID> animalDao = null;
	private Dao<Perdidos, UUID> perdidosDao = null;
	private Dao<Pet, UUID> petDao = null;
	private Dao<PetBook, UUID> petBookDao = null;
	private Dao<Produtos, UUID> produtosDao = null;
	private Dao<ProdutosCategoria, UUID> produtosCategoriaDao = null;
	private Dao<ProdutosPromocao, UUID> produtosPromocaoDao = null;
	private Dao<Usuario, UUID> usuarioDao = null;
	private Dao<Tabelas, UUID> tabelasDao = null;


	public Dao<Achados, UUID> getAchadosDao() throws SQLException {
		if (achadosDao == null) {
			try {
				achadosDao = getDao(Achados.class);
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return achadosDao;
	}

	public Dao<Animal, UUID> getAnimalDao() throws SQLException {
		if (animalDao == null) {
			try {
				animalDao = getDao(Animal.class);
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return animalDao;
	}

	public Dao<Perdidos, UUID> getPerdidosDao() throws SQLException {
		if (perdidosDao == null) {
			try {
				perdidosDao = getDao(Perdidos.class);
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return perdidosDao;
	}

	public Dao<Pet, UUID> getPetDao() throws SQLException {
		if (petDao == null) {
			try {
				petDao = getDao(Pet.class);
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return petDao;
	}

	public Dao<PetBook, UUID> getPetBookDao() throws SQLException {
		if (petBookDao == null) {
			try {
				petBookDao = getDao(PetBook.class);
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return petBookDao;
	}

	public Dao<Produtos, UUID> getProdutosDao() throws SQLException {
		if (produtosDao == null) {
			try {
				produtosDao = getDao(Produtos.class);
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return produtosDao;
	}

	public Dao<ProdutosCategoria, UUID> getProdutosCategoriaDao() throws SQLException {
		if (produtosCategoriaDao == null) {
			try {
				produtosCategoriaDao = getDao(ProdutosCategoria.class);
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return produtosCategoriaDao;
	}

	public Dao<ProdutosPromocao, UUID> getProdutosPromocaoDao() throws SQLException {
		if (produtosPromocaoDao == null) {
			try {
				produtosPromocaoDao = getDao(ProdutosPromocao.class);
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return produtosPromocaoDao;
	}

	public Dao<Usuario, UUID> getUsuarioDao() throws SQLException {
		if (usuarioDao == null) {
			try {
				usuarioDao = getDao(Usuario.class);
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return usuarioDao;
	}

	public Dao<Tabelas, UUID> getTabelasDao() throws SQLException {
		if (tabelasDao == null) {
			try {
				tabelasDao = getDao(Tabelas.class);
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return tabelasDao;
	}

}
