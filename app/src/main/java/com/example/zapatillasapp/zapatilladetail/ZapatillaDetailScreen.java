package com.example.zapatillasapp.zapatilladetail;

import androidx.fragment.app.FragmentActivity;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.app.AppMediator;

import java.lang.ref.WeakReference;

public class ZapatillaDetailScreen {

    public static void configure(ZapatillaDetailContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = AppMediator.getInstance();

        ZapatillaDetailContract.Presenter presenter = new ZapatillaDetailPresenter(mediator);
        ZapatillaDetailContract.Model model = new ZapatillaDetailModel(data);
        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
