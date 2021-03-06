package com.example.zapatillasapp.home;

import java.lang.ref.WeakReference;

public interface HomeContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(HomeViewModel viewModel);

        void navigateToNextScreen();

        void navigateToMenuScreen();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void onBackPressed();

        void onPause();

        void onDestroy();

        void onIniciarSesionBtnClicked();

        void onSinRegistrarBtnClicked();
    }

    interface Model {
        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);
    }

}