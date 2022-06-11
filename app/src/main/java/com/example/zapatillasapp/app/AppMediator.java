package com.example.zapatillasapp.app;

import com.example.zapatillasapp.home.HomeState;

@SuppressWarnings("unused")
public class AppMediator {

    private static AppMediator INSTANCE;

    private HomeState homeState = new HomeState();


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

}