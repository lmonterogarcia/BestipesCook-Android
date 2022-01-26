package com.medac.bestipescook.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.challenges.frChallenges;
import com.medac.bestipescook.controller.cuenta.frCuenta;
import com.medac.bestipescook.controller.noticias.frNoticias;
import com.medac.bestipescook.controller.ranking.frRanking;
import com.medac.bestipescook.controller.recetas.frRecetas;

public class MainActivity extends AppCompatActivity{

    com.medac.bestipescook.controller.noticias.frNoticias frNoticias = new frNoticias();
    com.medac.bestipescook.controller.challenges.frChallenges frChallenges = new frChallenges();
    com.medac.bestipescook.controller.recetas.frRecetas frRecetas = new frRecetas();
    com.medac.bestipescook.controller.ranking.frRanking frRanking = new frRanking();
    com.medac.bestipescook.controller.cuenta.frCuenta frCuenta = new frCuenta();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationBarView navigationView = findViewById(R.id.botton_navigation);
        navigationView.setOnItemSelectedListener(mOnNavigationItemSelectedListener);
        if (!frNoticias.isAdded()){loadFragment(frNoticias);}
    }

    private final BottomNavigationView.OnItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnItemSelectedListener() {
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
        if (!fragment.isAdded()){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            transaction.commit();
        }
    }

}