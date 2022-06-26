package com.example.zapatillasapp.favoritos;

import android.util.Log;

import com.example.zapatillasapp.app.AppMediator;
import com.example.zapatillasapp.data.ZapatillaItem;

import java.lang.ref.WeakReference;

public class FavListPresenter implements FavListContract.Presenter {

    public static String TAG = FavListPresenter.class.getSimpleName();

    private WeakReference<FavListContract.View> view;
    private FavListState state;
    private FavListContract.Model model;
    private AppMediator mediator;

    public FavListPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getFavListState();
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
        // Log.e(TAG, "onResume()");
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
    public void fetchFavZapatillaListData(){
        Log.e(TAG, "fetchFavZapatillasListData()");

        ZapatillaItem zapatilla = getDataFromMarcaListScreen();

        if(zapatilla != null){
            state.zapatilla = zapatilla;
        }

        model.fetchFavZapatillaListData(state.zapatilla, zapatillas -> {
            state.zapatillaItemList = zapatillas;
            Log.e(TAG, "p()" + state.zapatillaItemList);

            view.get().displayFavZapatillasListData(state);
        });


    }

    private ZapatillaItem getDataFromMarcaListScreen() {

        ZapatillaItem zapatillaItem = mediator.getZapatilla();

        return zapatillaItem;
    }

    @Override
    public void selectFavZapatillaListData(ZapatillaItem item){
        passDataToZapatillaDetailScreen(item);
        view.get().navigateToNextScreen();
    }

    private void passDataToZapatillaDetailScreen(ZapatillaItem item){
        mediator.setZapatilla(item);
    }


    @Override
    public void injectView(WeakReference<FavListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(FavListContract.Model model) {
        this.model = model;
    }

}