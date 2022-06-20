package com.example.zapatillasapp.tiendas;

import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.Tiendaitem;

import java.lang.ref.WeakReference;

public interface TiendaListContract {

    interface View {

        void displayTiendaListData(final TiendaListViewModel viewModel);

        void injectPresenter(Presenter presenter);

        void onDataUpdated(TiendaListViewModel viewModel);

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

        void fetchTiendaListData();

        void selectCategoryListData(Tiendaitem item);
    }

    interface Model {
        void fetchTiendaListData(
                final RepositoryContract.GetTiendaListCallback callback);
    }

}