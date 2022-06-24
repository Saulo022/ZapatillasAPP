package com.example.zapatillasapp.zapatillas;

import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.ZapatillaItem;

import java.lang.ref.WeakReference;

public interface ZapatillasListContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(ZapatillasListViewModel viewModel);

        void navigateToNextScreen();

        void displayZapatillasListData(final ZapatillasListViewModel viewModel);
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

        void selectZapatillaListData(ZapatillaItem item);

        void fetchZapatillaListData();
    }

    interface Model {

        void fetchZapatillaListData(
                ZapatillaItem zapatilla, RepositoryContract.GetZapatillaListCallback callback);
    }

}