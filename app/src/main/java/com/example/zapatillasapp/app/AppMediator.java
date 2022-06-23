package com.example.zapatillasapp.app;

import com.example.zapatillasapp.data.MarcaItem;
import com.example.zapatillasapp.data.Tiendaitem;
import com.example.zapatillasapp.data.ZapatillaItem;
import com.example.zapatillasapp.home.HomeState;
import com.example.zapatillasapp.login.LoginState;
import com.example.zapatillasapp.marcas.MarcaListState;
import com.example.zapatillasapp.menu.MenuPrincipalState;
import com.example.zapatillasapp.register.RegisterState;
import com.example.zapatillasapp.tiendas.TiendaListState;
import com.example.zapatillasapp.welcome.WelcomeState;

@SuppressWarnings("unused")
public class AppMediator {

    private static AppMediator INSTANCE;

    private HomeState homeState = new HomeState();
    private LoginState loginState = new LoginState();
    private RegisterState registerState = new RegisterState();
    private WelcomeState welcomeState = new WelcomeState();
    private MenuPrincipalState menuPrincipalState = new MenuPrincipalState();
    private TiendaListState tiendaListState = new TiendaListState();
    private MarcaListState marcaListState = new MarcaListState();

    private Tiendaitem tienda;
    private ZapatillaItem marca;

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

    public LoginState getLoginState() {
        return loginState;
    }

    public RegisterState getRegisterState() {
        return registerState;
    }

    public WelcomeState getWelcomeState() {
        return welcomeState;
    }

    public MenuPrincipalState getMenuPrincipalState() {
        return menuPrincipalState;
    }

    public TiendaListState getTiendaListState() {
        return tiendaListState;
    }

    public void setTienda(Tiendaitem item) {
        tienda = item;
    }

    public MarcaListState getMarcaListState() { return marcaListState;
    }

    public void setMarca(ZapatillaItem item) {
        marca = item;
    }

    public Tiendaitem getTienda() {
        Tiendaitem item = tienda;

        return item;
    }
}