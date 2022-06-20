package com.example.zapatillasapp.tiendas;

import com.example.zapatillasapp.app.AppMediator;
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
    public void fetchTiendaListData(){

        model.fetchTiendaListData(new RepositoryContract.GetTiendaListCallback() {
            @Override
            public void setTiendaList(List<Tiendaitem> tiendas) {
                state.tiendas = tiendas;

                view.get().displayTiendaListData(state);
            }
        });
    }

    @Override
    public void selectCategoryListData(Tiendaitem item) {
        //router.passDataToProductListScreen(item);
        passDataToProductListScreen(item);
        //router.navigateToProductListScreen();
        view.get().navigateToNextScreen();
    }

    private void passDataToProductListScreen(Tiendaitem item) {
        mediator.setCategory(item);
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