package com.example.zapatillasapp.register;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.zapatillasapp.login.LoginActivity;

public class RegisterActivity
        extends AppCompatActivity implements RegisterContract.View {

    public static String TAG = RegisterActivity.class.getSimpleName();

    private RegisterContract.Presenter presenter;

    EditText editTextUsername, editTextEmail,editTextPassword1,editTextPassword2;
    Button buttonRegister;
    TextView textViewLogin;

    private userDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle(R.string.Pantalla_Registrarse);


        // do the setup
        RegisterScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }

        editTextUsername = findViewById(R.id.etRegisterName);
        editTextEmail = findViewById(R.id.etRegisterEmail);
        editTextPassword1 = findViewById(R.id.etRegisterPassword1);
        editTextPassword2 = findViewById(R.id.etRegisterPassword2);

        buttonRegister = findViewById(R.id.btnRegister);

        textViewLogin = findViewById(R.id.textViewYaCuenta);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        userDao = Room.databaseBuilder(this, ZapatillasAppDatabase.class,"User").allowMainThreadQueries().build().getUserDao();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextUsername.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password1 = editTextPassword1.getText().toString().trim();
                String password2 = editTextPassword2.getText().toString().trim();
                
                if(password1.equals(password2)){
                    entityUser user = new entityUser(userName, password1, email);
                    userDao.insert(user);
                    Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(moveToLogin);
                }else{
                    Toast.makeText(RegisterActivity.this, "La contraseña no coincide", Toast.LENGTH_SHORT).show();
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
    public void onDataUpdated(RegisterViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(RegisterContract.Presenter presenter) {
        this.presenter = presenter;
    }
}