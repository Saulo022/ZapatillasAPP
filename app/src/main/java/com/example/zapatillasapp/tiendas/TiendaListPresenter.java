package com.example.zapatillasapp.tiendas;

import android.util.Log;

import com.example.zapatillasapp.app.AppMediator;
import com.example.zapatillasapp.app.SinRegistrarToHomeState;
import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.Tiendaitem;
import com.example.zapatillasapp.data.ZapatillaItem;

import java.lang.ref.WeakReference;
import java.util.List;

public class TiendaListPresenter implements TiendaListContract.Presenter {

    public static String TAG = TiendaListPresenter.class.getSimpleName();

    private WeakReference<TiendaListContract.View> view;
    private TiendaListState state;
    private TiendaListContract.Model model;
    private AppMediator mediator;

    public TiendaListPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getTiendaListState();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStartTiendaList()");

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
        Log.e(TAG, "onResumeTiendaList()");
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
    public void fetchTiendaListData(){

        model.fetchTiendaListData(tiendas -> {
            state.tiendas = tiendas;
            Log.e(TAG, "p()" + state.tiendas);

            view.get().displayTiendaListData(state);
        });
    }

    @Override
    public void selectCategoryListData(Tiendaitem item) {
        //router.passDataToProductListScreen(item);
        passDataToProductListScreen(item);
        //router.navigateToProductListScreen();
        SinRegistrarToHomeState newState = new SinRegistrarToHomeState();
        newState.sinRegistrar = state.sinRegistrar;
        passStateToNextScreen(newState);
        view.get().navigateToNextScreen();
    }

    private void passDataToProductListScreen(Tiendaitem item) {
        mediator.setTienda(item);
    }

    private SinRegistrarToHomeState getStateFromPreviousScreen() {
        return mediator.getPreviousRegisterScreenState();
    }

    private void passStateToNextScreen(SinRegistrarToHomeState state) {
        mediator.setNextRegisterScreenState(state);
    }

    @Override
    public void injectView(WeakReference<TiendaListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(TiendaListContract.Model model) {
        this.model = model;
    }

}