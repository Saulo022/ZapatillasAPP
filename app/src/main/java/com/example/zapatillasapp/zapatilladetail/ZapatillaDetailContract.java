package com.example.zapatillasapp.zapatilladetail;

import java.lang.ref.WeakReference;

public interface ZapatillaDetailContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(ZapatillaDetailViewModel viewModel);

        void displayZapatillaDetailData(ZapatillaDetailViewModel viewModel);

        void navigateToNextScreen();
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

        void fetchZapatillaDetailData();
    }

    interface Model {
        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);
    }

}