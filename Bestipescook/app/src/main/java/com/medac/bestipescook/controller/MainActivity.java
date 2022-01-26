package com.medac.bestipescook.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.retos.frRetos;
import com.medac.bestipescook.controller.cuenta.frCuenta;
import com.medac.bestipescook.controller.noticias.frNoticias;
import com.medac.bestipescook.controller.ranking.frRanking;
import com.medac.bestipescook.controller.recetas.frRecetas;

public class MainActivity extends AppCompatActivity{

    com.medac.bestipescook.controller.noticias.frNoticias frNoticias = new frNoticias();
    frRetos frChallenges = new frRetos();
    com.medac.bestipescook.controller.recetas.frRecetas frRecetas = new frRecetas();
    com.medac.bestipescook.controller.ranking.frRanking frRanking = new frRanking();
    com.medac.bestipescook.controller.cuenta.frCuenta frCuenta = new frCuenta();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationBarView navigationView = findViewById(R.id.botton_navigation);
        navigationView.setOnItemSelectedListener(mOnNavigationItemSelectedListener);
        if (!frNoticias.isAdded()){loadFragment(frNoticias, true);}
    }

    private final BottomNavigationView.OnItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            boolean fragmentLoad = false;
            switch (item.getItemId()) {
                case R.id.menu_noticias:
                    return loadFragment(frNoticias, fragmentLoad);
                case R.id.menu_challenges:
                    return loadFragment(frChallenges, fragmentLoad);
                case R.id.menu_recetas:
                    return loadFragment(frRecetas, fragmentLoad);
                case R.id.menu_ranking:
                    return loadFragment(frRanking, fragmentLoad);
                case R.id.menu_cuenta:
                    return loadFragment(frCuenta, fragmentLoad);
            }            return fragmentLoad;
        }
    };

    private boolean loadFragment(Fragment fragment, boolean fragmentLoad) {
        if (!fragment.isAdded()){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            transaction.commit();
            fragmentLoad = true;
        }
        return fragmentLoad;
    }

}