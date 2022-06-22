package com.example.zapatillasapp.marcas;

import androidx.fragment.app.FragmentActivity;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.app.AppMediator;
import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.ZapatillaAppRepository;

import java.lang.ref.WeakReference;

public class MarcaListScreen {

    public static void configure(MarcaListContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = AppMediator.getInstance();

        RepositoryContract repository = ZapatillaAppRepository.getInstance(context.get());

        MarcaListContract.Presenter presenter = new MarcaListPresenter(mediator);

        MarcaListModel model = new MarcaListModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
