package com.example.zapatillasapp.tiendas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.data.Tiendaitem;
import com.example.zapatillasapp.marcas.MarcaListActivity;

public class TiendaListActivity
        extends AppCompatActivity implements TiendaListContract.View {

    public static String TAG = TiendaListActivity.class.getSimpleName();

    private TiendaListContract.Presenter presenter;

    private TiendaListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda_list);
        getSupportActionBar().setTitle(R.string.app_name);

        listAdapter = new TiendaListAdapter(view -> {
            Tiendaitem item = (Tiendaitem) view.getTag();
            presenter.selectCategoryListData(item);
        });

        RecyclerView recyclerView = findViewById(R.id.marcas_list2);
        recyclerView.setAdapter(listAdapter);


        // do the setup
        TiendaListScreen.configure(this);

        presenter.fetchTiendaListData();
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
    public void displayTiendaListData(final TiendaListViewModel viewModel) {
        Log.e(TAG, "displayCategoryListData()");

        runOnUiThread(() -> {

            // deal with the data
            listAdapter.setItems(viewModel.tiendas);
        });

    }

    @Override
    public void onDataUpdated(TiendaListViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, MarcaListActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(TiendaListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}