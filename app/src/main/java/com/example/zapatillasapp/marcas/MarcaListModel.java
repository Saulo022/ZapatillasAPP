package com.example.zapatillasapp.marcas;

import android.util.Log;

import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.Tiendaitem;

public class MarcaListModel implements MarcaListContract.Model {

    public static String TAG = MarcaListModel.class.getSimpleName();

    private RepositoryContract repository;

    public MarcaListModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void fetchMarcaListData(
            Tiendaitem tienda, RepositoryContract.GetMarcaListCallback callback){

        Log.e(TAG, "fetchMarcaListData()");
        repository.getMarcaList(tienda, callback);
    }
}