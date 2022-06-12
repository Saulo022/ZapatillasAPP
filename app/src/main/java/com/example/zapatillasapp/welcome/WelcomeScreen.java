package com.example.zapatillasapp.welcome;

import androidx.fragment.app.FragmentActivity;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.app.AppMediator;

import java.lang.ref.WeakReference;

public class WelcomeScreen {

    public static void configure(WelcomeContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = AppMediator.getInstance();

        WelcomeContract.Presenter presenter = new WelcomePresenter(mediator);
        WelcomeContract.Model model = new WelcomeModel(data);
        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
