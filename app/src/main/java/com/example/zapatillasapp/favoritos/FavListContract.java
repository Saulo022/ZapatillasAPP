package com.example.zapatillasapp.favoritos;

import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.ZapatillaItem;

import java.lang.ref.WeakReference;

public interface FavListContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(FavListViewModel viewModel);

        void navigateToNextScreen();

        void displayFavZapatillasListData(final FavListViewModel viewModel);
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

        void fetchFavZapatillaListData();

        void selectFavZapatillaListData(ZapatillaItem item);
    }

    interface Model {

        void fetchFavZapatillaListData(
                ZapatillaItem zapatillaItem, RepositoryContract.GetZapatillaListCallback callback);
    }

}