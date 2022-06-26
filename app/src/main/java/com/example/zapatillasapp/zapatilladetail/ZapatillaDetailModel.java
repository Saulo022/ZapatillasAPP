package com.example.zapatillasapp.zapatilladetail;

import android.util.Log;

import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.ZapatillaItem;

public class ZapatillaDetailModel implements ZapatillaDetailContract.Model {

    public static String TAG = ZapatillaDetailModel.class.getSimpleName();

    private final RepositoryContract repository;

    public ZapatillaDetailModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void updateZapatillaList(
            ZapatillaItem zapatillaItem, RepositoryContract.UpdateZapatillaCallback callback
    ){
        Log.e(TAG, "updateZapatillaList()");
        repository.updateFavZapatilla(zapatillaItem, callback);
    }
}