package com.example.zapatillasapp.menu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.favoritos.FavListActivity;
import com.example.zapatillasapp.tiendas.TiendaListActivity;

public class MenuPrincipalActivity
        extends AppCompatActivity implements MenuPrincipalContract.View {

    public static String TAG = MenuPrincipalActivity.class.getSimpleName();

    private MenuPrincipalContract.Presenter presenter;

    private Button btnTiendas, btnMenuFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);
        getSupportActionBar().setTitle(R.string.Pantalla_Menu);

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
        btnMenuFav = findViewById(R.id.btnMenuFav);
    }

    private void enableLayouts(){
        btnTiendas.setOnClickListener(v -> presenter.onTiendasBtnClicked());
        btnMenuFav.setOnClickListener(v -> presenter.onFavoritosBtnClicked());

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
        btnMenuFav = findViewById(R.id.btnMenuFav);
        if(viewModel.sinRegistrar == 1){
            btnMenuFav.setEnabled(false);
        }else {
            btnMenuFav.setEnabled(true);
        }
    }


    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, TiendaListActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToFavScreen() {
        Intent intent = new Intent(this, FavListActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(MenuPrincipalContract.Presenter presenter) {
        this.presenter = presenter;
    }
}