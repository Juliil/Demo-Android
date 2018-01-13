package com.itm.mobile.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.itm.mobile.config.Constants;
import com.itm.mobile.dados.retrofit.models.LoginWeb;
import com.itm.mobile.dados.retrofit.models.UsuarioWeb;
import com.itm.mobile.dados.retrofit.restclients.UsuarioWebRestClient;
import com.itm.mobile.petlover.MainActivity;
import com.itm.mobile.petlover.R;
import com.itm.mobile.util.CheckNetwork;
import com.itm.mobile.util.Session;
import com.itm.mobile.util.UserSessionManager;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.itm.mobile.config.Constants.BASE_URL;

public class LoginActivity extends AppCompatActivity {

    private Session session;

    String serverImageURL;

    private UserSessionManager userSession;

    HashMap<String, String> user;

    EditText et_login_email, et_login_passwd;
    ProgressBar pb_login;

    UsuarioWeb usuarioWeb;

    UsuarioWebRestClient usuarioWebRestClient = UsuarioWebRestClient.getInstance();

    UsuarioWebRestClient.ResultGetCallback getCallback = new UsuarioWebRestClient.ResultGetCallback() {
        @Override
        public void resultReady(UsuarioWeb user) {
            if (user != null){
                usuarioWeb = user;
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("USUARIO", user);
                //intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                finish();
                startActivity(intent);
            } else {
                et_login_email.setTextColor(Color.RED);
                et_login_email.setError(getString(R.string.error_login));
                et_login_email.requestFocus();
            }
            pb_login.setVisibility(View.GONE);
            getSupportActionBar().setTitle(R.string.title_activity_login);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        serverImageURL = sharedPreferences.getString(Constants.IMAGE_SERVER_URL, BASE_URL);

        // Session class instance
        userSession = new UserSessionManager(this.getApplicationContext());
        session = new Session(this.getApplicationContext());
        session.deleteKey();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_user);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, NewUserActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        et_login_email = (EditText) findViewById(R.id.et_login_email);
        et_login_passwd = (EditText) findViewById(R.id.et_login_passwd);
        pb_login = (ProgressBar) findViewById(R.id.pb_login);
    }

    private boolean validaInput() {

        boolean retorno = true;
        View focusView = null;

        // Check the Availability of the Network Connection
        if (!CheckNetwork.isConnectedOrConnecting(this)){
            et_login_email.setError(getString(R.string.error_network_error));
            focusView = et_login_email;
            retorno = true;
        }

        if (TextUtils.isEmpty(et_login_email.getText())){
            et_login_email.setTextColor(Color.RED);
            et_login_email.setError(getString(R.string.error_required_field));
            focusView = et_login_email;
            retorno = false;
        } else if (!isEmailValid(et_login_email.getText().toString())) {
            et_login_email.setTextColor(Color.RED);
            et_login_email.setError(getString(R.string.error_invalid_email));
            focusView = et_login_email;
            retorno = false;
        }

        if (!TextUtils.isEmpty(et_login_passwd.getText()) && !isPasswordValid(et_login_passwd.getText().toString())) {
            et_login_passwd.setTextColor(Color.RED);
            et_login_passwd.setError(getString(R.string.error_required_field));
            focusView = et_login_passwd;
            retorno = false;
        }
        if (focusView != null) {
            focusView.requestFocus();
        }
        return retorno;
    }

    public void loginProcess(View view){
        if (validaInput()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("USUARIO", user);
            //intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            finish();
            startActivity(intent);
           /* pb_login.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle(R.string.lbl_progress);
            LoginWeb loginWeb = new LoginWeb();
            usuarioWebRestClient.setCallback(getCallback);
            //usuarioWebRestClient.autenticate(loginWeb);
            usuarioWebRestClient.login(et_login_email.getText().toString(), et_login_passwd.getText().toString());
            //usuarioWebRestClient.autenticate(loginWeb);*/
        }
    }

    private boolean isEmailValid(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}
