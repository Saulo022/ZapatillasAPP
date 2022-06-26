package com.example.zapatillasapp.favoritos;

import androidx.fragment.app.FragmentActivity;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.app.AppMediator;

import java.lang.ref.WeakReference;

public class FavListScreen {

    public static void configure(FavListContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = AppMediator.getInstance();

        FavListContract.Presenter presenter = new FavListPresenter(mediator);
        FavListContract.Model model = new FavListModel(data);
        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
