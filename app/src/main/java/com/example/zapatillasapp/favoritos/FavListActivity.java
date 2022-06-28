package com.example.zapatillasapp.favoritos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.data.ZapatillaItem;
import com.example.zapatillasapp.zapatilladetail.ZapatillaDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class FavListActivity
        extends AppCompatActivity implements FavListContract.View {

    public static String TAG = FavListActivity.class.getSimpleName();

    private FavListContract.Presenter presenter;

    private FavListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_list);
        getSupportActionBar().setTitle(R.string.Pantalla_Favoritos);

        listAdapter = new FavListAdapter(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZapatillaItem item = (ZapatillaItem) view.getTag();
                presenter.selectFavZapatillaListData(item);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.favList);
        recyclerView.setAdapter(listAdapter);

        // do the setup
        FavListScreen.configure(this);

        presenter.fetchFavZapatillaListData();
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
    public void onDataUpdated(FavListViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }

    @Override
    public void displayFavZapatillasListData(final FavListViewModel viewModel){
        Log.e(TAG, "displayFavZapatillasListData");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ZapatillaItem> favZapatillas = new ArrayList<>();
                for (int i=0; i<viewModel.zapatillaItemList.size(); i++){
                    if(viewModel.zapatillaItemList.get(i).fav){
                        favZapatillas.add(viewModel.zapatillaItemList.get(i));
                        Log.e(TAG, "p()" + favZapatillas);
                    }
                }
                listAdapter.setItems(favZapatillas);
            }
        });
    }

    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, ZapatillaDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(FavListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}