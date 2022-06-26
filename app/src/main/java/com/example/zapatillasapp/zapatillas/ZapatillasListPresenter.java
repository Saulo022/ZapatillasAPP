package com.example.zapatillasapp.zapatillas;

import android.util.Log;

import com.example.zapatillasapp.app.AppMediator;
import com.example.zapatillasapp.app.SinRegistrarToHomeState;
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
        Log.e(TAG, "onStartZapatillaList()");

        SinRegistrarToHomeState savedState = getStateFromPreviousScreen();
        if(savedState !=null){
            state.sinRegistrar = savedState.sinRegistrar;
            Log.e(TAG, "()" + savedState.sinRegistrar);
        }
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");
    }

    @Override
    public void onResume() {
         Log.e(TAG, "onResumeZapatillaList()");

        view.get().onDataUpdated(state);
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

        SinRegistrarToHomeState newState = new SinRegistrarToHomeState();
        newState.sinRegistrar = state.sinRegistrar;
        passStateToNextScreen(newState);

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

    private SinRegistrarToHomeState getStateFromPreviousScreen() {
        return mediator.getPreviousRegisterScreenState();
    }

    private void passStateToNextScreen(SinRegistrarToHomeState state) {
        mediator.setNextRegisterScreenState(state);
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