package com.example.zapatillasapp.tiendas;

import android.util.Log;

import com.example.zapatillasapp.data.RepositoryContract;

public class TiendaListModel implements TiendaListContract.Model {

    public static String TAG = TiendaListModel.class.getSimpleName();

    //private String data;
    private RepositoryContract repository;

    public TiendaListModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void fetchTiendaListData(
            final RepositoryContract.GetTiendaListCallback callback) {

        Log.e(TAG, "fetchCategoryListData()");

        repository.loadCatalog(
                true, error -> {
                    if(!error) {
                        repository.getTiendaList(callback);
                    }
                });

    }

}