package com.example.zapatillasapp.app;

import com.example.zapatillasapp.home.HomeState;
import com.example.zapatillasapp.login.LoginState;

@SuppressWarnings("unused")
public class AppMediator {

    private static AppMediator INSTANCE;

    private HomeState homeState = new HomeState();
    private LoginState loginState = new LoginState();

    private AppMediator() {

    }


    public static AppMediator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppMediator();
        }

        return INSTANCE;
    }

    // to reset the state when testing
    public static void resetInstance() {
        INSTANCE = null;
    }

    public HomeState getHomeState() {
        return homeState;
    }

    public LoginState getLoginState() { return loginState; }
}