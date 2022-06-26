package com.example.zapatillasapp.zapatilladetail;

import androidx.fragment.app.FragmentActivity;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.app.AppMediator;
import com.example.zapatillasapp.data.RepositoryContract;
import com.example.zapatillasapp.data.ZapatillaAppRepository;

import java.lang.ref.WeakReference;

public class ZapatillaDetailScreen {

    public static void configure(ZapatillaDetailContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        AppMediator mediator = AppMediator.getInstance();

        RepositoryContract repository = ZapatillaAppRepository.getInstance(context.get());

        ZapatillaDetailContract.Presenter presenter = new ZapatillaDetailPresenter(mediator);
        ZapatillaDetailModel model = new ZapatillaDetailModel(repository);


        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));
        view.injectPresenter(presenter);

    }
}
