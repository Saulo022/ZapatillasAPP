package com.example.zapatillasapp.marcas;

import android.util.Log;

import com.example.zapatillasapp.app.AppMediator;
import com.example.zapatillasapp.data.MarcaItem;
import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.Tiendaitem;

import java.lang.ref.WeakReference;
import java.util.List;

public class MarcaListPresenter implements MarcaListContract.Presenter {

    public static String TAG = MarcaListPresenter.class.getSimpleName();

    private WeakReference<MarcaListContract.View> view;
    private MarcaListState state;
    private MarcaListContract.Model model;
    private AppMediator mediator;

    public MarcaListPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getMarcaListState();
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
    public void selectMarcaListData(MarcaItem item){
        passDataToZapatillasScreen(item);
        view.get().navigateToNextScreen();
    }

    private void passDataToZapatillasScreen(MarcaItem item) {
        mediator.setMarca(item);
    }

    @Override
    public void fetchMarcaListData(){
        Log.e(TAG, "fetchMarcaListData()");

        Tiendaitem tienda = getDataFromMarcaListScreen();

        if(tienda !=null){
            state.tienda = tienda;
        }

        model.fetchMarcaListData(state.tienda, marcas -> {
            state.marcas = marcas;

            view.get().displayMarcaListData(state);
        });
    }

    private Tiendaitem getDataFromMarcaListScreen() {

        Tiendaitem tienda = mediator.getTienda();
        return tienda;
    }

    @Override
    public void injectView(WeakReference<MarcaListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(MarcaListContract.Model model) {
        this.model = model;
    }

}