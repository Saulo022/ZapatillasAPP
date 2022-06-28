package com.example.zapatillasapp.zapatilladetail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.zapatillasapp.R;
import com.example.zapatillasapp.data.ZapatillaItem;
import com.example.zapatillasapp.database.ZapatillaDao;
import com.example.zapatillasapp.menu.MenuPrincipalActivity;
import com.example.zapatillasapp.zapatillas.ZapatillasListActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ZapatillaDetailActivity
        extends AppCompatActivity implements ZapatillaDetailContract.View {

    public static String TAG = ZapatillaDetailActivity.class.getSimpleName();

    private ZapatillaDetailContract.Presenter presenter;

    ToggleButton tgbtn;
    FloatingActionButton homeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapatilla_detail);
        getSupportActionBar().setTitle(R.string.Pantalla_Detalles);



        // do the setup
        ZapatillaDetailScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }

        presenter.fetchZapatillaDetailData();

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
    public void onDataUpdated(ZapatillaDetailViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
        /*
        tgbtn = findViewById(R.id.toggleButton);
        if(viewModel.zapatillaItem.fav == true){
            tgbtn.isChecked() ;
        }*/
        tgbtn = findViewById(R.id.toggleButton);
        if(viewModel.sinRegistrar ==1){
            tgbtn.setEnabled(false);
        }else{
            tgbtn.setEnabled(true);
        }

    }


    public void onFloatingHomeBtnClicked(View view){
        homeBtn = findViewById(R.id.floatingHomeButton);
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);

    }

    public void onCustomToggleClick(View view){
        if(view.getId()== R.id.toggleButton){
            tgbtn = findViewById(R.id.toggleButton);
            if(tgbtn.isChecked()){
                Toast.makeText(this, "Añadido a favoritos", Toast.LENGTH_SHORT).show();
                presenter.onCustomToggleClickON();

            }else{
                Toast.makeText(this, "Eliminado de favoritos", Toast.LENGTH_SHORT).show();
                presenter.onCustomToggleClickOFF();
            }
        }
    }

    @Override
    public void displayZapatillaDetailData(ZapatillaDetailViewModel viewModel){
        Log.e(TAG, "displayZapatillaDetailData()");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                ZapatillaItem zapatillaItem = viewModel.zapatillaItem;


                ((TextView) findViewById(R.id.txtTienda)).setText("Tienda: " + zapatillaItem.tienda1);
                ((TextView) findViewById(R.id.txtNombre)).setText("Nombre: " + zapatillaItem.nombre);
                ((TextView) findViewById(R.id.txtMarca)).setText("Marca: " + zapatillaItem.marcas);
                ((TextView) findViewById(R.id.txtColor)).setText("Color: " + zapatillaItem.colores);
                ((TextView) findViewById(R.id.txtTallas)).setText("Tallas: " + zapatillaItem.tallas);
                ((TextView) findViewById(R.id.txtPrecio)).setText("Precio: " + zapatillaItem.precio);

                if(zapatillaItem.fav){
                    ((ToggleButton) findViewById(R.id.toggleButton)).setChecked(true);
                }else{
                    ((ToggleButton) findViewById(R.id.toggleButton)).setChecked(false);
                }


                loadImageFromURL((ImageView) findViewById(R.id.logoZapatillaDetail), zapatillaItem.fotoZap);
            }
        });



    }


    private void loadImageFromURL(ImageView imageView, String imageUrl){
        RequestManager reqManager = Glide.with(imageView.getContext());
        RequestBuilder reqBuilder = reqManager.load(imageUrl);
        RequestOptions reqOptions = new RequestOptions();
        reqOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        reqBuilder.apply(reqOptions);
        reqBuilder.into(imageView);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, ZapatillasListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, ZapatillaDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(ZapatillaDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }
}