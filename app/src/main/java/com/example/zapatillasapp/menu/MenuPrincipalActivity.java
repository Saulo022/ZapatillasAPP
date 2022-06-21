package com.example.zapatillasapp.menu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.tiendas.TiendaListActivity;

public class MenuPrincipalActivity
        extends AppCompatActivity implements MenuPrincipalContract.View {

    public static String TAG = MenuPrincipalActivity.class.getSimpleName();

    private MenuPrincipalContract.Presenter presenter;

    private Button btnTiendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);
        getSupportActionBar().setTitle(R.string.menu_principal);

        initLayout();
        enableLayouts();
        // do the setup
        MenuPrincipalScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }
    }

    private void initLayout(){
        btnTiendas = findViewById(R.id.btnMenuTiendas);
    }

    private void enableLayouts(){
        btnTiendas.setOnClickListener(v -> presenter.onTiendasBtnClicked());
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
    public void onDataUpdated(MenuPrincipalViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, TiendaListActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(MenuPrincipalContract.Presenter presenter) {
        this.presenter = presenter;
    }
}