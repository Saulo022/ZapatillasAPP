package com.example.zapatillasapp.zapatilladetail;

import android.view.View;

import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.ZapatillaItem;

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

        void onCustomToggleClickON();
        void onCustomToggleClickOFF();
    }

    interface Model {

        void updateZapatillaList(
                ZapatillaItem zapatillaItem, RepositoryContract.UpdateZapatillaCallback callback
        );

    }

}