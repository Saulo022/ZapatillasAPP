package com.example.zapatillasapp.zapatilladetail;

import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.app.AppMediator;
import com.example.zapatillasapp.app.SinRegistrarToHomeState;
import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.ZapatillaItem;
import com.example.zapatillasapp.database.ZapatillaDao;

import java.lang.ref.WeakReference;

public class ZapatillaDetailPresenter implements ZapatillaDetailContract.Presenter {

    public static String TAG = ZapatillaDetailPresenter.class.getSimpleName();

    private WeakReference<ZapatillaDetailContract.View> view;
    private ZapatillaDetailState state;
    private ZapatillaDetailContract.Model model;
    private AppMediator mediator;

    Boolean fav = true;
    Boolean noFav = false;


    public ZapatillaDetailPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getZapatillaDetailState();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStartZapatillaDetail()");

        SinRegistrarToHomeState savedState = getStateFromPreviousScreen();
        if(savedState !=null){
            state.sinRegistrar = savedState.sinRegistrar;
            Log.e(TAG, "()" + savedState.sinRegistrar);
        }

    }

    @Override
    public void onRestart() {
    }

    @Override
    public void onResume() {
         Log.e(TAG, "onResumeZapatillaDetail()");

         fetchZapatillaDetailData();
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
    public void onCustomToggleClickON(){
        state.zapatillaItem.fav = true;

        model.updateZapatillaList(state.zapatillaItem, new RepositoryContract.UpdateZapatillaCallback() {
            @Override
            public void onZapatillaUpdated() {
                view.get().displayZapatillaDetailData(state);
                Log.e(TAG, "onDataUpdatedON()" + state.zapatillaItem.fav);
            }
        });
    }

    @Override
    public void onCustomToggleClickOFF(){
        state.zapatillaItem.fav = false;

        model.updateZapatillaList(state.zapatillaItem, new RepositoryContract.UpdateZapatillaCallback() {
            @Override
            public void onZapatillaUpdated() {
                view.get().displayZapatillaDetailData(state);
                Log.e(TAG, "onDataUpdatedOFF()" + state.zapatillaItem.fav);
            }
        });
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

    private SinRegistrarToHomeState getStateFromPreviousScreen() {
        return mediator.getPreviousRegisterScreenState();
    }

    private void passStateToNextScreen(SinRegistrarToHomeState state) {
        mediator.setNextRegisterScreenState(state);
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