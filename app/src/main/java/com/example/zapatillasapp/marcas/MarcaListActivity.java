package com.example.zapatillasapp.marcas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.data.MarcaItem;
import com.example.zapatillasapp.data.Tiendaitem;

public class MarcaListActivity
        extends AppCompatActivity implements MarcaListContract.View {

    public static String TAG = MarcaListActivity.class.getSimpleName();

    private MarcaListContract.Presenter presenter;

    private MarcaListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca_list);
        getSupportActionBar().setTitle(R.string.app_name);

        listAdapter = new MarcaListAdapter(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MarcaItem item =(MarcaItem) view.getTag();
                presenter.selectMarcaListData(item);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.marca_list);
        recyclerView.setAdapter(listAdapter);
        // do the setup
        MarcaListScreen.configure(this);


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
    public void displayMarcaListData(final MarcaListViewModel viewModel){
        Log.e(TAG, "displayMarcaListData()");

        runOnUiThread(() -> {
            Tiendaitem tienda = viewModel.tienda;

            listAdapter.setItems(viewModel.marcas);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataUpdated(MarcaListViewModel viewModel) {
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
    public void injectPresenter(MarcaListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}