package com.itm.mobile.itmbase;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.itm.mobile.dados.ormlite.controller.AchadosRepo;
import com.itm.mobile.dados.ormlite.controller.AnimalRepo;
import com.itm.mobile.dados.ormlite.controller.PerdidosRepo;
import com.itm.mobile.dados.ormlite.controller.PetBookRepo;
import com.itm.mobile.dados.ormlite.controller.PetRepo;
import com.itm.mobile.dados.ormlite.controller.TabelasRepo;
import com.itm.mobile.dados.ormlite.controller.UsuarioRepo;
import com.itm.mobile.dados.ormlite.model.Achados;
import com.itm.mobile.dados.ormlite.model.Animal;
import com.itm.mobile.dados.ormlite.model.Perdidos;
import com.itm.mobile.dados.ormlite.model.Pet;
import com.itm.mobile.dados.ormlite.model.PetBook;
import com.itm.mobile.dados.ormlite.model.Tabelas;
import com.itm.mobile.dados.ormlite.model.Usuario;
import com.itm.mobile.login.LoginActivity;
import com.itm.mobile.petlover.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class SplashActivity extends AppCompatActivity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Temporary Populating Database
        populatingDatabase();

        ImageView mSplashButton = (ImageView) findViewById(R.id.ivSplash);
        mSplashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent intent = new Intent(SplashActivity.this, CondoMainActivity.class);
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });

        Handler handler = new Handler();
        handler.postDelayed(this, 3000);

    }

    public void run(){

        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        //Intent intent = new Intent(SplashActivity.this, CondoMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }


    private void populatingDatabase() {

        TabelasRepo tabelasRepo = new TabelasRepo(this);

        if (tabelasRepo.findAll().size() == 0) {
            UsuarioRepo usuarioRepo = new UsuarioRepo(this);
            PetRepo petRepo = new PetRepo(this);
            PetBookRepo petBookRepo = new PetBookRepo(this);
            AchadosRepo achadosRepo = new AchadosRepo(this);
            AnimalRepo animalRepo = new AnimalRepo(this);
            PerdidosRepo perdidosRepo = new PerdidosRepo(this);


            // Populating ServicosCategoria ListView
            Usuario usuario = new Usuario();
            ArrayList<Usuario> listUsuario = (ArrayList<Usuario>) usuario.populateCateg();
            for (Usuario elemento : listUsuario) {
                usuarioRepo.create(elemento);
            }

            // Populating ServicosCategoria ListView
            Pet pet = new Pet();
            ArrayList<Pet> listPet = (ArrayList<Pet>) pet.populateCateg();
            for (Pet elemento : listPet) {
                petRepo.create(elemento);
            }

            // Populating ServicosCategoria ListView
            PetBook petBook = new PetBook();
            ArrayList<PetBook> listPetBook = (ArrayList<PetBook>) petBook.populateCateg();
            for (PetBook elemento : listPetBook) {
                elemento.setUsuario(listUsuario.get(0));
                petBookRepo.create(elemento);
            }

            int i = 0;

            // Populating ServicosCategoria ListView
            Achados achados = new Achados();
            ArrayList<Achados> listAchados = (ArrayList<Achados>) achados.populateCateg();
            for (Achados elemento : listAchados) {
                elemento.setPet(listPet.get(i));
                achadosRepo.create(elemento);
                i++;
            }

            // Populating ServicosCategoria ListView
            Animal animal = new Animal();
            ArrayList<Animal> listAnimal = (ArrayList<Animal>) animal.populateCateg();
            i = 0;
            for (Animal elemento : listAnimal) {
                elemento.setPet(listPet.get(i));
                animalRepo.create(elemento);
                i++;
            }

            // Populating ServicosCategoria ListView
            Perdidos perdidos = new Perdidos();
            ArrayList<Perdidos> listPerdidos = (ArrayList<Perdidos>) perdidos.populateCateg();
            i = 0;
            for (Perdidos elemento : listPerdidos) {
                elemento.setPet(listPet.get(i));
                perdidosRepo.create(elemento);
                i++;
            }

            // Populating tabelas ListView
            Tabelas tabelas = new Tabelas();
            tabelas = new Tabelas("TABELAS", 10);
            tabelasRepo.create(tabelas);

        }
    }

}
