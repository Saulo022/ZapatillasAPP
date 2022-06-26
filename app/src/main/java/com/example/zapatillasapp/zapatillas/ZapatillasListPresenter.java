package com.example.zapatillasapp.zapatillas;

import android.util.Log;

import com.example.zapatillasapp.app.AppMediator;
import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.ZapatillaItem;

import java.lang.ref.WeakReference;
import java.util.List;

public class ZapatillasListPresenter implements ZapatillasListContract.Presenter {

    public static String TAG = ZapatillasListPresenter.class.getSimpleName();

    private WeakReference<ZapatillasListContract.View> view;
    private ZapatillasListState state;
    private ZapatillasListContract.Model model;
    private AppMediator mediator;

    public ZapatillasListPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getZapatillasListState();
    }

    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");
    }

    @Override
    public void onResume() {
         Log.e(TAG, "onResumeZapatillaList()");

        //Log.e(TAG, "onResumeZapatillaList()" + state.zapatillaItemList.get(0).fav);
    }

    @Override
    public void onBackPressed() {
        // Log.e(TAG, "onBackPressed()");
    }

    @Override
    public void onPause() {
        // Log.e(TAG, "onPause()");
    }

    @Override
    public void onDestroy() {
        // Log.e(TAG, "onDestroy()");
    }

    @Override
    public void selectZapatillaListData(ZapatillaItem item){
        passDataToZapatillaDetailScreen(item);
        view.get().navigateToNextScreen();
    }

    private void passDataToZapatillaDetailScreen(ZapatillaItem item) {
        mediator.setZapatilla(item);
    }

    @Override
    public void fetchZapatillaListData(){

        Log.e(TAG, "fetchZapatillasListData()");

        ZapatillaItem zapatilla = getDataFromMarcaListScreen();

        if(zapatilla !=null){
            state.zapatilla = zapatilla;
            Log.e(TAG, "p()" + state.zapatilla);
        }

        model.fetchZapatillaListData(state.zapatilla, zapatillas -> {
            state.zapatillaItemList = zapatillas;
            Log.e(TAG, "p()" + state.zapatillaItemList);

            view.get().displayZapatillasListData(state);
        });
    }

    private ZapatillaItem getDataFromMarcaListScreen() {

        ZapatillaItem zapatillaItem = mediator.getZapatilla();

        return zapatillaItem;
    }

    @Override
    public void injectView(WeakReference<ZapatillasListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ZapatillasListContract.Model model) {
        this.model = model;
    }

}