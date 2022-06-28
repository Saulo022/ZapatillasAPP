package com.example.zapatillasapp.welcome;

import com.example.zapatillasapp.app.AppMediator;
import com.example.zapatillasapp.app.SinRegistrarToHomeState;

import java.lang.ref.WeakReference;

public class WelcomePresenter implements WelcomeContract.Presenter {

    public static String TAG = WelcomePresenter.class.getSimpleName();

    private WeakReference<WelcomeContract.View> view;
    private WelcomeState state;
    private WelcomeContract.Model model;
    private AppMediator mediator;

    public WelcomePresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getWelcomeState();
    }

    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");

        // update the model if is necessary
        model.onRestartScreen(state.data);
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
    public void onMenuBtnClicked(){

        SinRegistrarToHomeState newState = new SinRegistrarToHomeState();
        newState.sinRegistrar = 0;
        passStateToNextScreen(newState);
        view.get().navigateToNextScreen();
    }

    private void passStateToNextScreen(SinRegistrarToHomeState state) {
        mediator.setNextRegisterScreenState(state);
    }

    @Override
    public void injectView(WeakReference<WelcomeContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(WelcomeContract.Model model) {
        this.model = model;
    }

}