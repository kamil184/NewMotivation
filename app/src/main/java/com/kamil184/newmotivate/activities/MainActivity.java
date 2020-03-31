package com.kamil184.newmotivate.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.base.BaseActivity;
import com.kamil184.newmotivate.utils.ToDoItem;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kamil184.newmotivate.constants.Constants.APP_PREFERENCES;
import static com.kamil184.newmotivate.constants.Constants.LIGHT_THEME;
import static com.kamil184.newmotivate.constants.Constants.THEME;
import static com.kamil184.newmotivate.constants.Constants.TODO_ITEM;

public class MainActivity extends BaseActivity {

    private static final int REQUEST_ID_TODO_ITEM = 1;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        themePreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        editor = themePreferences.edit();
        theme = themePreferences.getBoolean(THEME, LIGHT_THEME);
        if (theme == LIGHT_THEME) {
            setTheme(R.style.CustomStyle_LightTheme);
        } else {
            setTheme(R.style.CustomStyle_DarkTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (theme == LIGHT_THEME) {
            toolbar.setPopupTheme(R.style.LightPopup);
        } else {
            toolbar.setBackgroundColor(getResources().getColor(R.color.dark_color_surface));
        }
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.to_add_todo);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddToDoActivity.class);
            ToDoItem item = new ToDoItem();
            intent.putExtra(TODO_ITEM, item);
            startActivityForResult(intent, REQUEST_ID_TODO_ITEM);
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem darkTheme = menu.findItem(R.id.is_dark_theme);
        darkTheme.setChecked(theme);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.is_dark_theme) {
            theme = !theme;
            item.setChecked(!item.isChecked());
            editor.putBoolean(THEME, theme);
            editor.apply();
            recreate();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
