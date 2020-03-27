package com.zap.movieandtvshow;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;

import com.zap.movieandtvshow.movie.MovieTabFragment;
import com.zap.movieandtvshow.tvshow.TVDetail;
import com.zap.movieandtvshow.tvshow.TVShowTabFragment;

public class MainActivity extends AppCompatActivity {

    public static final String myAPI = "12e9a4179676130733c36f4c28358b1f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        String tvdetail = getIntent().getStringExtra(TVDetail.asal);

        if (savedInstanceState == null) {
            loadFragment(new MovieTabFragment());
            navView.setSelectedItemId(R.id.navigation_movie);
            if (tvdetail != null) {
                loadFragment(new TVShowTabFragment());
                navView.setSelectedItemId(R.id.navigation_tvshow);
            }
        }
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_movie:
                    fragment = new MovieTabFragment();
                    break;
                case R.id.navigation_tvshow:
                    fragment = new TVShowTabFragment();
                    break;
            }
            return loadFragment(fragment);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings){
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage(getResources().getString(R.string.confirmation_exitdialog))
                .setPositiveButton(getResources().getString(R.string.confirmation_exitdialog_yes), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }

                })
                .setNegativeButton(getResources().getString(R.string.confirmation_exitdialog_no), null)
                .show();
    }
}