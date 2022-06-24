package com.example.zapatillasapp.zapatilladetail;

public class ZapatillaDetailModel implements ZapatillaDetailContract.Model {

    public static String TAG = ZapatillaDetailModel.class.getSimpleName();

    private String data;

    public ZapatillaDetailModel(String data) {
        this.data = data;
    }

    @Override
    public String getStoredData() {
        // Log.e(TAG, "getStoredData()");
        return data;
    }

    @Override
    public void onRestartScreen(String data) {
        // Log.e(TAG, "onRestartScreen()");
    }

    @Override
    public void onDataFromNextScreen(String data) {
        // Log.e(TAG, "onDataFromNextScreen()");
    }

    @Override
    public void onDataFromPreviousScreen(String data) {
        // Log.e(TAG, "onDataFromPreviousScreen()");
    }
}