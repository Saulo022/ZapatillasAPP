package com.example.zapatillasapp.favoritos;

import com.example.zapatillasapp.app.AppMediator;

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
    public void injectView(WeakReference<FavListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(FavListContract.Model model) {
        this.model = model;
    }

}