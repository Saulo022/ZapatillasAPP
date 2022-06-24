package com.example.zapatillasapp.zapatillas;

import android.util.Log;

import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.ZapatillaItem;

public class ZapatillasListModel implements ZapatillasListContract.Model {

    public static String TAG = ZapatillasListModel.class.getSimpleName();

    private RepositoryContract repository;

    public ZapatillasListModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void fetchZapatillaListData(
            ZapatillaItem zapatilla, RepositoryContract.GetZapatillaListCallback callback){

        Log.e(TAG, "fetchZapatillasListData()");
        repository.getZapatillaList(zapatilla, callback);

    }
}