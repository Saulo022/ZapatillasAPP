package com.example.zapatillasapp.menu;

import androidx.fragment.app.FragmentActivity;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.app.AppMediator;

import java.lang.ref.WeakReference;

public class MenuPrincipalScreen {

    public static void configure(MenuPrincipalContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = AppMediator.getInstance();

        MenuPrincipalContract.Presenter presenter = new MenuPrincipalPresenter(mediator);
        MenuPrincipalContract.Model model = new MenuPrincipalModel(data);
        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
