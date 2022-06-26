package com.example.zapatillasapp.favoritos;

import androidx.fragment.app.FragmentActivity;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.app.AppMediator;
import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.ZapatillaAppRepository;

import java.lang.ref.WeakReference;

public class FavListScreen {

    public static void configure(FavListContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        AppMediator mediator = AppMediator.getInstance();

        RepositoryContract repository = ZapatillaAppRepository.getInstance(context.get());

        FavListContract.Presenter presenter = new FavListPresenter(mediator);

        FavListModel model = new FavListModel(repository);


        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));
        view.injectPresenter(presenter);

    }
}
