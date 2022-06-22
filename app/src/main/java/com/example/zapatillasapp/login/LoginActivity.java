package com.example.zapatillasapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.data.entityUser;
import com.example.zapatillasapp.database.ZapatillasAppDatabase;
import com.example.zapatillasapp.database.userDao;
import com.example.zapatillasapp.register.RegisterActivity;
import com.example.zapatillasapp.welcome.WelcomeActivity;

public class LoginActivity
        extends AppCompatActivity implements LoginContract.View {

    public static String TAG = LoginActivity.class.getSimpleName();

    private LoginContract.Presenter presenter;

    EditText editEmail,editPassword;
    Button buttonLogin;
    TextView textViewRegister;
    userDao db;
    ZapatillasAppDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle(R.string.login_name);

        // do the setup
        LoginScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }

        editEmail = findViewById(R.id.etRegisterName);
        editPassword = findViewById(R.id.etLoginPassword);
        buttonLogin = findViewById(R.id.btnRegister);
        textViewRegister = findViewById(R.id.textViewYaCuenta);


        database = Room.databaseBuilder(this, ZapatillasAppDatabase.class, "User")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        db = database.getUserDao();
        Log.e(TAG, "Login()" + database.getUserDao());

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                entityUser user = db.getentityUser(email,password);
                Log.e(TAG, "Login2()" + user);

                if(user != null){
                    Intent i = new Intent(LoginActivity.this, WelcomeActivity.class);
                    i.putExtra("User", user);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Usuario No Registrado o credenciales incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        presenter.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        presenter.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }

    @Override
    public void onDataUpdated(LoginViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }
}