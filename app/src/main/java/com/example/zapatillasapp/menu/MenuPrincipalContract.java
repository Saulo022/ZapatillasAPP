package com.example.zapatillasapp.menu;

import java.lang.ref.WeakReference;

public interface MenuPrincipalContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(MenuPrincipalViewModel viewModel);

        void navigateToNextScreen();

        void navigateToFavScreen();
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

        void onTiendasBtnClicked();

        void onFavoritosBtnClicked();
    }

    interface Model {
        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);
    }

}