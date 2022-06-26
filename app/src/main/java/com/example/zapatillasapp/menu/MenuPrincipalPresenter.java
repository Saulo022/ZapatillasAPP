package com.example.zapatillasapp.menu;

import android.util.Log;

import com.example.zapatillasapp.app.AppMediator;
import com.example.zapatillasapp.app.SinRegistrarToHomeState;

import java.lang.ref.WeakReference;

public class MenuPrincipalPresenter implements MenuPrincipalContract.Presenter {

    public static String TAG = MenuPrincipalPresenter.class.getSimpleName();

    private WeakReference<MenuPrincipalContract.View> view;
    private MenuPrincipalState state;
    private MenuPrincipalContract.Model model;
    private AppMediator mediator;

    public MenuPrincipalPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getMenuPrincipalState();
    }

    @Override
    public void onStart() {
         Log.e(TAG, "onStartMenuPrincipal()");

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
        Log.e(TAG, "onResumeMenuPrincipal()");
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
    public void onTiendasBtnClicked(){
        SinRegistrarToHomeState newState = new SinRegistrarToHomeState();
        newState.sinRegistrar = state.sinRegistrar;
        passStateToNextScreen(newState);
        view.get().navigateToNextScreen();
    }

    @Override
    public void onFavoritosBtnClicked(){
        view.get().navigateToFavScreen();
    }

    private SinRegistrarToHomeState getStateFromPreviousScreen() {
        return mediator.getPreviousRegisterScreenState();
    }

    private void passStateToNextScreen(SinRegistrarToHomeState state) {
        mediator.setNextRegisterScreenState(state);
    }

    @Override
    public void injectView(WeakReference<MenuPrincipalContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(MenuPrincipalContract.Model model) {
        this.model = model;
    }

}