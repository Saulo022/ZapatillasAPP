package com.example.zapatillasapp.menu;

import com.example.zapatillasapp.app.AppMediator;

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
    public void onTiendasBtnClicked(){
        view.get().navigateToNextScreen();
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