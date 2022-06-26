package com.example.zapatillasapp.zapatillas;

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
import com.example.zapatillasapp.data.ZapatillaItem;
import com.example.zapatillasapp.zapatilladetail.ZapatillaDetailActivity;

public class ZapatillasListActivity
        extends AppCompatActivity implements ZapatillasListContract.View {

    public static String TAG = ZapatillasListActivity.class.getSimpleName();

    private ZapatillasListContract.Presenter presenter;

    private ZapatillasListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapatillas_list);
        getSupportActionBar().setTitle(R.string.app_name);


        listAdapter = new ZapatillasListAdapter(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZapatillaItem item = (ZapatillaItem) view.getTag();
                presenter.selectZapatillaListData(item);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.zapatillaList);
        recyclerView.setAdapter(listAdapter);

        // do the setup
        ZapatillasListScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }

       presenter.fetchZapatillaListData();
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
    public void displayZapatillasListData(final ZapatillasListViewModel viewModel){
        Log.e(TAG, "displayZapatillaListData()");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                listAdapter.setItems(viewModel.zapatillaItemList);

            }
        });
    }

    @Override
    public void onDataUpdated(ZapatillasListViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
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
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, ZapatillaDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(ZapatillasListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}