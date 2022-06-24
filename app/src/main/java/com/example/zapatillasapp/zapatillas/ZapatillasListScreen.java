package com.example.zapatillasapp.zapatillas;

import androidx.fragment.app.FragmentActivity;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.app.AppMediator;
import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.ZapatillaAppRepository;

import java.lang.ref.WeakReference;

public class ZapatillasListScreen {

    public static void configure(ZapatillasListContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        AppMediator mediator = AppMediator.getInstance();

        RepositoryContract repository = ZapatillaAppRepository.getInstance(context.get());

        ZapatillasListContract.Presenter presenter = new ZapatillasListPresenter(mediator);

        ZapatillasListModel model = new ZapatillasListModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
