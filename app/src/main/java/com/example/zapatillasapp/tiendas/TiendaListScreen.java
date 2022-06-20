package com.example.zapatillasapp.tiendas;

import androidx.fragment.app.FragmentActivity;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.app.AppMediator;
import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.ZapatillaAppRepository;

import java.lang.ref.WeakReference;

public class TiendaListScreen {

    public static void configure(TiendaListContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = AppMediator.getInstance();
        TiendaListContract.Presenter presenter = new TiendaListPresenter(mediator);

        RepositoryContract repository = ZapatillaAppRepository.getInstance(context.get());
        TiendaListContract.Model model = new TiendaListModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));
        view.injectPresenter(presenter);

    }
}
