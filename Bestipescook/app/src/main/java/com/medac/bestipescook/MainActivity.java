package com.medac.bestipescook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.medac.bestipescook.view.ChallengesFragment;
import com.medac.bestipescook.view.CuentaFragment;
import com.medac.bestipescook.view.NoticiasFragment;
import com.medac.bestipescook.view.RankingFragment;
import com.medac.bestipescook.view.RecetasFragment;

public class MainActivity extends AppCompatActivity{

    NoticiasFragment frNoticias = new NoticiasFragment();
    ChallengesFragment frChallenges = new ChallengesFragment();
    RecetasFragment frRecetas = new RecetasFragment();
    RankingFragment frRanking = new RankingFragment();
    CuentaFragment frCuenta = new CuentaFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.botton_navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(frNoticias);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_noticias:
                    loadFragment(frNoticias);
                    return true;
                case R.id.menu_challenges:
                    loadFragment(frChallenges);
                    return true;
                case R.id.menu_recetas:
                    loadFragment(frRecetas);
                    return true;
                case R.id.menu_ranking:
                    loadFragment(frRanking);
                    return true;
                case R.id.menu_cuenta:
                    loadFragment(frCuenta);
                    return true;
            }            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

}