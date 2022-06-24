package com.example.zapatillasapp.zapatilladetail;

import android.util.Log;

import com.example.zapatillasapp.app.AppMediator;
import com.example.zapatillasapp.data.ZapatillaItem;

import java.lang.ref.WeakReference;

public class ZapatillaDetailPresenter implements ZapatillaDetailContract.Presenter {

    public static String TAG = ZapatillaDetailPresenter.class.getSimpleName();

    private WeakReference<ZapatillaDetailContract.View> view;
    private ZapatillaDetailState state;
    private ZapatillaDetailContract.Model model;
    private AppMediator mediator;

    public ZapatillaDetailPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getZapatillaDetailState();
    }

    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");

    }

    @Override
    public void onRestart() {
    }

    @Override
    public void onResume() {
         Log.e(TAG, "onResumeZapatillaDetail()");
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

    private ZapatillaItem getDataFromZapatillasListScreen() {
        ZapatillaItem zapatilla = mediator.getZapatilla();
        return zapatilla;
    }

    @Override
    public void fetchZapatillaDetailData() {
        // Log.e(TAG, "fetchProductDetailData()");

        // set passed state
        ZapatillaItem zapatilla = getDataFromZapatillasListScreen();
        //ProductItem product = router.getDataFromProductListScreen();
        if(zapatilla != null) {
            state.zapatillaItem = zapatilla;
        }

        view.get().displayZapatillaDetailData(state);
    }

    @Override
    public void injectView(WeakReference<ZapatillaDetailContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ZapatillaDetailContract.Model model) {
        this.model = model;
    }

}