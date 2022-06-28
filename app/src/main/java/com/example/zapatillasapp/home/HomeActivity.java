package com.example.zapatillasapp.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.login.LoginActivity;
import com.example.zapatillasapp.menu.MenuPrincipalActivity;

public class HomeActivity
        extends AppCompatActivity implements HomeContract.View {

    public static String TAG = HomeActivity.class.getSimpleName();

    private HomeContract.Presenter presenter;

    private Button btnIniciarSesion, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle(R.string.Pantalla_Inicio);

        initLayout();
        enableLayouts();
        // do the setup
        HomeScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }
    }

    private void initLayout(){
        btnIniciarSesion = findViewById(R.id.button);
        btnRegistrar = findViewById(R.id.button2);
    }

    private void enableLayouts(){
        btnIniciarSesion.setOnClickListener(v -> presenter.onIniciarSesionBtnClicked());
        btnRegistrar.setOnClickListener(v -> presenter.onSinRegistrarBtnClicked());
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
    public void onDataUpdated(HomeViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToMenuScreen() {
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }
}