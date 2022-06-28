package com.example.zapatillasapp.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.data.entityUser;
import com.example.zapatillasapp.menu.MenuPrincipalActivity;

public class WelcomeActivity
        extends AppCompatActivity implements WelcomeContract.View {

    public static String TAG = WelcomeActivity.class.getSimpleName();

    private WelcomeContract.Presenter presenter;

    private TextView tvUser;
    private Button btnMenu;

    private entityUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().setTitle(R.string.Pantalla_Bienvenida);

        initLayout();
        enableLayouts();
        // do the setup
        WelcomeScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }

        user = (entityUser) getIntent().getSerializableExtra("User");

        tvUser = findViewById(R.id.tvUser);

        if (user != null) {
            tvUser.setText("Bienvenido " + user.getUserName());
        }
    }

    private void initLayout(){
        btnMenu = findViewById(R.id.btnMenu);
    }

    private void enableLayouts(){
        btnMenu.setOnClickListener(v -> presenter.onMenuBtnClicked());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        presenter.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        presenter.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }

    @Override
    public void onDataUpdated(WelcomeViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(WelcomeContract.Presenter presenter) {
        this.presenter = presenter;
    }
}