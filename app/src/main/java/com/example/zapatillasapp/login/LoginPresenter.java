package com.example.zapatillasapp.login;

import android.util.Log;

import com.example.zapatillasapp.app.AppMediator;

import java.lang.ref.WeakReference;

public class LoginPresenter implements LoginContract.Presenter {

    public static String TAG = LoginPresenter.class.getSimpleName();

    private WeakReference<LoginContract.View> view;
    private LoginState state;
    private LoginContract.Model model;
    private AppMediator mediator;

    public LoginPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getLoginState();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStartLogin()");
    }

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestartLogin()");
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResumeLogin()");
    }

    @Override
    public void onBackPressed() {
        Log.e(TAG, "onBackPressedLogin()");
    }

    @Override
    public void onPause() {
        Log.e(TAG, "onPauseLogin()");
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroyLogin()");
    }

    @Override
    public void injectView(WeakReference<LoginContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(LoginContract.Model model) {
        this.model = model;
    }

}