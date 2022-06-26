package com.example.zapatillasapp.home;

import android.util.Log;

import com.example.zapatillasapp.app.AppMediator;
import com.example.zapatillasapp.app.SinRegistrarToHomeState;

import java.lang.ref.WeakReference;

public class HomePresenter implements HomeContract.Presenter {

    public static String TAG = HomePresenter.class.getSimpleName();

    private WeakReference<HomeContract.View> view;
    private HomeState state;
    private HomeContract.Model model;
    private AppMediator mediator;

    public HomePresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getHomeState();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStartHome()");

        if(state ==null){
            state = new HomeState();
        }
    }

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestartHome()");

    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResumeHome()");
    }

    @Override
    public void onBackPressed() {
        Log.e(TAG, "onBackPressedHome()");
    }

    @Override
    public void onPause() {
        Log.e(TAG, "onPauseHome()");
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroyHome()");
    }

    @Override
    public void onIniciarSesionBtnClicked(){
        view.get().navigateToNextScreen();
    }

    @Override
    public void onSinRegistrarBtnClicked(){
        SinRegistrarToHomeState newState = new SinRegistrarToHomeState();
        newState.sinRegistrar = 1;
        passStateToNextScreen(newState);
        view.get().navigateToMenuScreen();
    }

    private void passStateToNextScreen(SinRegistrarToHomeState state) {
        mediator.setNextRegisterScreenState(state);
    }

    @Override
    public void injectView(WeakReference<HomeContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(HomeContract.Model model) {
        this.model = model;
    }

}