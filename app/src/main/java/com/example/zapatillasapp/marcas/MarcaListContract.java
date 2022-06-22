package com.example.zapatillasapp.marcas;

import com.example.zapatillasapp.data.MarcaItem;
import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.Tiendaitem;

import java.lang.ref.WeakReference;

public interface MarcaListContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(MarcaListViewModel viewModel);

        void navigateToNextScreen();

        void displayMarcaListData(final MarcaListViewModel viewModel);
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

        void selectMarcaListData(MarcaItem item);

        void fetchMarcaListData();
    }

    interface Model {
        void fetchMarcaListData(
                Tiendaitem tienda, RepositoryContract.GetMarcaListCallback callback);
    }

}