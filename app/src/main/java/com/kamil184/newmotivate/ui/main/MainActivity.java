package com.kamil184.newmotivate.ui.main;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.ui.addTodo.AddToDoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kamil184.newmotivate.util.Constants.LIGHT_THEME;
import static com.kamil184.newmotivate.util.Constants.THEME;

public class MainActivity extends AppCompatActivity  {

    /*@BindView(R.id.toolbar)
    Toolbar toolbar;*/
    private final String TAG = AddToDoActivity.class.getSimpleName();
    NavController navController;
    @BindView(R.id.main_bottom_nav)
    BottomNavigationView navView;
    boolean theme;
    SharedPreferences preference;
    SharedPreferences.OnSharedPreferenceChangeListener prefListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preference = PreferenceManager.getDefaultSharedPreferences(this);
        theme = preference.getBoolean(THEME, LIGHT_THEME);
        if (theme == LIGHT_THEME) {
            setTheme(R.style.CustomStyle_LightTheme);
        } else {
            setTheme(R.style.CustomStyle_DarkTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        prefListener = (sharedPreferences, s) -> {
            switch (s){
                case THEME:
                    recreate();
                    break;
            }
        };

        navController = Navigation.findNavController(this, R.id.main_nav_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        /*if (theme == LIGHT_THEME) {
            toolbar.setPopupTheme(R.style.LightPopup);
        } else {
            toolbar.setBackgroundColor(getResources().getColor(R.color.dark_color_surface));
        }
        setSupportActionBar(toolbar);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        preference.registerOnSharedPreferenceChangeListener(prefListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        preference.unregisterOnSharedPreferenceChangeListener(prefListener);

    }
}
