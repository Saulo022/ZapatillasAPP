package com.example.zapatillasapp.favoritos;

import android.util.Log;

import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.ZapatillaItem;

public class FavListModel implements FavListContract.Model {

    public static String TAG = FavListModel.class.getSimpleName();

    private RepositoryContract repository;

    public FavListModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void fetchFavZapatillaListData(
            ZapatillaItem zapatillaItem, RepositoryContract.GetZapatillaListCallback callback){

        repository.getAllFavZapatillaList(callback);
    }

}